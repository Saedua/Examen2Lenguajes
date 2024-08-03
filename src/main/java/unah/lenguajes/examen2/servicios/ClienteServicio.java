package unah.lenguajes.examen2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.examen2.modelos.Cliente;
import unah.lenguajes.examen2.modelos.Cuotas;
import unah.lenguajes.examen2.modelos.Prestamos;
import unah.lenguajes.examen2.repositorios.ClienteRepositorio;
import unah.lenguajes.examen2.repositorios.CuotasRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    PrestamosServicio prestamosServicio;

    @Autowired
    CuotasServicio cuotasServicio;

    @Autowired
    CuotasRepositorio cuotasRepositorio;

    public Cliente crearCliente(Cliente nvoCliente) {

        if (!this.clienteRepositorio.existsById(nvoCliente.getDni())) {
            List<Prestamos> prestamosCliente = nvoCliente.getPrestamos();
            double cuotaPrestamo;
            // List<Cuotas> cuotas = null;

            // MOVER ESTO A UNA FUNCION EN EL SERVICIO DE PRESTAMOS PARA SOLO LLAMAR UNA
            // FUNCION

            if (prestamosCliente != null) {
                for (Prestamos prestamo : prestamosCliente) {
                    cuotaPrestamo = (prestamo.getMonto() * (0.09 / 12)
                            * Math.pow((1 + (0.09 / 12)), prestamo.getPlazo()))
                            / ((Math.pow((1 + 0.09 / 12), prestamo.getPlazo())) - 1);

                    prestamo.setCuota(cuotaPrestamo);
                    prestamo.setCliente(nvoCliente);
                    // cuotas = this.cuotasServicio.crearCuotas(prestamo);
                }

            }
            // FALTA AUN EL GENERAR LOS REGISTROS EN CUOTAS

            return this.clienteRepositorio.save(nvoCliente);
            // this.cuotasRepositorio.saveAll(cuotas);
            // return nvoCliente;

        } else {
            return null;
        }
    }

    // Inciso 6
    public List<Cliente> obtenerTodos() {
        return this.clienteRepositorio.findAll();
    }
}
