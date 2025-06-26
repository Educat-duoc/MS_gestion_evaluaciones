package cl.duoc.ms_gestion_evaluaciones.controller;


import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*import org.springframework.web.bind.annotation.PostMapping;*/
/*import org.springframework.web.bind.annotation.RequestBody;*/
/*import org.springframework.web.bind.annotation.PutMapping;*/
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/Evaluacion")
@RequiredArgsConstructor
@Tag(name = "Evaluaciones",description = "operaciones de gestion evaluaciones del microservicios")

public class EvaluacionController {

    private static final Logger logger = LoggerFactory.getLogger(EvaluacionController.class);
    private final EvaluacionService evaluacionService;
    
    @GetMapping("Evaluacion/status")
    @Operation(summary = "Obtener evaluaciones", description = "Obtiene evaluaciones actual del microservicio.")
    @ApiResponse(responseCode = "200", description = "Microservicio activo")

    public String estadoServicio() {
        return  "El MS esta activo";
        
    }
    
    @PostMapping
    @Operation(summary = "Crear evaluación", description = "Crea una nueva evaluación. Devuelve la evaluación creada.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Evaluación creada correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evaluacion.class))),
    @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación")
        })
public ResponseEntity<?> crearEvaluacion(@RequestBody Evaluacion evaluacion) {
    try {
        Evaluacion nuevaEvaluacion = evaluacionService.crearEvaluacion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    } catch (IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }
}


    @GetMapping
    @Operation(summary = "Obtener todas las evaluaciones", description = "Devuelve una lista de todas las evaluaciones registradas.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de evaluaciones encontrada",
        content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evaluacion.class))))
    })
public List<Evaluacion> obtenerTodas() {
    logger.info("Solicitud para listar todas las evaluaciones");
    return evaluacionService.obtenerTodasLasEvaluaciones();
}


    @GetMapping("ObtenerEvaluacion/{id}")
    @Operation(summary = "Obtener evaluación por ID", description = "Devuelve los detalles de una evaluación específica por su ID.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Evaluación encontrada",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evaluacion.class))),
    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
})
public ResponseEntity<Evaluacion> obtenerPorId(@PathVariable String id) {
    logger.info("solicitud recibida para obtener evaluaciones por ID: {}", id);
    return evaluacionService.obtenerEvaluacionPorId(id)
            .map(evaluacion -> {
                logger.info("Evaluación encontrada con ID: {}", evaluacion.getId());
                return new ResponseEntity<>(evaluacion, HttpStatus.OK);
            })
            .orElseGet(() -> {
                logger.warn("Evaluación no encontrada con ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
}


    @PutMapping("Actualizar/{id}")
    @Operation(summary = "Actualizar evaluación", description = "Actualiza una evaluación existente, identificada por su ID.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Evaluación actualizada correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evaluacion.class))),
    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
public ResponseEntity<Evaluacion> actualizarEvaluacion(@PathVariable String id, @RequestBody Evaluacion evaluacion) {
    logger.info("solicitud recibida para actualizar evaluación con ID {}. Datos: {}", id, evaluacion.getId());
    try {
        Evaluacion actualizada = evaluacionService.actualizarEvaluacion(id, evaluacion);
        return new ResponseEntity<>(actualizada, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        logger.error("No se pudo actualizar evaluación con ID {}: {}", id, e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("eliminarId/{id}")
    @Operation(summary = "Eliminar evaluación", description = "Elimina una evaluación existente por su ID.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Evaluación eliminada correctamente"),
    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
})
public ResponseEntity<Void> eliminarEvaluacion(@PathVariable String id) {
    logger.info("Solicitud recibida para eliminar evaluación por ID: {}", id);
    try {
        evaluacionService.eliminarEvaluacion(id);
        logger.info("Evaluación eliminada");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (IllegalArgumentException e) {
        logger.error("Intento de eliminar evaluación inexistente con ID {}: {}", id, e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}}