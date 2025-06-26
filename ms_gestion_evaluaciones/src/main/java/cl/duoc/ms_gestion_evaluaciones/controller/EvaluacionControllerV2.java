package cl.duoc.ms_gestion_evaluaciones.controller;

import cl.duoc.ms_gestion_evaluaciones.assemblers.EvaluacionModelAssembler;
import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionControllerV2 {

    private final EvaluacionService evaluacionService;
    private final EvaluacionModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Evaluacion>> obtenerTodas() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.obtenerTodasLasEvaluaciones().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones,
            linkTo(methodOn(EvaluacionControllerV2.class).obtenerTodas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Evaluacion> obtenerEvaluacionPorId(@PathVariable String id) {
        Evaluacion evaluacion = evaluacionService.obtenerEvaluacionPorId(id)
            .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
        return assembler.toModel(evaluacion);
    }
}