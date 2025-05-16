package com.casestudy.CaseStudy.Controller;

import com.casestudy.CaseStudy.Business.Congrete.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<String> getNearbyPlaces(@RequestParam double lat,
                                                  @RequestParam double lng,
                                                  @RequestParam int radius) {
        return ResponseEntity.ok(placeService.getNearbyPlaces(lat, lng, radius));
    }
}
