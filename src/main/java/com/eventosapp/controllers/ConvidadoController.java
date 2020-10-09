package com.eventosapp.controllers;

import com.eventosapp.models.Convidado;
import com.eventosapp.repository.ConvidadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/convidados")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoRepository cr;

    @DeleteMapping("/{rg}")
    public String excluir(@PathVariable String rg) {
        Convidado convidado = cr.findByRg(rg);
        cr.delete(convidado);

        return "redirect:/eventos/" + convidado.getEvento().getCodigo();
    }
}
