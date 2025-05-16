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

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Case Study</h2>
      <input
        placeholder="Latitude"
        value={lat}
        onChange={(e) => setLat(e.target.value)}
      />
      <input
        placeholder="Longitude"
        value={lng}
        onChange={(e) => setLng(e.target.value)}
      />
      <input
        placeholder="Radius (meters)"
        value={radius}
        onChange={(e) => setRadius(e.target.value)}
      />
      <button onClick={handleSearch}>Search</button>

      <ul>
        {places.map((place) => (
          <li key={place.place_id}>{place.name}</li>
        ))}
      </ul>

      <div style={{ height: "500px", marginTop: "1rem" }}>
        <iframe
          width="100%"
          height="100%"
          src={`https://www.google.com/maps/embed/v1/place?key=${apiKey}&q=${lat},${lng}`}
          allowFullScreen
          title="map"
        ></iframe>
      </div>
    </div>
  );
}

export default App;
