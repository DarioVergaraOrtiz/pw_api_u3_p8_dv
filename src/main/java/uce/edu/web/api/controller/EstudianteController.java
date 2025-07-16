package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.HijoService;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;



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
    @Operation(
        summary= "Consultar estudiante por ID",
        description= "Esta capacidad permite consultar un estudiante por su ID"
    )
    public Response consultarPorId(@PathParam("id") Integer id,@Context UriInfo uriInfo) {
        
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }

    //?genero=F&provincia=pichincha SOAP -> XML.   RESTful -> JSON
    @GET
    @Path("")
    @Operation(
        summary= "Consultar estudiantes",
        description= "Esta capacidad permite consultar estudiantes de la base de datos"
    )
    public Response consultarTodos(@QueryParam("genero") String genero,
                                   @QueryParam("provincia") String provincia){
        System.out.println(provincia);

        List<EstudianteTo> estudiantes = this.estudianteService.consultarTodos(genero).stream()
            .map(EstudianteMapper::toTo)
            .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(estudiantes).build();
    }

    @POST
    @Path("")
    @Operation(
        summary= "Guardar estudiante",
        description= "Esta capacidad permite guardar en la base de datos un estudiante"
    )
    public void guardar(EstudianteTo estudianteTo) {
        this.estudianteService.guardar(EstudianteMapper.toEntity(estudianteTo));
    }

    @PUT
    @Path("/{id}")
    public void actualizarPorId(@PathParam("id") Integer id, EstudianteTo estudianteTo) {
        estudianteTo.setId(id);
        this.estudianteService.actualizarPorId(EstudianteMapper.toEntity(estudianteTo));
    }

    @PATCH
    @Path("/{id}")
    @Operation(
        summary= "Actualizar parcialmente estudiante por ID",
        description= "Esta capacidad permite actualizar parcialmente un estudiante por su ID"
    )
    public void actualizarParcialPorId(@PathParam("id") Integer id, EstudianteTo estudianteTo){

        estudianteTo.setId(id);
        EstudianteTo eTo = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        if(estudianteTo.getApellido() != null) {
            eTo.setApellido(estudianteTo.getApellido());
        }
        if(estudianteTo.getNombre() != null) {
            eTo.setNombre(estudianteTo.getNombre());
        }
        if(estudianteTo.getGenero() != null) {
            eTo.setGenero(estudianteTo.getGenero());
        }
        if(estudianteTo.getFechaNacimiento() != null) {
            eTo.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        }
        this.estudianteService.actualizarParcialPorId(EstudianteMapper.toEntity(eTo));
    } 

    @DELETE
    @Path("/{id}")
    @Operation(
        summary= "Eliminar estudiante por ID",
        description= "Esta capacidad permite eliminar un estudiante por su ID"
    )
    public void eliminarPorId(@PathParam("id") Integer id) {
        this.estudianteService.eliminarPorId(id);
    }

    //http://......../estudiantes/1/hijos GET
    @GET
    @Path("/{id}/hijos")
    @Operation(
        summary= "Consultar hijos por ID de estudiante",
        description= "Esta capacidad permite consultar los hijos de un estudiante por su ID"
    )
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id){

        return this.hijoService.buscarPorEstudianteId(id);
        
    }
}
