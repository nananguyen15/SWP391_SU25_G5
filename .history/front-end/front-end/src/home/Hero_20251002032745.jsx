import React, { useState } from "react";
import { Container } from "react-bootstrap";
import { FaChevronLeft, FaChevronRight } from "react-icons/fa";

function Hero() {
  const [currentSlide, setCurrentSlide] = useState(0);

  const books = [
    {
      id: 1,
      title: "The Stranger",
      author: "Albert Camus",
      image: "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400",
    },
    {
      id: 2,
      title: "Der Process",
      author: "Franz Kafka",
      image: "https://images.unsplash.com/photo-1512820790803-83ca734da794?w=400",
    },
    {
      id: 3,
      title: "Dante",
      author: "Clive James",
      image: "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?w=400",
    },
  ];

  const nextSlide = () => {
    setCurrentSlide((prev) => (prev + 1) % books.length);
  };

  const prevSlide = () => {
    setCurrentSlide((prev) => (prev - 1 + books.length) % books.length);
  };

  const getVisibleBooks = () => {
    const visible = [];
    for (let i = 0; i < 3; i++) {
      visible.push(books[(currentSlide + i) % books.length]);
    }
    return visible;
  };

  return (
    <div style={{ backgroundColor: "#e9dfd3", minHeight: "100vh" }}>
      <Container fluid className="py-5" style={{ paddingTop: "100px" }}>
        <div className="row align-items-center" style={{ minHeight: "80vh" }}>
          {/* Left Content */}
          <div className="col-lg-5 col-md-12 mb-4 mb-lg-0 px-5">
            <h1
              style={{
                fontFamily: "LovelyHome",
                fontSize: "clamp(2.5rem, 5vw, 4rem)",
                color: "#2c1810",
                fontWeight: "bold",
                marginBottom: "1.5rem",
              }}
            >
              Find Your Next Book
            </h1>
            <p
              style={{
                fontSize: "1.1rem",
                color: "#5c4a3d",
                marginBottom: "2rem",
                lineHeight: "1.6",
              }}
            >
              Our most popular and trending <strong>BookVerse</strong> perfect.
              Not sure what to read now next reading mood Perfectly.
            </p>
            <button
              className="btn"
              style={{
                backgroundColor: "#8B6B4C",
                color: "white",
                border: "none",
                padding: "12px 40px",
                fontSize: "1rem",
                borderRadius: "6px",
                fontWeight: "500",
                transition: "all 0.3s ease",
              }}
              onMouseEnter={(e) => {
                e.target.style.backgroundColor = "#6d5438";
                e.target.style.transform = "translateY(-2px)";
              }}
              onMouseLeave={(e) => {
                e.target.style.backgroundColor = "#8B6B4C";
                e.target.style.transform = "translateY(0)";
              }}
            >
              Get Started
            </button>
          </div>

          {/* Right Books Slideshow */}
          <div className="col-lg-7 col-md-12 position-relative px-5">
            <div
              className="d-flex justify-content-center align-items-center gap-4 position-relative"
              style={{ padding: "0 60px" }}
            >
              {/* Previous Arrow */}
              <button
                onClick={prevSlide}
                className="position-absolute start-0 btn p-0"
                style={{
                  backgroundColor: "transparent",
                  color: "#8B6B4C",
                  border: "none",
                  width: "40px",
                  height: "40px",
                  zIndex: 10,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  cursor: "pointer",
                  transition: "color 0.3s ease",
                }}
                onMouseEnter={(e) => (e.target.style.color = "#6d5438")}
                onMouseLeave={(e) => (e.target.style.color = "#8B6B4C")}
              >
                <FaChevronLeft size={24} />
              </button>

              {/* Book Cards */}
              {getVisibleBooks().map((book, index) => {
                const isCenter = index === 1;
                return (
                  <div
                    key={`${book.id}-${index}`}
                    className="text-center"
                    style={{
                      transition: "all 0.5s ease",
                    }}
                  >
                    {/* Book Cover with Arch Shape */}
                    <div
                      style={{
                        position: "relative",
                        width: "200px",
                        height: "280px",
                        margin: "0 auto 1rem",
                      }}
                    >
                      <div
                        style={{
                          width: "100%",
                          height: "100%",
                          borderRadius: isCenter
                            ? "0 0 100px 100px"
                            : "100px 100px 0 0",
                          overflow: "hidden",
                          boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
                          transform: isCenter ? "rotate(180deg)" : "none",
                        }}
                      >
                        <img
                          src={book.image}
                          alt={book.title}
                          style={{
                            width: "100%",
                            height: "100%",
                            objectFit: "cover",
                            transform: isCenter ? "rotate(180deg)" : "none",
                          }}
                        />
                      </div>
                    </div>

                    {/* Book Info */}
                    <div style={{ maxWidth: "200px", margin: "0 auto" }}>
                      <h5
                        style={{
                          fontSize: "1.1rem",
                          fontWeight: "bold",
                          color: "#2c1810",
                          marginBottom: "0.25rem",
                        }}
                      >
                        {book.title}
                      </h5>
                      <p
                        style={{
                          fontSize: "0.95rem",
                          color: "#5c4a3d",
                          margin: 0,
                        }}
                      >
                        {book.author}
                      </p>
                    </div>
                  </div>
                );
              })}

              {/* Next Arrow */}
              <button
                onClick={nextSlide}
                className="position-absolute end-0 btn p-0"
                style={{
                  backgroundColor: "transparent",
                  color: "#8B6B4C",
                  border: "none",
                  width: "40px",
                  height: "40px",
                  zIndex: 10,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  cursor: "pointer",
                  transition: "color 0.3s ease",
                }}
                onMouseEnter={(e) => (e.target.style.color = "#6d5438")}
                onMouseLeave={(e) => (e.target.style.color = "#8B6B4C")}
              >
                <FaChevronRight size={24} />
              </button>
            </div>
          </div>
        </div>
      </Container>
    </div>
  );
}

export default Hero;