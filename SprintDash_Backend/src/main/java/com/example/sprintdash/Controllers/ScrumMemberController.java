package com.example.sprintdash.Controllers;

import com.example.sprintdash.Models.ScrumMember;
import com.example.sprintdash.Services.ScrumMemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/member")
public class ScrumMemberController {
    @Autowired
    ScrumMemberServices scrumMemberServices;


    @GetMapping("/all")
    public List<ScrumMember> getAllScrumMembers() {
        return scrumMemberServices.getAllScrumMembers();
    }
    @GetMapping("/{email}")
    public Optional<ScrumMember> getScrumMemberByEmail(@PathVariable String email){
        return scrumMemberServices.getScrumMemberByEmail(email);
    }

    @PutMapping("/update")
    public void updateScrumMembers(){
        scrumMemberServices.updateScrumMembers();
    }
    @PostMapping("/signup")
    public ScrumMember signup(@RequestBody ScrumMember member) {
        return scrumMemberServices.signup(member);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {
            ScrumMember member = scrumMemberServices.login(email, password);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
