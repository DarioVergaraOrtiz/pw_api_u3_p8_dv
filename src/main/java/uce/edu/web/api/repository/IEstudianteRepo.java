package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteRepo {

    public Estudiante seleccionarporId(Integer id);
    public List<Estudiante> seleccionarTodos();
    public void actualizarPorId(Integer id, Estudiante estudiante);
    public void actualizarParcialPordId(Integer id, Estudiante estudiante);
    public void borrarPorId(Integer id);
    public void insertar(Estudiante estudiante);
    
}
