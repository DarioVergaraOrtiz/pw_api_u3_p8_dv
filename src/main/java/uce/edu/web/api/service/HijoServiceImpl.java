package uce.edu.web.api.service;
import java.util.ArrayList;
import java.util.List;
import uce.edu.web.api.repository.modelo.Hijo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.HijoRepo;

@ApplicationScoped
public class HijoServiceImpl implements HijoService {

    @Inject
    private HijoRepo hijoRepo;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer Id) {
     return this.hijoRepo.buscarPorEstudianteId(Id);
    }
    
}
