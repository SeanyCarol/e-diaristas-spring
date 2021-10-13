package br.com.treinaweb.ediaristas.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.treinaweb.ediaristas.dtos.ViaCepResponse;
import br.com.treinaweb.ediaristas.exceptions.CepInvalidException;
import br.com.treinaweb.ediaristas.exceptions.CepNotFoundException;

@Service
public class ViaCepService {

  public ViaCepResponse getAddressByCep(String cep) {
    var url = "https://viacep.com.br/ws/" + cep + "/json/";

    var httpClient = new RestTemplate();
    ResponseEntity<ViaCepResponse> response;

    try {
      response = httpClient.getForEntity(url, ViaCepResponse.class);
    } catch (HttpClientErrorException.BadRequest ex) {
      throw new CepInvalidException("CEP inválido!");
    }

    if (response.getBody().getCep() == null) {
      throw new CepNotFoundException("CEP não encontrado!");
    }

    return response.getBody();
  }
}
