package cl.duoc.ms_gestion_evaluaciones.TestService;

import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.repository.EvaluacionRepository;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EvaluacionServiceTest {

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

    @Test
    void testObtenerTodas() {
        List<Evaluacion> lista = Arrays.asList(new Evaluacion(), new Evaluacion());
        when(evaluacionRepository.findAll()).thenReturn(lista);

        List<Evaluacion> resultado = evaluacionService.obtenerTodasLasEvaluaciones();

        assertEquals(2, resultado.size());
        verify(evaluacionRepository).findAll();
    }

    @Test
    void testObtenerPorIdExiste() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId("1");

        when(evaluacionRepository.findById("1")).thenReturn(Optional.of(evaluacion));

        Optional<Evaluacion> resultado = evaluacionService.obtenerEvaluacionPorId("1");

        assertTrue(resultado.isPresent());
        assertEquals("1", resultado.get().getId());
        verify(evaluacionRepository).findById("1");
    }

    @Test
    void testObtenerPorIdNoExiste() {
        when(evaluacionRepository.findById("99")).thenReturn(Optional.empty());

        Optional<Evaluacion> resultado = evaluacionService.obtenerEvaluacionPorId("99");

        assertFalse(resultado.isPresent());
    }

    @Test
    void testActualizarEvaluacionExiste() {
        Evaluacion original = new Evaluacion();
        original.setId("1");

        Evaluacion actualizada = new Evaluacion();
        actualizada.setNombreEstudiante("Nuevo");

        when(evaluacionRepository.findById("1")).thenReturn(Optional.of(original));
        when(evaluacionRepository.save(any(Evaluacion.class))).thenReturn(actualizada);

        Evaluacion resultado = evaluacionService.actualizarEvaluacion("1", actualizada);

        assertEquals("Nuevo", resultado.getNombreEstudiante());
        verify(evaluacionRepository).save(any(Evaluacion.class));
    }

    @Test
    void testActualizarEvaluacionNoExiste() {
        Evaluacion actualizada = new Evaluacion();
        when(evaluacionRepository.findById("99")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                evaluacionService.actualizarEvaluacion("99", actualizada));
    }

    @Test
    void testEliminarEvaluacionExiste() {
    // Simula que la evaluacion con ID "1" existe
    when(evaluacionRepository.existsById("1")).thenReturn(true);
    doNothing().when(evaluacionRepository).deleteById("1");

    // Llama al metodo que estás probando
    evaluacionService.eliminarEvaluacion("1");

    // Verifica que deleteById fue llamado
    verify(evaluacionRepository).deleteById("1");
}

@Test
void testEliminarEvaluacionNoExiste() {
    // Simula que la evaluacion con ID "99" no existe
    when(evaluacionRepository.existsById("99")).thenReturn(false);

    // Verifica que lanza la excepcion
    assertThrows(IllegalArgumentException.class, () ->
            evaluacionService.eliminarEvaluacion("99"));
}
}
