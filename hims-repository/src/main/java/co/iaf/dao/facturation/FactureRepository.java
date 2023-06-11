package co.iaf.dao.facturation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.facturation.Facture;

public interface FactureRepository extends JpaRepository<Facture, String>{

}
