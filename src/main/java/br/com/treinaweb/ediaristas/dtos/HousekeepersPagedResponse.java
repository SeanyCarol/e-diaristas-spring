package br.com.treinaweb.ediaristas.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.treinaweb.ediaristas.models.Housekeeper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousekeepersPagedResponse {

  private List<Housekeeper> housekeepers;

  @JsonProperty("quantidade_diaristas")
  private Long quantityHousekeepers;
}
