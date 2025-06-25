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

public class TestService {

     private EvaluacionRepository evaluacionRepository;
    private EvaluacionService evaluacionService;

    @BeforeEach
    void setUp() {
        evaluacionRepository = mock(EvaluacionRepository.class);
        evaluacionService = new EvaluacionService(evaluacionRepository);
    }

    //test crear evaluacion
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

    //test obtener todas las evaluaciones 
    @Test
    void testObtenerTodas() {
        List<Evaluacion> lista = Arrays.asList(new Evaluacion(), new Evaluacion());
        when(evaluacionRepository.findAll()).thenReturn(lista);

        List<Evaluacion> resultado = evaluacionService.obtenerTodasLasEvaluaciones();

        assertEquals(2, resultado.size());
        verify(evaluacionRepository).findAll();
    }

    // test para obtener evaluacion por id si esque existe 
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

    // buscamos una evaluacion que no existe, el sistema no se cae 
    //responde correctamente indicando que no encontró nada.
    @Test
    void testObtenerPorIdNoExiste() {
        when(evaluacionRepository.findById("99")).thenReturn(Optional.empty());

        Optional<Evaluacion> resultado = evaluacionService.obtenerEvaluacionPorId("99");

        assertFalse(resultado.isPresent());
    }
    //test para actualizar y guardar cambio de evaluacion
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




}
