package unah.lenguajes.examen2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import unah.lenguajes.examen2.modelos.Cliente;
import unah.lenguajes.examen2.modelos.Prestamos;

public interface PrestamosRepositorio extends JpaRepository<Prestamos, Long> {

}
