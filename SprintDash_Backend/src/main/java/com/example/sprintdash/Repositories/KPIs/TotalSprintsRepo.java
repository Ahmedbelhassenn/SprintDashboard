package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.TotalSprints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface TotalSprintsRepo extends JpaRepository<TotalSprints, Long> {
    Optional<TotalSprints> findByPeriod(String period);
}
