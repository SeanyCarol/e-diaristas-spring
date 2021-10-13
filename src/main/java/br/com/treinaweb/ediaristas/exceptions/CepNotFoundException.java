package br.com.treinaweb.ediaristas.exceptions;

public class CepNotFoundException extends RuntimeException {

  public CepNotFoundException(String message) {
    super(message);
  }
}
