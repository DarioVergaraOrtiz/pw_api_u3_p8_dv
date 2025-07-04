package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService{

    @Inject
    private IProfesorRepo profesorRepo;


    @Override
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo) {
        Profesor p1 = this.profesorRepo.seleccionarPorId(id);
        ProfesorTo p = new ProfesorTo(p1.getId(), p1.getNombre(), p1.getApellido(), p1.getTitulo(), p1.getFechaContratacion(), uriInfo);
        return p;
    }


    @Override
    public List<Profesor> consultarTodos() {
         return this.profesorRepo.seleccionarTodos();
    }


    @Override
    public void guardar(Profesor profesor) {
        this.profesorRepo.insertar(profesor);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.profesorRepo.actualizarPorId(profesor);
    }


    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.profesorRepo.actualizarParcialPorId(profesor);
    }


    @Override
    public void eliminarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }
    
}
