package org.example.hackathon_test.Place.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Place.Entity.Place;
import org.example.hackathon_test.User.Entity.User;

import java.time.LocalDateTime;


@Getter
@Setter
public class GetPlaceDto {
    private Long id;
    private String placeName;
    private String address;
    private Double starAvg;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetPlaceDto from(Place place){
        GetPlaceDto getPlaceDto = new GetPlaceDto();

        getPlaceDto.setId(place.getId());
        getPlaceDto.setPlaceName(place.getPlaceName());
        getPlaceDto.setAddress(place.getAddress());
        getPlaceDto.setStarAvg(place.getStarAvg());
        getPlaceDto.setCreateDate(place.getCreateDate());
        getPlaceDto.setModifyDate(place.getModifyDate());

        return getPlaceDto;
    }
}
