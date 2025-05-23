package cl.duoc.ms_gestion_evaluaciones.controller;

import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*import org.springframework.web.bind.annotation.PostMapping;*/
/*import org.springframework.web.bind.annotation.RequestBody;*/
/*import org.springframework.web.bind.annotation.PutMapping;*/
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Evaluacion")
@RequiredArgsConstructor

public class EvaluacionController {
private static final Logger log = LoggerFactory.getLogger(EvaluacionController.class);
    private final EvaluacionService evaluacionService;
    @GetMapping("/status")
    public String estadoServicio() {
        return "Microservicio de gestión de evaluaciones activo";
    }
    @PostMapping
    public ResponseEntity<Evaluacion> crearEvaluacion(@RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion nueva = evaluacionService.crearEvaluacion(evaluacion);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("Error al crear evaluación: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<Evaluacion> obtenerTodas() {
        log.info("Solicitud para listar todas las evaluaciones");
        return evaluacionService.obtenerTodasLasEvaluaciones();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> obtenerPorId(@PathVariable Long id) {
        return evaluacionService.obtenerEvaluacionPorId(id)
                .map(evaluacion -> {
                    log.info("Evaluación encontrada con ID: {}", id);
                    return new ResponseEntity<>(evaluacion, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    log.warn("Evaluación no encontrada con ID: {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
    
}
