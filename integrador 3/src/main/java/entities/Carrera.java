package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

    @Entity
    public class Carrera {
        @Id
        @Column(name = "id_carrera")
        private int idCarrera;

        @Column(nullable = false)
        private String carrera;

        private int duracion;


        public Carrera() {}

        public Carrera(int idCarrera, String carrera, int duracion) {
            this.idCarrera = idCarrera;
            this.carrera = carrera;
            this.duracion = duracion;
        }

        public int getIdCarrera() {
            return idCarrera;
        }

        public void setIdCarrera(int idCarrera) {
            this.idCarrera = idCarrera;
        }

        public String getCarrera() {
            return carrera;
        }

        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        public int getDuracion() {
            return duracion;
        }

        public void setDuracion(int duracion) {
            this.duracion = duracion;
        }


    }

