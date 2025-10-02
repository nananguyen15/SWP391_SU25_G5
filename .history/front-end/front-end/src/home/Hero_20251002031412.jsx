import React, { useState } from "react";
import { Container } from "react-bootstrap";
import { FaChevronLeft, FaChevronRight } from "react-icons/fa";

function Header() {
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
    <div style={{ backgroundColor: "#e9dfd3", paddingTop: "80px" }}>
      <Container fluid className="py-5">
        <div className="row align-items-center">
          {/* Left Content */}
          <div className="col-lg-5 col-md-12 mb-4 mb-lg-0">
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
          <div className="col-lg-7 col-md-12 position-relative">
            <div className="d-flex justify-content-center align-items-end gap-4 position-relative">
              {/* Previous Arrow */}
              <button
                onClick={prevSlide}
                className="position-absolute start-0 top-50 translate-middle-y btn"
                style={{
                  backgroundColor: "rgba(139, 107, 76, 0.8)",
                  color: "white",
                  border: "none",
                  width: "40px",
                  height: "40px",
                  borderRadius: "50%",
                  zIndex: 10,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <FaChevronLeft />
              </button>

              {/* Book Cards */}
              {getVisibleBooks().map((book, index) => {
                const isCenter = index === 1;
                return (
                  <div
                    key={`${book.id}-${index}`}
                    className="text-center"
                    style={{
                      transform: isCenter
                        ? "translateY(20px) scale(1)"
                        : "scale(0.9)",
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
                          boxShadow: "0 10px 30px rgba(0,0,0,0.2)",
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
                className="position-absolute end-0 top-50 translate-middle-y btn"
                style={{
                  backgroundColor: "rgba(139, 107, 76, 0.8)",
                  color: "white",
                  border: "none",
                  width: "40px",
                  height: "40px",
                  borderRadius: "50%",
                  zIndex: 10,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <FaChevronRight />
              </button>
            </div>

            {/* Slide Indicators */}
            <div
              className="d-flex justify-content-center gap-2 mt-4"
              style={{ marginBottom: "2rem" }}
            >
              {books.map((_, index) => (
                <button
                  key={index}
                  onClick={() => setCurrentSlide(index)}
                  style={{
                    width: currentSlide === index ? "30px" : "10px",
                    height: "10px",
                    borderRadius: "5px",
                    border: "none",
                    backgroundColor:
                      currentSlide === index
                        ? "#8B6B4C"
                        : "rgba(139, 107, 76, 0.3)",
                    transition: "all 0.3s ease",
                    cursor: "pointer",
                  }}
                />
              ))}
            </div>
          </div>
        </div>
      </Container>
    </div>
  );
}

export default Hero;