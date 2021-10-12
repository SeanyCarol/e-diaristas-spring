package br.com.treinaweb.ediaristas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.models.Housekeeper;

public interface HousekeeperRepository extends JpaRepository<Housekeeper, Long> {

}
