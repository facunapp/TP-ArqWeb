package repository.impl;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import jakarta.persistence.EntityManager;
import repository.EstudianteCarreraRepository;

import java.util.List;

public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {

    private EntityManager em;

    public EstudianteCarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void matricular(Estudiante estudiante, Carrera carrera, int anioInscripcion) {
        em.getTransaction().begin();

        // Creamos la matrícula sin antigüedad (el 0 final corresponde a graduación: no graduado)
        EstudianteCarrera matricula = new EstudianteCarrera(estudiante, carrera, anioInscripcion, 0);

        em.persist(matricula);
        em.getTransaction().commit();
    }

    @Override
    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(int idCarrera, String ciudad) {
        String jpql = "SELECT ec.estudiante " +
                "FROM EstudianteCarrera ec " +
                "WHERE ec.carrera.idCarrera = :idCarrera " +
                "AND ec.estudiante.ciudad = :ciudad";

        return em.createQuery(jpql, Estudiante.class)
                .setParameter("idCarrera", idCarrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }
}