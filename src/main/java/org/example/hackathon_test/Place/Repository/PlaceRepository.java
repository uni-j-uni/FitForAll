package org.example.hackathon_test.Place.Repository;

import org.example.hackathon_test.Place.Entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByAddress(String address);

}
