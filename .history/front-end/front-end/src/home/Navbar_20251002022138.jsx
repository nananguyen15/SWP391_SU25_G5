import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import bookLogo from "../assets/img/book.png";
import NavDropdown from "react-bootstrap/NavDropdown";
import { FaSearch } from "react-icons/fa";
import FormControl from "react-bootstrap/FormControl";
import { IoNotifications, IoCart } from "react-icons/io5";
import "../home/fonts.css";

function BrandBookVerse() {
  const [expanded, setExpanded] = useState(false);

  return (
    <Navbar 
      className="bg-white border-bottom" 
      fixed="top" 
      expand="md"
      expanded={expanded}
      onToggle={setExpanded}
    >
      <Container fluid className="px-3">
        {/* Brand */}
        <Navbar.Brand
          href="#home"
          style={{
            fontFamily: "LovelyHome",
            fontSize: "30px",
            color: "#8B6B4C",
            fontWeight: "1000",
          }}
          className="d-flex align-items-center"
        >
          <img
            src={bookLogo}
            width="30"
            height="30"
            className="me-2"
            alt="BookVerse logo"
          />
          BookVerse
        </Navbar.Brand>

        {/* Toggle button for mobile */}
        <Navbar.Toggle aria-controls="basic-navbar-nav" />

        <Navbar.Collapse id="basic-navbar-nav">
          {/* Navigation Links */}
          <Nav className="mx-auto">
            <Nav.Link href="#books" className="mx-3 nav-link-custom">Books</Nav.Link>
            <Nav.Link href="#categories" className="mx-3 nav-link-custom">Categories</Nav.Link>
            <Nav.Link href="#wishlist" className="mx-3 nav-link-custom">Wishlist</Nav.Link>
            <Nav.Link href="#blog" className="mx-3 nav-link-custom">Blog</Nav.Link>
            <Nav.Link href="#about" className="mx-3 nav-link-custom">About Us</Nav.Link>
          </Nav>

          {/* Right side controls */}
          <div className="d-flex align-items-center gap-3">
            {/* Search - Updated styling */}
            <div className="position-relative d-none d-md-block">
              <FormControl
                type="search"
                placeholder="Search book..."
                aria-label="Search"
                className="search-input"
                style={{ 
                  height: "38px",
                  width: "280px",
                  border: "none",
                  borderBottom: "2px solid #ddd",
                  borderRadius: "0",
                  backgroundColor: "transparent",
                  paddingRight: "40px"
                }}
                onFocus={(e) => e.target.style.borderBottomColor = "#8B6B4C"}
                onBlur={(e) => e.target.style.borderBottomColor = "#ddd"}
              />
              <FaSearch
                className="position-absolute"
                style={{
                  right: "12px",
                  top: "50%",
                  transform: "translateY(-50%)",
                  color: "#8B6B4C",
                  pointerEvents: "none",
                }}
                size={16}
              />
            </div>

            {/* Search icon only for small screens */}
            <button
              className="btn d-md-none p-0"
              style={{
                border: "none",
                backgroundColor: "transparent",
                color: "#8B6B4C",
                height: "38px",
                width: "38px"
              }}
            >
              <FaSearch size={16} />
            </button>

            {/* Cart Icon */}
            <button
              className="btn p-0"
              style={{
                border: "none",
                backgroundColor: "transparent",
                color: "#8B6B4C",
                height: "38px",
                width: "38px"
              }}
            >
              <IoCart size={24} />
            </button>

            {/* Sign In Button */}
            <button
              className="btn sign-in-btn"
              style={{
                height: "38px",
                minWidth: "80px",
                border: "none",
                color: "#333",
                backgroundColor: "transparent",
                fontSize: "14px",
                fontWeight: "500"
              }}
            >
              Sign In
            </button>
          </div>
        </Navbar.Collapse>
      </Container>

      <style jsx>{`
        .nav-link-custom {
          color: #333 !important;
          font-weight: 500 !important;
          text-decoration: none !important;
          position: relative !important;
          transition: all 0.3s ease !important;
        }
        
        .nav-link-custom:hover {
          color: #8B6B4C !important;
        }
        
        .nav-link-custom:hover::after {
          content: '';
          position: absolute;
          bottom: -2px;
          left: 0;
          right: 0;
          height: 2px;
          background-color: #8B6B4C;
          animation: underlineSlide 0.3s ease-in-out;
        }
        
        @keyframes underlineSlide {
          from {
            width: 0;
            left: 50%;
          }
          to {
            width: 100%;
            left: 0;
          }
        }
        
        .search-input:focus {
          outline: none !important;
          box-shadow: none !important;
        }
        
        .sign-in-btn:hover {
          color: #8B6B4C !important;
        }
        
        .navbar {
          box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
      `}</style>
    </Navbar>
  );
}

export default BrandBookVerse;