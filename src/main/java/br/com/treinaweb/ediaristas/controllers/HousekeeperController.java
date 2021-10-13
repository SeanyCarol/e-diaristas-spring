package br.com.treinaweb.ediaristas.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.models.Housekeeper;
import br.com.treinaweb.ediaristas.repositories.HousekeeperRepository;
import br.com.treinaweb.ediaristas.services.FileService;
import br.com.treinaweb.ediaristas.services.ViaCepService;
import br.com.treinaweb.ediaristas.validators.CepValidator;

@Controller
@RequestMapping("/admin/diaristas")
public class HousekeeperController {

  @Autowired
  private HousekeeperRepository repository;

  @Autowired
  private FileService fileService;

  @Autowired
  private ViaCepService viaCepService;

  @Autowired
  private CepValidator cepValidator;

  @InitBinder("housekeeper")
  private void initBinder(WebDataBinder binder) {
    binder.addValidators(cepValidator);
  }

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
  public String register(@RequestParam MultipartFile image, @Valid Housekeeper housekeeper, BindingResult result)
      throws IOException {
    if (result.hasErrors()) {
      return "admin/housekeepers/form";
    }

    var filename = fileService.save(image);
    housekeeper.setPhotograph(filename);

    var cep = housekeeper.getCep();
    var address = viaCepService.getAddressByCep(cep);
    var ibgeCode = address.getIbge();
    housekeeper.setIbgeCode(ibgeCode);

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
  public String update(@RequestParam MultipartFile image, @PathVariable Long id, @Valid Housekeeper housekeeper,
      BindingResult result) throws IOException {
    if (result.hasErrors()) {
      return "admin/housekeepers/form";
    }

    var currentHousekeeper = repository.getById(id);

    if (image.isEmpty()) {
      housekeeper.setPhotograph(currentHousekeeper.getPhotograph());
    } else {
      var filename = fileService.save(image);
      housekeeper.setPhotograph(filename);
    }

    var cep = housekeeper.getCep();
    var address = viaCepService.getAddressByCep(cep);
    var ibgeCode = address.getIbge();
    housekeeper.setIbgeCode(ibgeCode);

    repository.save(housekeeper);

    return "redirect:/admin/diaristas";
  }

  @GetMapping("/{id}/excluir")
  public String delete(@PathVariable Long id) {
    repository.deleteById(id);

    return "redirect:/admin/diaristas";
  }
}
