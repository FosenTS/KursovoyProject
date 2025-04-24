package ru.raffalda.kursovoy_project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FacultyControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenGetFaculties_thenReturnFacultiesPage() throws Exception {
        mockMvc.perform(get("/faculties"))
                .andExpect(status().isOk())
                .andExpect(view().name("faculties"))
                .andExpect(model().attributeExists("faculties"));
    }

    @Test
    public void whenUnauthorizedAccessFaculties_thenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/faculties"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void whenNonAdminAccessFaculties_thenForbidden() throws Exception {
        mockMvc.perform(get("/faculties/new"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenAdminAccessNewFaculty_thenSuccess() throws Exception {
        mockMvc.perform(get("/faculties/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("faculty-form"))
                .andExpect(model().attributeExists("faculty"));
    }
} 