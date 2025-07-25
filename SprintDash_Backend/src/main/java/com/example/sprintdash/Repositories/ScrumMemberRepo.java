package com.example.sprintdash.Repositories;

import com.example.sprintdash.Models.ScrumMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ScrumMemberRepo extends JpaRepository<ScrumMember, Long> {
    Optional<ScrumMember> findByEmail(String email);
}
