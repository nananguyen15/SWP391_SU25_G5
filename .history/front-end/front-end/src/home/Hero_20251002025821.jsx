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
              Find
              <br />
              Your
              <br />
              Next
              <br />
              Book
            </h1>
            <p className="hero-sub">
              Our most popular and trending On.Book perfect
              <br />
              Not sure what to read now next reading mood Perfectly.
            </p>
            <button className="btn explore-btn">Explore Now</button>
          </div>

          <div className="col-md-6 d-none d-md-flex justify-content-center align-items-center">
            <div className="book-stack">
              <div className="book-card card1">
                <div className="book-window">
                  <img src={cover1} alt="The Stranger" />
                </div>
                <div className="book-info">
                  <h5>The Stranger</h5>
                  <p>Albert Camus</p>
                </div>
              </div>

              <div className="book-card card2">
                <div className="book-window">
                  <img src={cover2} alt="Der Process" />
                </div>
                <div className="book-info">
                  <h5>Der Process</h5>
                  <p>Franz Kafka</p>
                </div>
              </div>

              <div className="book-card card3">
                <div className="book-window">
                  <img src={cover3} alt="Ante" />
                </div>
                <div className="book-info">
                  <h5>Ante</h5>
                  <p>Clive James</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <style jsx>{`
        .home-hero {
          background: #e9dfd3;
          padding-bottom: 80px;
          min-height: 100vh;
        }

        .hero-title {
          font-family: 'Georgia', serif;
          font-weight: 700;
          color: #000;
          margin: 0 0 24px 0;
          line-height: 1;
          font-size: clamp(60px, 8vw, 120px);
          letter-spacing: -2px;
        }

        .hero-sub {
          color: #333;
          margin-bottom: 28px;
          font-size: 15px;
          line-height: 1.6;
        }

        .explore-btn {
          background: #000;
          color: #fff;
          border: none;
          padding: 14px 32px;
          border-radius: 6px;
          font-weight: 500;
          transition: all 0.3s ease;
        }

        .explore-btn:hover {
          background: #333;
          transform: translateY(-2px);
        }

        .book-stack {
          position: relative;
          width: 550px;
          height: 450px;
        }

        .book-card {
          position: absolute;
          width: 280px;
          background: white;
          border-radius: 20px;
          box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
          overflow: hidden;
          transition: transform 0.3s ease;
        }

        .book-card:hover {
          transform: translateY(-8px) !important;
        }

        .book-window {
          width: 100%;
          height: 320px;
          border-radius: 20px 20px 0 0;
          overflow: hidden;
          position: relative;
        }

        .book-window::before {
          content: '';
          position: absolute;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          background: radial-gradient(ellipse at center, transparent 40%, rgba(0,0,0,0.1) 100%);
          z-index: 1;
        }

        .book-window img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          display: block;
        }

        .book-info {
          padding: 16px 20px;
          text-align: center;
          background: white;
        }

        .book-info h5 {
          margin: 0 0 4px 0;
          font-size: 16px;
          font-weight: 600;
          color: #000;
        }

        .book-info p {
          margin: 0;
          font-size: 13px;
          color: #666;
        }

        /* Card positions - xếp chồng 3 lớp */
        .card1 {
          left: -20px;
          top: 80px;
          transform: rotate(-8deg);
          z-index: 1;
        }

        .card2 {
          left: 140px;
          top: 30px;
          transform: rotate(2deg);
          z-index: 3;
        }

        .card3 {
          right: -20px;
          top: 90px;
          transform: rotate(8deg);
          z-index: 2;
        }

        /* Responsive */
        @media (max-width: 991px) {
          .home-hero {
            padding-top: 100px;
            min-height: auto;
          }
          .hero-title {
            font-size: 52px;
          }
        }
      `}</style>
    </section>
  );
}