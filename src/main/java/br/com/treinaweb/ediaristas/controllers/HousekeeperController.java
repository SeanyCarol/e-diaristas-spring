package br.com.treinaweb.ediaristas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public String register(@Valid Housekeeper housekeeper, BindingResult result) {
    if (result.hasErrors()) {
      return "admin/housekeepers/form";
    }

    repository.save(housekeeper);

    return "redirect:/admin/diaristas";
  }

  @GetMapping("/{id}/editar")
  public ModelAndView update(@PathVariable Long id) {
    var modelAndView = new ModelAndView("admin/housekeepers/form");

    modelAndView.addObject("housekeeper", repository.getById(id));

    return modelAndView;
  }

  @PostMapping("/{id}/editar")
  public String update(@PathVariable Long id, @Valid Housekeeper housekeeper, BindingResult result) {
    if (result.hasErrors()) {
      return "admin/housekeepers/form";
    }

    repository.save(housekeeper);

    return "redirect:/admin/diaristas";
  }

  @GetMapping("/{id}/excluir")
  public String delete(@PathVariable Long id) {
    repository.deleteById(id);

    return "redirect:/admin/diaristas";
  }
}
