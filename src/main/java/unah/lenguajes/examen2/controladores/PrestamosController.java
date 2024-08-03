package unah.lenguajes.examen2.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unah.lenguajes.examen2.modelos.Prestamos;
import unah.lenguajes.examen2.servicios.PrestamosServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamosController {

    @Autowired
    PrestamosServicio prestamosServicio;

    @GetMapping("/obtener/{codigo}")
    public Prestamos obtenerPorCodigo(@PathVariable long codigo) {
        return this.prestamosServicio.obtenerPorCodigo(codigo);
    }

    @PostMapping("/crear/{dni}")
    public Prestamos crearPrestamo(@PathVariable String dni, @RequestBody Prestamos nvoPrestamo) {

        return this.prestamosServicio.crearPrestamo(dni, nvoPrestamo);
    }

}
