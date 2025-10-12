package com.swp391.bookverse.controller.auth.otp;

import com.swp391.bookverse.service.auth.otp.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

record SendByEmailReq(String email, String tokenType) {}
record SendForUserReq(String userId, String email, String tokenType) {}
record VerifyReq(String email, String userId, String code, String tokenType) {}

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final OtpService svc;

    public OtpController(OtpService svc) { this.svc = svc; }

    @PostMapping("/send-by-email")
    public ResponseEntity<?> sendByEmail(@RequestBody SendByEmailReq req) {
        svc.sendOtpByEmail(req.email(), req.tokenType() == null ? "LOGIN" : req.tokenType());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-for-user")
    public ResponseEntity<?> sendForUser(@RequestBody SendForUserReq req) {
        svc.sendOtpForUser(req.userId(), req.email(), req.tokenType() == null ? "LOGIN" : req.tokenType());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyReq req) {
        boolean ok = svc.verify(req.email(), req.userId(), req.code(),
                req.tokenType() == null ? "LOGIN" : req.tokenType());
        return ok ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("Invalid or expired code.");
    }
}
