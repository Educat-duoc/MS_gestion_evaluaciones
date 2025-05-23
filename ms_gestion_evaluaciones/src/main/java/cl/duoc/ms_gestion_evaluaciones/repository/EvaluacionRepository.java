package cl.duoc.ms_gestion_evaluaciones.repository;

import cl.duoc.ms_gestion_evaluaciones.model.Evaluacion;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, String> {
   
}
