package repository.impl;

import java.util.List;

import entities.Estudiante;
import jakarta.persistence.EntityManager;
import repository.EstudianteRepository;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private EntityManager em;

    // Constructor que recibe el EntityManager, tal como se recomienda en el Patrón DAO
    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void guardar(Estudiante estudiante) {
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
    }

    @Override
    public Estudiante buscarPorLu(int lu) {
        // Implementación básica, usando createQuery para buscar por el atributo lu
        String jpql = "SELECT e FROM Estudiante e WHERE e.lu = :lu";
        return em.createQuery(jpql, Estudiante.class)
                .setParameter("lu", lu)
                .getSingleResult();
    }

    @Override
    public List<Estudiante> obtenerTodosPorCriterio() {
        return em.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido ASC", Estudiante.class)
                .getResultList();
    }

    @Override
    public List<Estudiante> buscarPorGenero(String genero) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        return em.createQuery(jpql, Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
    }

    //Metodo ej 2.g
    @Override
    public List<Estudiante> buscarPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        String jpql = "SELECT ec.estudiante " +
                "FROM EstudianteCarrera ec " +
                "WHERE ec.carrera.carrera = :carrera " +
                "AND ec.estudiante.ciudad = :ciudad";

        return em.createQuery(jpql, Estudiante.class)
                .setParameter("carrera", nombreCarrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }
}