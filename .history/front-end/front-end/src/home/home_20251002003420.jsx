import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import bookLogo from "../assets/img/book.png";
import NavDropdown from "react-bootstrap/NavDropdown";

function BrandBookVerse() {
  return (
    <>
      <Navbar className="bg-body-tertiary w-100" fixed="top">
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
          <Nav.Link href="#books">Books</Nav.Link>
          <Nav.Link href="#series">Series</Nav.Link>
          <NavDropdown title="Categories" id="navbarScrollingDropdown">
            <NavDropdown.Item href="#action1">Action 1</NavDropdown.Item>
            <NavDropdown.Item href="#action2">Action 2</NavDropdown.Item>
            <NavDropdown.Item href="#action3">Action 3</NavDropdown.Item>
          </NavDropdown>
        </Nav>
          <InputGroup className="mb-3">
            <FormControl
              type="search"
              placeholder="Search"
              aria-label="Search"
              aria-describedby="basic-addon2" 
              

      </Navbar>
    </>
  );
}

export default BrandBookVerse;
