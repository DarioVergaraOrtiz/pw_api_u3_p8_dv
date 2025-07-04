package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import jakarta.persistence.TypedQuery;
import uce.edu.web.api.repository.modelo.Profesor;

@Transactional
@ApplicationScoped
public class ProfesorRepoImpl implements IProfesorRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profesor seleccionarPorId(Integer id) {
        return this.entityManager.find(Profesor.class, id);
    }

    @Override
    public List<Profesor> seleccionarTodos() {
        TypedQuery<Profesor> query = this.entityManager.createQuery("SELECT e FROM Profesor e", Profesor.class);
        return query.getResultList();
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Profesor profesor) {
        this.entityManager.persist(profesor);
    }

    

}
