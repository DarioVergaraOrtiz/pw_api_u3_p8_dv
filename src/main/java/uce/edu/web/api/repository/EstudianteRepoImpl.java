package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;

@Transactional
@ApplicationScoped
public class EstudianteRepoImpl implements IEstudianteRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Estudiante seleccionarporId(Integer id) {
        return this.entityManager.find(Estudiante.class, id);
    }

    @Override
    public List<Estudiante> seleccionarTodos() {
        TypedQuery<Estudiante> query = this.entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return query.getResultList();
    }

    @Override
    public void actualizarPorId(Integer id, Estudiante estudiante) {
        Estudiante estudianteExistente = this.entityManager.find(Estudiante.class, id);
        if (estudianteExistente != null) {
            estudianteExistente.setNombre(estudiante.getNombre());
            estudianteExistente.setApellido(estudiante.getApellido());
            estudianteExistente.setFechaNacimiento(estudiante.getFechaNacimiento());
            this.entityManager.merge(estudianteExistente);
        }
    }

    @Override
    public void actualizarParcialPordId(Integer id, Estudiante estudiante) {
        Estudiante estudianteExistente = this.entityManager.find(Estudiante.class, id);
        if (estudianteExistente != null) {
            if (estudiante.getNombre() != null) {
                estudianteExistente.setNombre(estudiante.getNombre());
            }
            if (estudiante.getApellido() != null) {
                estudianteExistente.setApellido(estudiante.getApellido());
            }
            if (estudiante.getFechaNacimiento() != null) {
                estudianteExistente.setFechaNacimiento(estudiante.getFechaNacimiento());
            }
            this.entityManager.merge(estudianteExistente);
        }
    }

    @Override
    public void borrarPorId(Integer id) {
        Estudiante estudiante = this.entityManager.find(Estudiante.class, id);
        if (estudiante != null) {
            this.entityManager.remove(estudiante);
        }
    }

    @Override
    public void insertar(Estudiante estudiante) {
        this.entityManager.persist(estudiante);
    }
    
}
