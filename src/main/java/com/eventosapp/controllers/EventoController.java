package com.eventosapp.controllers;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoRepository er;
    private final ConvidadoRepository cr;

    @GetMapping("/novo")
    public String form() {
        return "evento/formEvento";
    }

    @PostMapping
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarEvento";
        }

        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }

    @GetMapping
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("listaEventos");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    @GetMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados", convidados);

        return mv;
    }

    @DeleteMapping("/{codigo}")
    public String deletarEvento(@PathVariable Long codigo) {
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);
        return "redirect:/eventos";
    }

    @PostMapping(value = "/{codigo}")
    public String adicionarConvidadosAoEvento(@PathVariable("codigo") Long codigo,
                                              @Valid Convidado convidado,
                                              BindingResult result,
                                              RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{codigo}";
        }
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
        return "redirect:/{codigo}";
    }

}	
