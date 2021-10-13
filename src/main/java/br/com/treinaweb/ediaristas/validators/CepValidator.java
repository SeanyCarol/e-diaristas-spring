package br.com.treinaweb.ediaristas.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.treinaweb.ediaristas.models.Housekeeper;
import br.com.treinaweb.ediaristas.services.ViaCepService;

@Component
public class CepValidator implements Validator {

  @Autowired
  private ViaCepService viaCepService;

  @Override
  public boolean supports(Class<?> clazz) {
    return Housekeeper.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    var housekeeper = (Housekeeper) target;

    try {
      var cep = housekeeper.getCep();
      viaCepService.getAddressByCep(cep);
    } catch (RuntimeException e) {
      errors.rejectValue("cep", null, e.getMessage());
    }
  }

}
