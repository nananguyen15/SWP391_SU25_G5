import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import "./index.css";
import Navbar from "./home/Navbar";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Navbar />
    
  </StrictMode>
);
