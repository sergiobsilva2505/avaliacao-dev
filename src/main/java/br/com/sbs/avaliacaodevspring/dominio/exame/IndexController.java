package br.com.sbs.avaliacaodevspring.dominio.exame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String redirect() {
        return "redirect:/exames";
    }

}
