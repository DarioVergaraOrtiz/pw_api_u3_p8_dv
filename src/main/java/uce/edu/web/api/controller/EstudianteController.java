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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.to.EstudianteTo;
import uce.edu.web.api.service.HijoService;


@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteController {
    
    @Inject
    private IEstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id,@Context UriInfo uriInfo) {
        
        EstudianteTo estu = this.estudianteService.buscarPorId(id, uriInfo);
        return Response.status(227).entity(estu).build();
    }

    //?genero=F&provincia=pichincha SOAP -> XML.   RESTful -> JSON
    @GET
    @Path("")
    @Operation(
        summary= "Consultar estudiantes",
        description= "Esta capacidad permite consultar estudiantes de la base de datos"
    )
    public Response consultarTodos(@QueryParam("genero") String genero,@QueryParam("provincia") String provincia){
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.consultarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Operation(
        summary= "Guardar estudiante",
        description= "Esta capacidad permite guardar en la base de datos un estudiante"
    )
    public Response guardar( Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPorId(@PathParam("id") Integer id, Estudiante estudiante){
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.ok().build();
    }

/*   @PATCH
    @Path("/{id}")
    public Response actualizarParcialPorId(@PathParam("id") Integer id, Estudiante estudiante){

        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if (estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());            
        }
        if (estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());            
        }
        if (estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());            
        }        
        this.estudianteService.actualizarParcialPorId(e);
        return Response.ok().build();
    } */

    @DELETE
    @Path("/{id}")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        this.estudianteService.eliminarPorId(id);
        return Response.noContent().build();
    }

    //http://......../estudiantes/1/hijos GET
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id){

        return this.hijoService.buscarPorEstudianteId(id);
        
    }
}
