package br.com.treinaweb.ediaristas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.dtos.HousekeepersPagedResponse;
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
  public HousekeepersPagedResponse findHouseKeeperByCep(@RequestParam String cep) {
    var address = viaCepService.getAddressByCep(cep);
    var ibgeCode = address.getIbge();

    var pageable = PageRequest.of(0, 6);
    var housekeepers = repository.findByIbgeCode(ibgeCode, pageable);

    var quantityHousekeepers = housekeepers.getTotalElements() > 6 ? housekeepers.getTotalElements() - 6 : 0;

    return new HousekeepersPagedResponse(housekeepers.getContent(), quantityHousekeepers);
  }
}
