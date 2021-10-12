package br.com.treinaweb.ediaristas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.models.Housekeeper;
import br.com.treinaweb.ediaristas.repositories.HousekeeperRepository;

@Controller
@RequestMapping("/admin/diaristas")
public class HousekeeperController {

  @Autowired
  private HousekeeperRepository repository;

  @GetMapping
  public ModelAndView list() {
    var modelAndView = new ModelAndView("admin/housekeepers/list");

    modelAndView.addObject("housekeepers", repository.findAll());

    return modelAndView;
  }

  @GetMapping("/cadastrar")
  public ModelAndView register() {
    var modelAndView = new ModelAndView("admin/housekeepers/form");

    modelAndView.addObject("housekeeper", new Housekeeper());

    return modelAndView;
  }

  @PostMapping("/cadastrar")
  public String register(Housekeeper housekeeper) {
    repository.save(housekeeper);

    return "redirect:/admin/diaristas/cadastrar";
  }
}
