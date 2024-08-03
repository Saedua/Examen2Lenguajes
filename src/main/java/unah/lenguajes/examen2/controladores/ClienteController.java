package unah.lenguajes.examen2.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unah.lenguajes.examen2.modelos.Cliente;
import unah.lenguajes.examen2.servicios.ClienteServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/obtener/todos")
    public List<Cliente> obtenerTodos() {
        return this.clienteServicio.obtenerTodos();
    }

    @PostMapping("/crear")
    public Cliente crear(@RequestBody Cliente nvoCliente) {
        // TODO: process POST request

        return this.clienteServicio.crearCliente(nvoCliente);
    }

}
