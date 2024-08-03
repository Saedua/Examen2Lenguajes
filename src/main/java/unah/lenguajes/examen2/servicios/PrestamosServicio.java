package unah.lenguajes.examen2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.examen2.modelos.Cliente;
import unah.lenguajes.examen2.modelos.Cuotas;
import unah.lenguajes.examen2.modelos.Prestamos;
import unah.lenguajes.examen2.repositorios.ClienteRepositorio;
import unah.lenguajes.examen2.repositorios.CuotasRepositorio;
import unah.lenguajes.examen2.repositorios.PrestamosRepositorio;

@Service
public class PrestamosServicio {

    @Autowired
    PrestamosRepositorio prestamosRepositorio;

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    CuotasRepositorio cuotasRepositorio;

    @Autowired
    CuotasServicio cuotasServicio;

    public double obtenerCuota(List<Prestamos> prestamosCliente) {
        double cuotaPrestamo = 0;
        if (prestamosCliente != null) {
            for (Prestamos prestamo : prestamosCliente) {
                cuotaPrestamo = (prestamo.getMonto() * (0.09 / 12))
                        / (1 - Math.pow((1 + 0.09 / 12), prestamo.getPlazo()));
            }
            return cuotaPrestamo;
        }
        return cuotaPrestamo;
    }

    public Prestamos crearPrestamo(String dni, Prestamos nvoPrestamo) {
        if (this.clienteRepositorio.existsById(dni)) {
            Cliente cliente = this.clienteRepositorio.findById(dni).get();
            List<Prestamos> prestamosCliente = cliente.getPrestamos();
            int cantidadPrestamosCliente = prestamosCliente.size();
            List<Cuotas> cuotas = null;
            /*
             * for (Prestamos prestamo : prestamosCliente) {
             * cantidadPrestamosCliente++;
             * }
             */
            if (cantidadPrestamosCliente > 2) {
                return null;
            } else {
                // AGREGAR METODO PARA CALCULAR LA CUOTA

                this.prestamosRepositorio.save(nvoPrestamo);
                cuotas = this.cuotasServicio.crearCuotas(nvoPrestamo);
                this.cuotasRepositorio.saveAll(cuotas);
                return nvoPrestamo;

            }
        } else {
            return null;
        }

    }

    public Prestamos obtenerPorCodigo(long codigo) {
        return this.prestamosRepositorio.findById(codigo).get();
    }
}
