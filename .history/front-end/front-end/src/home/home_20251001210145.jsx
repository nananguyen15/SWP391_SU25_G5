import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import bookLogo from "../assets/book.png";

function BrandBookVerse() {
  return (
    <>
      <Navbar className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="#home" style={{ fontFamily: "Parisienne", fontSize: "30px", color: "#a95a00ff" }}>
            <img
              src={bookLogo}
              width="30"
              height="30"
              className="d-inline-block align-top"
              alt="BookVerse logo"
            />{" "}
            BookVerse
          </Navbar.Brand>
        </Container>
      </Navbar>
    </>
  );
}

export default BrandBookVerse;
