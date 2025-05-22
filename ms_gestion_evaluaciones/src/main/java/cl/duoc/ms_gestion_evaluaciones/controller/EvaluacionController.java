package cl.duoc.ms_gestion_evaluaciones.controller;

import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import cl.duoc.ms_gestion_evaluaciones.service.EvaluacionService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*import org.springframework.web.bind.annotation.PostMapping;*/
/*import org.springframework.web.bind.annotation.RequestBody;*/
/*import org.springframework.web.bind.annotation.PutMapping;*/
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Evaluacion")
@RequiredArgsConstructor

public class EvaluacionController {

}
