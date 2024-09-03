package org.example.hackathon_test.Place.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.Place.Dto.PlaceDto;
import org.example.hackathon_test.Review.Entity.Review;
import org.example.hackathon_test.Review.Repository.ReviewRepository;
import org.example.hackathon_test.Place.Entity.Place;
import org.example.hackathon_test.Place.Repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long createPlace(PlaceDto placeDto) {
        Optional<Place> placeOptional = placeRepository.findByAddress(placeDto.getAddress());
        if (placeOptional.isPresent()) return placeOptional.get().getId();

        Place place = new Place();

        place.setPlaceName(placeDto.getPlaceName());
        place.setAddress(placeDto.getAddress());
        place.setStarAvg(0.0);

        placeRepository.save(place);

        return place.getId();
    }

    @Transactional
    public Optional<Place> getPlaceById(Long id){
        Optional<Place> placeOptional = placeRepository.findById(id);
        List<Review> reviewList = reviewRepository.findByPlaceId(id);
        Place place = placeOptional.get();

        if(!reviewList.isEmpty()) {
            double starAvg = reviewList.stream().mapToDouble(Review::getStarRating).sum() / reviewList.size();

            place.setStarAvg(starAvg);
        }
        placeRepository.save(place);

        return placeRepository.findById(id);
    }
}
