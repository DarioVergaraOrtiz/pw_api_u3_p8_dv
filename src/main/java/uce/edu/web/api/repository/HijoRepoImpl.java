package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
@Transactional
public class HijoRepoImpl implements HijoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        TypedQuery<Hijo> myQuery = this.entityManager.createQuery("SELECT h FROM Hijo h WHERE h.estudiante.id =:id", Hijo.class);
        myQuery.setParameter("id", id);
        return myQuery.getResultList();
    }
    
}
