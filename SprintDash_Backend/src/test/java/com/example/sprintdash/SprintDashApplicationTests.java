package com.example.sprintdash;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {FeignAutoConfiguration.class})
@SpringBootTest
class SprintDashApplicationTests {

    @Test
    void contextLoads() {
    }

}
