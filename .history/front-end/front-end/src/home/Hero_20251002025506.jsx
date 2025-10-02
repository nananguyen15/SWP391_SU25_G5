import React from "react";

const cover1 = "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=400&q=80";
const cover2 = "https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=400&q=80";
const cover3 = "https://images.unsplash.com/photo-1507842217343-583bb7270b66?auto=format&fit=crop&w=400&q=80";

export default function Hero() {
  return (
    <section className="home-hero">
      <div className="container-fluid" style={{ paddingTop: 90 }}>
        <div className="row align-items-center">
          <div className="col-md-6">
            <h1 className="hero-title">
              Find Your
              <br />
              Next Book
            </h1>
            <p className="hero-sub">
              Our most popular and trending On.Book perfect
              <br />
              Not sure what to read now next reading mood Perfectly.
            </p>
            <button className="btn explore-btn">Explore Now</button>
          </div>

          <div className="col-md-6 d-none d-md-flex justify-content-end">
            <div className="book-stack" aria-hidden>
              <figure className="book b1">
                <img src={cover1} alt="cover 1" />
                <figcaption>The Stranger<br/><small>Albert Camus</small></figcaption>
              </figure>

              <figure className="book b2">
                <img src={cover2} alt="cover 2" />
                <figcaption>Der Process<br/><small>Franz Kafka</small></figcaption>
              </figure>

              <figure className="book b3">
                <img src={cover3} alt="cover 3" />
                <figcaption>Ante<br/><small>Clive James</small></figcaption>
              </figure>
            </div>
          </div>
        </div>
      </div>

      <style jsx>{`
        .home-hero {
          background: #e9dfd3;
          padding-bottom: 60px;
        }

        .hero-title {
          font-family: 'Playfair Display', Georgia, serif;
          font-weight: 700;
          color: #111;
          margin: 0 0 18px 0;
          line-height: 0.92;
          font-size: clamp(42px, 7vw, 110px);
        }

        .hero-sub {
          color: #333;
          margin-bottom: 24px;
          font-size: 16px;
        }

        .explore-btn {
          background: #111;
          color: #fff;
          border: none;
          padding: 12px 26px;
          border-radius: 4px;
        }

        .book-stack {
          width: 520px;
          height: 360px;
          position: relative;
        }

        .book {
          position: absolute;
          width: 180px;
          border-radius: 8px;
          overflow: hidden;
          box-shadow: 0 12px 30px rgba(17,17,17,0.18);
          background: white;
          text-align: center;
        }

        .book img {
          display: block;
          width: 100%;
          height: 260px;
          object-fit: cover;
        }

        .book figcaption {
          font-size: 14px;
          color: #111;
          padding: 8px 6px;
        }

        /* positions to mimic the example */
        .book.b1 {
          left: 0;
          top: 28px;
          transform: rotate(-6deg) scale(0.95);
          z-index: 1;
        }

        .book.b2 {
          left: 170px;
          top: 0;
          transform: rotate(0deg) scale(1.08);
          z-index: 2;
        }

        .book.b3 {
          left: 330px;
          top: 30px;
          transform: rotate(6deg) scale(0.98);
          z-index: 1;
        }

        /* responsive */
        @media (max-width: 991px) {
          .book-stack { display: none; }
          .home-hero { padding-top: 110px; }
        }
      `}</style>
    </section>
  )