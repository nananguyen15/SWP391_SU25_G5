<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Bookverse – README</title>
  <style>
    :root { --bg:#0f172a; --card:#111827; --text:#e5e7eb; --muted:#9ca3af; --accent:#22d3ee; }
    * { box-sizing: border-box; }
    body { margin:0; font-family: ui-sans-serif, system-ui, -apple-system, Segoe UI, Roboto, Ubuntu, Cantarell, Noto Sans, Helvetica Neue, Arial, "Apple Color Emoji", "Segoe UI Emoji"; background: linear-gradient(180deg, #0b1220, #0f172a); color: var(--text); }
    a { color: var(--accent); text-decoration: none; }
    a:hover { text-decoration: underline; }
    .container { max-width: 980px; margin: 0 auto; padding: 48px 24px 64px; }
    .card { background: rgba(17,24,39,0.7); border: 1px solid rgba(255,255,255,0.06); border-radius: 16px; padding: 28px; margin-bottom: 20px; backdrop-filter: saturate(140%) blur(6px); }
    h1 { font-size: 42px; margin: 0 0 10px; letter-spacing: -0.02em; }
    .tagline { color: var(--muted); margin: 0 0 28px; }
    h2 { font-size: 24px; margin: 26px 0 12px; border-bottom: 1px dashed rgba(255,255,255,0.1); padding-bottom: 8px; }
    ul { margin: 12px 0 8px 22px; line-height: 1.6; }
    .kicker { display: inline-block; padding: 6px 10px; border: 1px solid rgba(255,255,255,0.12); border-radius: 999px; font-size: 12px; color: var(--muted); margin-bottom: 14px; }
    code, .mono { font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace; font-size: 0.95em; }
    .footer { color: var(--muted); font-size: 14px; margin-top: 28px; }
  </style>
</head>
<body>
  <main class="container">
    <section class="card">
      <div class="kicker">README</div>
      <h1>📚 Bookverse</h1>
      <p class="tagline">An online bookstore web application powered by Spring Boot, React, and MySQL.</p>

      <p>Bookverse is an <strong>online bookstore web application</strong> built with a modern full‑stack architecture. It provides a smooth browsing and purchasing experience for users while maintaining a clean and maintainable backend.</p>
    </section>

    <section class="card">
      <h2>🚀 Tech Stack</h2>
      <h3>Frontend</h3>
      <ul>
        <li>React.js (hooks &amp; functional components)</li>
        <li>Axios (API calls)</li>
        <li>TailwindCSS / Bootstrap (if applied for styling)</li>
      </ul>
      <h3>Backend</h3>
      <ul>
        <li>Spring Boot (REST API)</li>
        <li>Swagger (API documentation)</li>
        <li>Postman (API testing)</li>
      </ul>
      <h3>Database</h3>
      <ul>
        <li>MySQL 8 (running inside Docker)</li>
        <li>Managed with MySQL Workbench / JetBrains DataGrip</li>
      </ul>
      <h3>Tools &amp; Utilities</h3>
      <ul>
        <li>Docker (containerization)</li>
        <li>Git &amp; GitHub (version control)</li>
        <li>Maven / Gradle (dependency management)</li>
      </ul>
    </section>

    <section class="card">
      <h2>✨ Features</h2>
      <ul>
        <li>User authentication and authorization</li>
        <li>Browse, search, and filter books</li>
        <li>Add to cart and place orders</li>
        <li>Admin panel for managing books and inventory</li>
        <li>API documentation with <span class="mono">Swagger UI</span></li>
        <li>REST API testing with <span class="mono">Postman</span></li>
      </ul>

      <h3>🔐 Authentication Roles</h3>
      <ul>
        <li><strong>Guest</strong>: Can sign up, verify email, reset password</li>
        <li><strong>Customer</strong>: Can sign in, verify email, reset password, log out</li>
        <li><strong>Staff</strong>: Same as customer + manage users (update, view list)</li>
        <li><strong>Admin</strong>: Full access (create, update, delete users, assign roles)</li>
      </ul>
    </section>

    <section class="card">
      <h2>📖 API Documentation</h2>
      <ul>
        <li>Swagger UI: <span class="mono">http://localhost:8080/swagger-ui/index.html</span></li>
        <li>Postman Collection: add a link if you export one</li>
      </ul>
    </section>

    <section class="card">
      <h2>🧪 Testing</h2>
      <ul>
        <li>Backend unit tests with <span class="mono">JUnit &amp; Mockito</span></li>
        <li>Manual API testing with <span class="mono">Postman</span></li>
        <li>UI testing with React Testing Library (optional)</li>
      </ul>
    </section>

    <section class="card footer">
      <p>🤝 Contributions, issues, and feature requests are welcome. Feel free to open a PR or submit an issue.</p>
      <p>📜 Licensed under the <strong>MIT License</strong> – see the LICENSE file for details.</p>
      <p>⚡ Developed with ❤️ using Spring Boot, React, and MySQL.</p>
    </section>
  </main>
</body>
</html>
