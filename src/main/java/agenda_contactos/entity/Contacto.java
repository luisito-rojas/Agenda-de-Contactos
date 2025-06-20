package agenda_contactos.entity;


//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "Debe ingresar su nombre")
    private String nombre;

    @NotEmpty(message = "Debe ingresar su email")
    @Email
    private String email;

    @NotBlank(message = "Debe ingresar su numero de celular")
    private String celular;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //Esta anotacion nos va a permitir que la fecha de nacimiento sea solo la fecha que haya pasado, NO PRESENTE, NO FUTURO
    @Past
    @NotNull(message = "Debe ingresar su fecha de nacimiento")
    private LocalDate fechaNacimiento;


    private LocalDateTime fechaRegistros;

    public Contacto() {
    }

    public Contacto(Integer id, String nombre, String email, String celular, LocalDate fechaNacimiento, LocalDateTime fechaRegistros) {
        Id = id;
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistros = fechaRegistros;
    }

    public Contacto(String nombre, String email, String celular, LocalDate fechaNacimiento, LocalDateTime fechaRegistros) {
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistros = fechaRegistros;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaRegistros() {
        return fechaRegistros;
    }

    public void setFechaRegistros(LocalDateTime fechaRegistros) {
        this.fechaRegistros = fechaRegistros;
    }

    //Cuando creamos un nuevo registro automaticamente se le va a registrar la fecha de hoy
    @PrePersist
    public void asignarFechaRegistro(){
        fechaRegistros = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Contacto{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaRegistros=" + fechaRegistros +
                '}';
    }
}
