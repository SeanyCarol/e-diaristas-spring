package br.com.treinaweb.ediaristas.converters;

import javax.persistence.AttributeConverter;

public class TelephoneConverter implements AttributeConverter<String, String> {

  @Override
  public String convertToDatabaseColumn(String telephone) {
    return telephone.replaceAll("[() -]", "");
  }

  @Override
  public String convertToEntityAttribute(String telephone) {
    return telephone;
  }
}
