package br.com.sbs.avaliacaodevspring.exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String redirect() {
        return "redirect:/exames";
    }


}
