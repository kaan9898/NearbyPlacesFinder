package com.casestudy.CaseStudy.Business.Abstract;

public interface PlaceManager {
    String getNearbyPlaces(double lat, double lng, int radius);
}
