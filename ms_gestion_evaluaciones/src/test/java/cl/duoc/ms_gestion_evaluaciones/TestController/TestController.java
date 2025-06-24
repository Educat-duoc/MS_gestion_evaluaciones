package cl.duoc.ms_gestion_evaluaciones.TestController;
import cl.duoc.ms_gestion_evaluaciones.controller.EvaluacionController;
import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EvaluacionController.class)
public class TestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluacionService evaluacionService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testEstadoServicio() throws Exception {
        mockMvc.perform(get("/Evaluacion/Evaluacion/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("El MS esta activo"));
    }
    //test de crear evaluacion
    @Test
    void testCrearEvaluacion() throws Exception {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId("1");
        evaluacion.setNombreEstudiante("Evaluación prueba");

        when(evaluacionService.crearEvaluacion(any(Evaluacion.class))).thenReturn(evaluacion);

        mockMvc.perform(post("/Evaluacion/CrearEvaluacion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evaluacion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("nombre").value("Evaluación pruebaprueba"));
    }
    //Prueba se obtienen todas las evaluaciones
    @Test
    void testObtenerTodas() throws Exception {
        Evaluacion e1 = new Evaluacion();
        e1.setId("1");
        e1.setNombreEstudiante("Eval 1");

        Evaluacion e2 = new Evaluacion();
        e2.setId("2");
        e2.setNombreEstudiante("Eval 2");

        when(evaluacionService.obtenerTodasLasEvaluaciones()).thenReturn(Arrays.asList(e1, e2));

        mockMvc.perform(get("/Evaluacion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    
// Prueba que se puede obtener una evaluación por ID cuando existe
     @Test
    void testObtenerPorId_Existe() throws Exception {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId("1");
        evaluacion.setNombreEstudiante("Eval");

        when(evaluacionService.obtenerEvaluacionPorId("1")).thenReturn(Optional.of(evaluacion));

        mockMvc.perform(get("/Evaluacion/ObtenerEvaluacion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

}



