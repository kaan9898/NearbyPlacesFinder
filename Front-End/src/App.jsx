import './App.css'
import React, { useState } from "react";
import axios from "axios";

function App() {
  const [lat, setLat] = useState("");
  const [lng, setLng] = useState("");
  const [radius, setRadius] = useState("");
  const [places, setPlaces] = useState([]);
  const apiKey = import.meta.env.VITE_REACT_APP_GOOGLE_API_KEY;
  console.log("API KEY:", apiKey);
  const handleSearch = async () => {
    try {
      const res = await axios.get(`http://localhost:8070/api/places`, {
        params: { lat, lng, radius },
      });
      setPlaces(res.data.results);
    } catch (error) {
      console.error("Error fetching places:", error);
    }
  };
