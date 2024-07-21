package med.voll.api.controller;

import lombok.With;
import med.voll.api.domain.consultas.*;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.tools.CommonEntityCreation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static med.voll.api.tools.CommonEntityCreation.createMedico;
import static med.voll.api.tools.CommonEntityCreation.createPaciente;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ConsultasControllerTest {

    // MockMvc is a class that allows us to test the controllers
    // it simulates the HTTP requests
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RequestConsultaDTO> agendaJson;

    @Autowired
    private JacksonTester<CancelamentoConsultaDTO> cancelamentoJson;


    @MockBean
    private AgendaConsultasService agendaConsultasService;

    @Test
    @DisplayName("Should return 401 when trying to schedule a consultation because of missing token")
    void agendarConsulta401NoAuth() throws Exception {
        mvc.perform(post("/consultas/agendar"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Should return 400 because of missing data")
    @WithMockUser
    void agendarConsulta400() throws Exception {
        mvc.perform(post("/consultas/agendar"))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Should return 201 when scheduling a consultation for cardiologia") // we just testing with mocks here
    @WithMockUser
    void agendarConsultaCardiologia201() throws Exception {
        var nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        var paciente = CommonEntityCreation.createPaciente();
        var consultaRequestDTO = new RequestConsultaDTO(1L, 1L, nextMonday, Especialidade.CARDIOLOGIA);
        var consulta = new Consulta(1L, createMedico(), createPaciente(), nextMonday, StatusConsultaEnum.AGENDADA, null);

        when(agendaConsultasService.agendarConsulta(any(RequestConsultaDTO.class))).thenReturn(consulta);

        var response = mvc.perform(
                post("/consultas/agendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendaJson.write(
                                consultaRequestDTO
                        ).getJson()))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        var expected = agendaJson.write(consultaRequestDTO).getJson();
        var actual = response.getContentAsString();

        assertEquals(expected, actual);
    }
}