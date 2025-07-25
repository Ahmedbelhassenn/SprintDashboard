package com.example.sprintdash.Repositories;

import com.example.sprintdash.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketId(String id);
}
