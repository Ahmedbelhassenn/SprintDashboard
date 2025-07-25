package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.TotalTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface TotalTicketsRepo extends JpaRepository<TotalTickets, Long> {
    Optional<TotalTickets> findByPeriod(String period);
}
