package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {
    
    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarPorId(id)).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary= "Consultar profesores",
        description= "Esta capacidad permite consultar profesores de la base de datos"
    )
    public Response consultarTodos(){
        return Response.status(Response.Status.OK).entity(this.profesorService.consultarTodos()).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary= "Guardar profesor",
        description= "Esta capacidad permite guardar en la base de datos un profesor"
    )
    public Response guardar(Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@PathParam("id") Integer id, Profesor profesor){
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.ok().build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@PathParam("id") Integer id, Profesor profesor){
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
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPorId(@PathParam("id") Integer id) {
        this.profesorService.eliminarPorId(id);
        return Response.noContent().build();
    }
}
