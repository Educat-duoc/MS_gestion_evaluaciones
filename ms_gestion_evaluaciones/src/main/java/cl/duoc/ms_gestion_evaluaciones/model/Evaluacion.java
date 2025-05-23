package cl.duoc.ms_gestion_evaluaciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Evaluacion") 
public class Evaluacion {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // id automatico autoincrementable prueba
    @Column(name = "ID")
    private String id;
    @Column(name = "NOMBREESTUDIANTE")
    private String nombreEstudiante;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "SECCION")
    private String seccion;
    @Column(name = "MATERIA")
    private String materia;
    @Column(name = "PERIODO")
    private String periodo;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "PUNTAJE")
    private int puntaje;
    @Column(name = "NOTA")
    private int nota;

    public Evaluacion(String nombreEstudiante, String tipo, String seccion, String materia, String periodo, String estado,int puntaje,int nota){
        this.nombreEstudiante=nombreEstudiante;
        this.tipo=tipo;
        this.seccion=seccion;
        this.materia=materia;
        this.periodo=periodo;
        this.estado=estado;
        this.puntaje=puntaje;
        this.nota=nota;
    }
}
