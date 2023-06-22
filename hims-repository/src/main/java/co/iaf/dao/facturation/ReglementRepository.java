package co.iaf.dao.facturation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.facturation.Reglement;

public interface ReglementRepository extends JpaRepository<Reglement, String>{

}
