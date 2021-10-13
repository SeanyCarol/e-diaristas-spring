package br.com.treinaweb.ediaristas.models;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.treinaweb.ediaristas.converters.CepConverter;
import br.com.treinaweb.ediaristas.converters.CpfConverter;
import br.com.treinaweb.ediaristas.converters.TelephoneConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "diarista")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Housekeeper {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @NotNull
  @Size(min = 3, max = 100)
  @Column(nullable = false, length = 100)
  private String fullname;

  @NotNull
  @Size(min = 11, max = 14)
  @CPF
  @Column(nullable = false, unique = true, length = 11)
  @Convert(converter = CpfConverter.class)
  private String cpf;

  @NotNull
  @NotEmpty
  @Email
  @Column(nullable = false, unique = true)
  private String email;

  @NotNull
  @Size(min = 11, max = 15)
  @Column(nullable = false, length = 11)
  @Convert(converter = TelephoneConverter.class)
  private String telephone;

  @NotNull
  @NotEmpty
  @Column(nullable = false)
  private String address;

  @NotNull
  @NotEmpty
  @Column(nullable = false)
  private String number;

  @NotNull
  @NotEmpty
  @Column(nullable = false)
  private String neighborhood;

  @Column(nullable = true)
  private String addressComplement;

  @NotNull
  @Size(min = 8, max = 9)
  @Column(nullable = false, length = 8)
  @Convert(converter = CepConverter.class)
  private String cep;

  @NotNull
  @NotEmpty
  @Column(nullable = false)
  private String city;

  @NotNull
  @NotEmpty
  @Size(min = 2, max = 2)
  @Column(nullable = false, length = 2)
  private String state;

  @NotNull
  @NotEmpty
  @Column(nullable = false)
  private String ibgeCode;

  @Column(nullable = false)
  private String photograph;
}
