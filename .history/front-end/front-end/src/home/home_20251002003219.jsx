import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import bookLogo from "../assets/img/book.png";
import NavDropdown from "react-bootstrap/NavDropdown";
import { InputGroup, FormControl } from "react-bootstrap";

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
            alt="Logo BookVerse"
          />{" "}
          BookVerse
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="#books">Sách</Nav.Link>
          <Nav.Link href="#series">Bộ sách</Nav.Link>
          <NavDropdown title="Thể loại" id="navbarScrollingDropdown">
            <NavDropdown.Item href="#action1">Hành động 1</NavDropdown.Item>
            <NavDropdown.Item href="#action2">Hành động 2</NavDropdown.Item>
            <NavDropdown.Item href="#action3">Hành động 3</NavDropdown.Item>
          </NavDropdown>
        </Nav>
        <InputGroup className="mb-3">
          <FormControl
            type="search"
            placeholder="Tìm kiếm"
            aria-label="Tìm kiếm"
            aria-describedby="basic-addon2"
          />
        </InputGroup>
      </Navbar>
    </>
  );
}

export default BrandBookVerse;

export default BrandBookVerse;
