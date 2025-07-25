package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.ScrumMemberKpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScrumMemberKpiRepo extends JpaRepository<ScrumMemberKpi, Long> {
    Optional<ScrumMemberKpi> findById(Long id);
    @Query("SELECT k FROM ScrumMemberKpi k WHERE k.scrumMember.email = :email")
    ScrumMemberKpi findByScrumMemberEmail(String email);
}
