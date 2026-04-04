package com.simon.wigellpadel.repository;

import com.simon.wigellpadel.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court,Long> {
}
