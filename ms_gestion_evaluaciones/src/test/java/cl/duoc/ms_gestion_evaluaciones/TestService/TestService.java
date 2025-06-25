package cl.duoc.ms_gestion_evaluaciones.TestService;

import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.repository.EvaluacionRepository;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestService {

     private EvaluacionRepository evaluacionRepository;
    private EvaluacionService evaluacionService;

    @BeforeEach
    void setUp() {
        evaluacionRepository = mock(EvaluacionRepository.class);
        evaluacionService = new EvaluacionService(evaluacionRepository);
    }
    @Test
    void testCrearEvaluacion() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombreEstudiante("Prueba");

        when(evaluacionRepository.save(evaluacion)).thenReturn(evaluacion);

        Evaluacion resultado = evaluacionService.crearEvaluacion(evaluacion);

        assertNotNull(resultado);
        assertEquals("Prueba", resultado.getNombreEstudiante());
        verify(evaluacionRepository).save(evaluacion);
    }





}
