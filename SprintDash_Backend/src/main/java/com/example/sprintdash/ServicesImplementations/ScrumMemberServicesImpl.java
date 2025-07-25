package com.example.sprintdash.ServicesImplementations;

import com.example.sprintdash.Models.ScrumMember;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Repositories.ScrumMemberRepo;
import com.example.sprintdash.Repositories.TicketRepo;
import com.example.sprintdash.Services.ScrumMemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class ScrumMemberServicesImpl implements ScrumMemberServices {
    @Autowired
    ScrumMemberRepo scrumMemberRepo;
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public List<ScrumMember> getAllScrumMembers() {
        return scrumMemberRepo.findAll();
    }

    public Optional<ScrumMember> getScrumMemberByEmail(String email) {
        return scrumMemberRepo.findByEmail(email);
    }
    public void updateScrumMembers(){
        List<Ticket> tickets = ticketRepo.findAll();
        for (Ticket ticket : tickets) {
            if(ticket.getAssignedMember()!=null){
                ScrumMember scrumMember=scrumMemberRepo.findByEmail(ticket.getAssignedMember())
                        .orElse(new ScrumMember());
                scrumMember.setEmail(ticket.getAssignedMember());
                scrumMemberRepo.save(scrumMember);
            }
        }
    }
    public ScrumMember signup(ScrumMember member) {
        Optional<ScrumMember> existingMember = scrumMemberRepo.findByEmail(member.getEmail());
        if (existingMember.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Already Exists");
        }
        String rawPassword = member.getPassword();
        if (!isPasswordStrong(rawPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weak password");
        }
        // Encode the password before saving
        member.setPassword(passwordEncoder.encode(rawPassword));
        // Save the new ScrumMember
        return scrumMemberRepo.save(member);
    }

    private boolean isPasswordStrong(String password) {
        // Example password strength check: at least 8 characters, contains letters and numbers
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*\\d.*");
    }
    public ScrumMember login(String email, String password) {
        ScrumMember member = scrumMemberRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

        // Compare the raw password with the stored hashed password
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return member;
    }
}
