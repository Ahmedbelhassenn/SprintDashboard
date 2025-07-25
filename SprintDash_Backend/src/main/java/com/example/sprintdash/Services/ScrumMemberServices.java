package com.example.sprintdash.Services;

import com.example.sprintdash.Models.ScrumMember;

import java.util.List;
import java.util.Optional;

public interface ScrumMemberServices {
    List<ScrumMember> getAllScrumMembers();
    Optional<ScrumMember> getScrumMemberByEmail(String email);
    void updateScrumMembers();
    ScrumMember signup(ScrumMember member);
    ScrumMember login(String email, String password);
}
