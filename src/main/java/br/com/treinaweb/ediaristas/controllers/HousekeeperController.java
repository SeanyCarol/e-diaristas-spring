package br.com.treinaweb.ediaristas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.models.Housekeeper;

@Controller
@RequestMapping("/admin/diaristas")
public class HousekeeperController {

  @GetMapping("/cadastrar")
  public ModelAndView register() {
    var modelAndView = new ModelAndView("admin/housekeepers/form");

    modelAndView.addObject("housekeeper", new Housekeeper());

    return modelAndView;
  }
}
