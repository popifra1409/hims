package co.iaf.dao.facturation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.facturation.Imputation;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.parametrage.Domaine;

public interface PrestationRepository extends JpaRepository<Prestation, Long> {

	// liste des prestations par Domaine
	List<Prestation> findByDomaine(Domaine domaine);

	// liste des prestations par inmputation
	List<Prestation> findByImputation(Imputation imputation);
}
