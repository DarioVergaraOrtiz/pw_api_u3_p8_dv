package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IEstudianteRepo;
import uce.edu.web.api.repository.modelo.Estudiante;

@ApplicationScoped
public class EstudianteServiceImpl implements IEstudianteService{

    @Inject
    private IEstudianteRepo estudianteRepo;


    @Override
    public Estudiante buscarPorId(Integer id) {
        return this.estudianteRepo.seleccionarporId(id);
    }

    @Override
    public List<Estudiante> consultarTodos(String genero) {
        return this.estudianteRepo.seleccionarTodos(genero);
    }

    @Override
    public void guardar(Estudiante estudiante) {
        this.estudianteRepo.insertar(estudiante);
    }

    @Override
    public void actualizarPorId(Estudiante estudiante) {
        this.estudianteRepo.actualizarPorId(estudiante);
    }

    @Override
    public void actualizarParcialPorId(Estudiante estudiante) {
        this.estudianteRepo.actualizarParcialPordId(estudiante);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.estudianteRepo.borrarPorId(id);
    }
    
}
