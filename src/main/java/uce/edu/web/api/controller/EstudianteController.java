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
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;


@Path("/estudiantes")
public class EstudianteController {
    
    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.buscarPorId(id);
    }

    @GET
    @Path("")
    public List<Estudiante> consultarTodos(){
        return this.estudianteService.consultarTodos();
    }

    @POST
    @Path("")
    public void guardar(Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        
    }

    @PUT
    @Path("/{id}")
    public void actualizarPorId(@PathParam("id") Integer id,Estudiante estudiante){
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(id, estudiante);

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id,Estudiante estudiante){

        estudiante.setId(id);
        Estudiante estudianteExistente = this.estudianteService.buscarPorId(id);
        if (estudianteExistente.getApellido() != null) {
            estudiante.setApellido(estudianteExistente.getApellido());
        }
        if (estudianteExistente.getFechaNacimiento() != null) {
            estudiante.setFechaNacimiento(estudianteExistente.getFechaNacimiento());
        }
        if (estudianteExistente.getNombre() != null) {
            estudiante.setNombre(estudianteExistente.getNombre());
        }

        this.estudianteService.actualizarParcialPorId(id, estudianteExistente);
    }

    @DELETE
    @Path("/{id}")
    public void eliminarPorId(@PathParam("id") Integer id) {
        this.estudianteService.eliminarPorId(id);
    }
}
