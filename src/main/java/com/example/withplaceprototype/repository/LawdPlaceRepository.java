package com.example.withplaceprototype.repository;

import com.example.withplaceprototype.entity.LawdPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface LawdPlaceRepository extends JpaRepository<LawdPlace, Entity> {
    LawdPlace findByLawdDongEquals(String name);
}
