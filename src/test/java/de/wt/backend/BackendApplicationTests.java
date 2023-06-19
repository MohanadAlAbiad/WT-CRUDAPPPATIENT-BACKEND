package de.wt.backend;

import de.wt.backend.controller.ClientController;
import de.wt.backend.model.Patient;
import de.wt.backend.repository.PatientRepository;
import de.wt.backend.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BackendApplicationTests {
    private MockMvc mvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    private Patient patient = new Patient();

    @Test
    void contextLoads() {
    }

    @Test
    public void testPatientModel() {
        patient.setId(1L);
        patient.setName("Test");
        patient.setEmail("test@test.com");
        patient.setpNo("123456");
        patient.setGender("Male");

        assertEquals(1L, patient.getId());
        assertEquals("Test", patient.getName());
        assertEquals("test@test.com", patient.getEmail());
        assertEquals("123456", patient.getpNo());
        assertEquals("Male", patient.getGender());
    }

    @Test
    public void testAddPatient() {
        when(patientService.addPatient(patient)).thenReturn(patient);
        assertEquals(patient, patientService.addPatient(patient));
    }

    @Test
    public void testGetPatientById() {
        when(patientService.getPatientById(1L)).thenReturn(patient);
        assertEquals(patient, patientService.getPatientById(1L));
    }

    @Test
    public void testGetPatients() {
        List<Patient> patientList = new ArrayList<>();
        when(patientService.getPatients()).thenReturn(patientList);
        assertEquals(patientList, patientService.getPatients());
    }

    @Test
    public void testUpdatePatient() {
        when(patientService.updatePatient(patient)).thenReturn(patient);
        assertEquals(patient, patientService.updatePatient(patient));
    }

    @Test
    public void testHelloWorld() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(clientController).build();
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }
}

