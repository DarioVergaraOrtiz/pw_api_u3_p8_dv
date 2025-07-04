package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;


public class ProfesorTo {
    private Integer id;

    private String nombre;

    private String apellido;

    private String titulo;

    private LocalDateTime fechaContratacion;

    public Map<String, String> _links = new HashMap<>();

    public ProfesorTo(Integer id, String nombre, String apellido, String titulo, LocalDateTime fechaContratacion, UriInfo uriInfo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.fechaContratacion = fechaContratacion;

        URI todosHijos = uriInfo.getBaseUriBuilder().path(ProfesorController.class)
        .path(ProfesorController.class, "obtenerHijosPorId")
        .build(id);

        _links.put("hijos", todosHijos.toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDateTime fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
