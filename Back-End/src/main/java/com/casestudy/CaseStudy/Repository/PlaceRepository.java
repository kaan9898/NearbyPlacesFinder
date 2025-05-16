package com.casestudy.CaseStudy.Repository;

import com.casestudy.CaseStudy.Domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    Optional<Place> findByLatitudeAndLongitudeAndRadius(double lat, double lng, int radius);
}
