package org.example.hackathon_test.Place.Controller;


import lombok.AllArgsConstructor;
import org.example.hackathon_test.Place.Dto.GetPlaceDto;
import org.example.hackathon_test.Place.Dto.PlaceDto;
import org.example.hackathon_test.Place.Entity.Place;
import org.example.hackathon_test.Place.Service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/place")
public class PlaceController {
    private PlaceService placeService;

    @PostMapping
    public Long createPlace(@RequestBody PlaceDto placeDto){ return placeService.createPlace(placeDto); }

    @GetMapping("/{id}")
    public ResponseEntity<GetPlaceDto> getPlaceById(@PathVariable(name = "id") Long id){
        Optional<Place> placeOptional = placeService.getPlaceById(id);

        if(placeOptional.isPresent()){
            GetPlaceDto dto = GetPlaceDto.from(placeOptional.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}
