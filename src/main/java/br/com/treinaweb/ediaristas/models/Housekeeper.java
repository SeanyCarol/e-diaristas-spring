package br.com.treinaweb.ediaristas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

  @Column(nullable = false, length = 100)
  private String fullname;

  @Column(nullable = false, unique = true, length = 11)
  private String cpf;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, length = 11)
  private String telephone;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private String neighborhood;

  @Column(nullable = true)
  private String addressComplement;

  @Column(nullable = false, length = 8)
  private String cep;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false, length = 2)
  private String state;

  @Column(nullable = false)
  private String ibgeCode;
}
