import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import bookLogo from "../assets/book.png";

function BrandBookVerse() {
  return (
    <>
      <Navbar className="bg-body-tertiary w-100" fixed="top">
        <Navbar.Brand
          href="#home"
          style={{
            fontFamily: "Parisienne",
            fontSize: "30px",
            color: "#8B6B4C",
            fontWeight: "800",
          }}
          className="d-flex align-items-center"
        >
          <img
            src={bookLogo}
            width="30"
            height="30"
            className="d-inline-block align-top"
            alt="BookVerse logo"
          />{" "}
          BookVerse
        </Navbar.Brand>
      </Navbar>
    </>
  );
}

export default BrandBookVerse;
