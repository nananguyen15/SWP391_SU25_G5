package com.swp391.bookverse.service.auth.otp;

import com.swp391.bookverse.entity.auth.otp.OtpToken;
import com.swp391.bookverse.repository.auth.otp.OtpTokenRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class OtpService {
    private final JavaMailSender mailSender;
    private final OtpTokenRepository repo;
    private final SecureRandom rnd = new SecureRandom();
    private final Duration ttl = Duration.ofMinutes(5);
    private final int resendCooldownSec = 45;

    public OtpService(JavaMailSender mailSender, OtpTokenRepository repo) {
        this.mailSender = mailSender;
        this.repo = repo;
    }

    @Transactional
    public void sendOtpByEmail(String email, String tokenType) {
        String normEmail = email.trim().toLowerCase();

        repo.findTopByEmailAndUsedFalseOrderByCreatedAtDesc(normEmail).ifPresent(last -> {
            if (Instant.now().isBefore(last.getCreatedAt().plusSeconds(resendCooldownSec))) {
                throw new IllegalStateException("Please wait before requesting another code.");
            }
        });

        String code = gen6();
        OtpToken t = new OtpToken();
        t.setEmail(normEmail);
        t.setUserId(null); // not linked yet
        t.setCode(code);
        t.setTokenType(tokenType);
        t.setCreatedAt(Instant.now());
        t.setExpiresAt(t.getCreatedAt().plus(ttl));
        repo.save(t);

        sendEmail(normEmail, code);
    }

    @Transactional
    public void sendOtpForUser(String userId, String email, String tokenType) {
        String normEmail = email.trim().toLowerCase();
        Optional<OtpToken> last = repo.findTopByUserIdAndUsedFalseOrderByCreatedAtDesc(userId);
        if (last.isPresent() && Instant.now().isBefore(last.get().getCreatedAt().plusSeconds(resendCooldownSec))) {
            throw new IllegalStateException("Please wait before requesting another code.");
        }

        String code = gen6();
        OtpToken t = new OtpToken();
        t.setEmail(normEmail);
        t.setUserId(userId);
        t.setCode(code);
        t.setTokenType(tokenType);
        t.setCreatedAt(Instant.now());
        t.setExpiresAt(t.getCreatedAt().plus(ttl));
        repo.save(t);

        sendEmail(normEmail, code);
    }

    @Transactional
    public boolean verify(String emailOrNull, String userIdOrNull, String code, String tokenType) {
        OtpToken token = (userIdOrNull != null && !userIdOrNull.isBlank())
                ? repo.findTopByUserIdAndUsedFalseOrderByCreatedAtDesc(userIdOrNull).orElse(null)
                : repo.findTopByEmailAndUsedFalseOrderByCreatedAtDesc(emailOrNull.trim().toLowerCase()).orElse(null);

        if (token == null) return false;
        if (!tokenType.equals(token.getTokenType())) return false;
        if (Instant.now().isAfter(token.getExpiresAt())) return false;
        if (!token.getCode().equals(code)) return false;

        token.setUsed(true);
        repo.save(token);
        return true;
    }

    private String gen6() {
        return String.format("%06d", rnd.nextInt(1_000_000));
    }

    private void sendEmail(String to, String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Your Bookverse verification code");
        msg.setText("""
        Your verification code is: %s

        It expires in 5 minutes. If you didn't request this, you can ignore this email.
        """.formatted(code));
        mailSender.send(msg);
    }
}
