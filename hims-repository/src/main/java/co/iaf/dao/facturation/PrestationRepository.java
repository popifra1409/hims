package co.iaf.dao.facturation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.facturation.Prestation;

public interface PrestationRepository extends JpaRepository<Prestation, Long>{

}
