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
    private String Tipo;
    @Column(name = "SECCION")
    private String Seccion;
    @Column(name = "MATERIA")
    private String Materia;
    @Column(name = "PERIODO")
    private String Periodo;
    @Column(name = "ESTADO")
    private String Estado;
    @Column(name = "PUNTAJE")
    private int Puntaje;
    @Column(name = "NOTA")
    private int Nota;

    public Evaluacion(String nombreEstudiante, String Tipo, String Seccion, String Materia, String Periodo, String Estado,int Puntaje,int Nota){
        this.nombreEstudiante=nombreEstudiante;
        this.Tipo=Tipo;
        this.Seccion=Seccion;
        this.Materia=Materia;
        this.Periodo=Periodo;
        this.Estado=Estado;
        this.Puntaje=Puntaje;
        this.Nota=Nota;
    }
}
