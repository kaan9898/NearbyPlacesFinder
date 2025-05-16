package com.casestudy.CaseStudy.Business.Congrete;

import com.casestudy.CaseStudy.Business.Abstract.PlaceManager;
import com.casestudy.CaseStudy.Domain.Place;
import com.casestudy.CaseStudy.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PlaceService implements PlaceManager {
    @Value("google.api.key")
    private String googleApiKey;
    private final PlaceRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public String getNearbyPlaces(double lat, double lng, int radius) {
        Optional<Place> cached = repository.findByLatitudeAndLongitudeAndRadius(lat, lng, radius);
        if (cached.isPresent()) {
            return cached.get().getResponseJson();
        }

        String url = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&key=%s",
                lat, lng, radius, googleApiKey
        );

        String response = restTemplate.getForObject(url, String.class);

        Place place = new Place();
        place.setLatitude(lat);
        place.setLongitude(lng);
        place.setRadius(radius);
        place.setResponseJson(response);
        place.setTimestamp(LocalDateTime.now());

        repository.save(place);

        return response;
    }
}
