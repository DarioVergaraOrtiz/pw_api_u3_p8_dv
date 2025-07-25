package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public class EstudianteMapper {
    

    public static EstudianteTo toTo(Estudiante estudiante){

        EstudianteTo eTo = new EstudianteTo();
            eTo.setId(estudiante.getId());
            eTo.setNombre(estudiante.getNombre());
            eTo.setApellido(estudiante.getApellido());
            eTo.setFechaNacimiento(estudiante.getFechaNacimiento());

        return eTo;
    }

    public static Estudiante toEntity(EstudianteTo estudianteTo){
        
        Estudiante e = new Estudiante();

            e.setId(estudianteTo.getId());
            e.setNombre(estudianteTo.getNombre());
            e.setApellido(estudianteTo.getApellido());
            e.setFechaNacimiento(estudianteTo.getFechaNacimiento());
            e.setGenero(estudianteTo.getGenero());

        return e;

    }
}
