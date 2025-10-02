import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import bookLogo from "../assets/img/book.png";

function BrandBookVerse() {
  return (
    <>
      <Navbar className="bg-body-tertiary w-100" fixed="top">
        <Container>
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
            className="d-inline-block align-top"
            alt="BookVerse logo"
          />{" "}
          BookVerse
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="#home">Home</Nav.Link>
          <Nav.Link href="#features">Features</Nav.Link>
          <Nav.Link href="#pricing">Pricing</Nav.Link>
        </Nav>
      </Navbar>
    </>
  );
}

export default BrandBookVerse;
