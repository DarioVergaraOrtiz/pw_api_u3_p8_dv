package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {
    
    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id,@Context UriInfo uriInfo) {
        
        ProfesorTo profe = this.profesorService.buscarPorId(id, uriInfo);
        return Response.status(227).entity(profe).build();
    }

    @GET
    @Path("")
    @Operation(
        summary= "Consultar profesores",
        description= "Esta capacidad permite consultar profesores de la base de datos"
    )
    public Response consultarTodos(){
        return Response.status(Response.Status.OK).entity(this.profesorService.consultarTodos()).build();
    }

    @POST
    @Path("")
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

/*  @PATCH
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
    } */

    @DELETE
    @Path("/{id}")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        this.profesorService.eliminarPorId(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id){
        
        Hijo h1 = new Hijo();
        h1.setNombre("calixtoProfesor");
        
        Hijo h2 = new Hijo();
        h2.setNombre("darioProfesor");

        List<Hijo> hijos = new ArrayList<>();

        hijos.add(h1);
        hijos.add(h2);

        return hijos;
        
    }
}
