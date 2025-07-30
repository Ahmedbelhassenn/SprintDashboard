package com.example.sprintdash;

import com.example.sprintdash.Models.KPIs.Velocity.VelocityAvg;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.Velocity.VelocityAvgRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.ServicesImplementations.KPIs.Velocity.VelocityAvgServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")

public class VelocityAvgServicesTest {
    @Mock
    VelocityAvgRepo velocityAvgRepo;
    @Mock
    private SprintRepo sprintRepo;

    @InjectMocks
    private VelocityAvgServicesImpl velocityAvgServices;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateVelocityAvg() {
        // Préparation des données de test
        Sprint sprint1 = new Sprint();
        sprint1.setState("closed");
        sprint1.setCompletedStoryPoints(10L);

        Sprint sprint2 = new Sprint();
        sprint2.setState("closed");
        sprint2.setCompletedStoryPoints(20L);

        Sprint sprint3 = new Sprint();
        sprint3.setState("open"); // doit être ignoré

        List<Sprint> sprints = Arrays.asList(sprint1, sprint2, sprint3);
        when(sprintRepo.findAll()).thenReturn(sprints);

        VelocityAvg velocityAvg = new VelocityAvg();
        velocityAvg.setPeriod("all");
        when(velocityAvgRepo.findByPeriod("all")).thenReturn(Optional.of(velocityAvg));

        // Appel de la méthode à tester
        velocityAvgServices.calculateVelocityAvg();

        // Capture de l'objet sauvegardé
        ArgumentCaptor<VelocityAvg> captor = ArgumentCaptor.forClass(VelocityAvg.class);
        verify(velocityAvgRepo).save(captor.capture());

        // Vérification que la moyenne est correcte: (10 + 20) / 2 = 15.0
        assertEquals(15.0, captor.getValue().getVelocityAvg());
    }
}