package org.example.hackathon_test.Place.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Place.Entity.Place;

@Getter
@Setter
public class PlaceDto {
    private String placeName;
    private String address;

    public static PlaceDto from(Place place) {
        PlaceDto placeDto = new PlaceDto();

        placeDto.setPlaceName(place.getPlaceName());
        placeDto.setAddress(place.getAddress());

        return placeDto;
    }
}