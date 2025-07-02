package uce.edu.web.api.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {
    
    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    public Profesor consultarPorId(@PathParam("id") Integer id) {
        return this.profesorService.buscarPorId(id);
    }

    @GET
    @Path("")
    public List<Profesor> consultarTodos(){
        return this.profesorService.consultarTodos();
    }

    @POST
    @Path("")
    public void guardar(Profesor profesor) {
        this.profesorService.guardar(profesor);
    }

    @PUT
    @Path("/{id}")
    public void actualizarPorId(@PathParam("id") Integer id,Profesor profesor){
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id,Profesor profesor){
         profesor.setId(id);
        Profesor e = this.profesorService.buscarPorId(id);
        if (profesor.getApellido() != null) {
            e.setApellido(profesor.getApellido());            
        }
        if (profesor.getNombre() != null) {
            e.setNombre(profesor.getNombre());            
        }
        if (profesor.getTitulo() != null) {
            e.setTitulo(profesor.getTitulo());            
        }
        if (profesor.getFechaContratacion() != null) {
            e.setFechaContratacion(profesor.getFechaContratacion());
        }
        this.profesorService.actualizarParcialPorId(e);
    }

    @DELETE
    @Path("/{id}")
    public void eliminarPorId(@PathParam("id") Integer id) {
        this.profesorService.eliminarPorId(id);
    }
}
