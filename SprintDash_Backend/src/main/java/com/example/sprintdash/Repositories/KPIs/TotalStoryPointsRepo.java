package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.TotalSprints;
import com.example.sprintdash.Models.KPIs.TotalStoryPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface TotalStoryPointsRepo extends JpaRepository<TotalStoryPoints, Long> {
    Optional<TotalStoryPoints> findByPeriod(String period);

}
