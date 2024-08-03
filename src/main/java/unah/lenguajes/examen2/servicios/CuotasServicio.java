package unah.lenguajes.examen2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.examen2.modelos.Cuotas;
import unah.lenguajes.examen2.modelos.Prestamos;
import unah.lenguajes.examen2.repositorios.CuotasRepositorio;
import unah.lenguajes.examen2.repositorios.PrestamosRepositorio;

@Service
public class CuotasServicio {

    @Autowired
    CuotasRepositorio cuotasRepositorio;

    @Autowired
    PrestamosRepositorio prestamosRepositorio;

    public List<Cuotas> crearCuotas(Prestamos prestamo) {
        List<Cuotas> cuotas = null;
        // Prestamos prestamo =
        // this.prestamosRepositorio.findById(codigoPrestamo).get();

        for (int i = 0; i <= prestamo.getPlazo() * 12; i++) {
            Cuotas cuota = new Cuotas();
            cuota.setPrestamo(prestamo);
            double saldoAnterior = 0;
            if (i == 0) {
                cuota.setMes(0);
                cuota.setCapital(0);
                cuota.setInteres(0);
                cuota.setSaldo(prestamo.getMonto());
                saldoAnterior = prestamo.getMonto();
                this.cuotasRepositorio.save(cuota);
                cuotas.add(cuota);
            } else {
                double interesActual;
                double capitalActual;
                cuota.setMes(i);
                interesActual = saldoAnterior * (0.09 / 12);
                cuota.setInteres(interesActual);
                capitalActual = prestamo.getCuota() - interesActual;
                cuota.setCapital(capitalActual);
                cuota.setSaldo(saldoAnterior - capitalActual);
                cuotas.add(cuota);
                // this.cuotasRepositorio.save(cuota);
                saldoAnterior = saldoAnterior - capitalActual;
            }
        }
        return cuotas;
        // this.cuotasRepositorio.saveAll(cuotas);
    }
}
