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
        // En base a la teoría de JPA, envolvemos la operación persist (INSERT) en una transacción[cite: 7]
        em.getTransaction().begin();

        // Completamos con valores por defecto (0) los campos de graduación y antigüedad que requiere tu entidad
        EstudianteCarrera matricula = new EstudianteCarrera(0, estudiante, carrera, anioInscripcion, 0, 0);

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