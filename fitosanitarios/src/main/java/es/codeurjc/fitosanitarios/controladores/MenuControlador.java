package es.codeurjc.fitosanitarios.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuControlador {

    @RequestMapping("/")
    public String cargarMenu() {
        return "menu";
    }
}
