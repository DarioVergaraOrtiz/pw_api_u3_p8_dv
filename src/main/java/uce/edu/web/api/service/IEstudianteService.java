package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public interface IEstudianteService {
    public EstudianteTo buscarPorId(Integer id, UriInfo uriInfo);
    public List<Estudiante> consultarTodos(String genero);
    public void guardar(Estudiante estudiante);
    public void actualizarPorId(Estudiante estudiante);
    public void actualizarParcialPorId(Estudiante estudiante);
    public void eliminarPorId(Integer id);
}
