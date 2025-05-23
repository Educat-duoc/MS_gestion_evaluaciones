package cl.duoc.ms_gestion_evaluaciones.service;

import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.repository.EvaluacionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service 
@RequiredArgsConstructor 
@Slf4j 
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    @Transactional(readOnly = true)
    public Optional<Evaluacion> obtenerEvaluacionPorId(Long id) {
        log.info("Buscando evaluación con ID: {}", id);
        return evaluacionRepository.findById(id);
    }

    // Obtener todas las evaluaciones
    @Transactional(readOnly = true)
    public List<Evaluacion> obtenerTodasLasEvaluaciones() {
        log.info("Listando todas las evaluaciones.");
        return evaluacionRepository.findAll();
    }

    // Crear una nueva evaluación
    @Transactional
    public Evaluacion crearEvaluacion(Evaluacion evaluacion) {
        if (evaluacion.getId() != null && evaluacionRepository.existsById(evaluacion.getId())) {
            throw new IllegalArgumentException("La evaluación ya existe");
        }
        log.info("Guardando nueva evaluación.");
        return evaluacionRepository.save(evaluacion);
    }

    // Actualizar una evaluación existente
    @Transactional
    public Evaluacion actualizarEvaluacion(Long id, Evaluacion detalles) {
        log.info("Actualizando evaluación con ID: {}", id);
        return evaluacionRepository.findById(id)
                .map(e -> {
                    e.setNombreEstudiante(detalles.getNombreEstudiante());
                    e.setTipo(detalles.getTipo());
                    e.setSeccion(detalles.getSeccion());
                    e.setMateria(detalles.getMateria());
                    e.setPeriodo(detalles.getPeriodo());
                    e.setEstado(detalles.getEstado());
                    e.setPuntaje(detalles.getPuntaje());
                    e.setNota(detalles.getNota());
                    return evaluacionRepository.save(e);
                })
                .orElseThrow(() -> {
                    log.error("No se encontró la evaluación con ID: {}", id);
                    return new IllegalArgumentException("Evaluación no encontrada con ID: " + id);
                });
    }

    // Eliminar una evaluación
    @Transactional
    public void eliminarEvaluacion(Long id) {
        log.info("Eliminando evaluación con ID: {}", id);
        if (!evaluacionRepository.existsById(id)) {
            log.warn("No existe evaluación con ID: {}", id);
            throw new IllegalArgumentException("Evaluación no encontrada con ID: " + id);
        }
        evaluacionRepository.deleteById(id);
    }
}

