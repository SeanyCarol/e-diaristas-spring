package br.com.treinaweb.ediaristas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.models.Housekeeper;
import br.com.treinaweb.ediaristas.repositories.HousekeeperRepository;
import br.com.treinaweb.ediaristas.services.ViaCepService;

@RestController
@RequestMapping("/api/diaristas-cidade")
public class HousekeeperRestController {

  @Autowired
  private HousekeeperRepository repository;

  @Autowired
  private ViaCepService viaCepService;

  @GetMapping
  public List<Housekeeper> findHouseKeeperByCep(@RequestParam String cep) {
    var address = viaCepService.getAddressByCep(cep);
    var ibgeCode = address.getIbge();

    return repository.findByIbgeCode(ibgeCode);
  }
}