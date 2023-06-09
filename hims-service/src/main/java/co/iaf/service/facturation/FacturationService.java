package co.iaf.service.facturation;

import java.util.List;

import co.iaf.entity.facturation.Imputation;
import co.iaf.entity.facturation.Prestation;

public interface FacturationService {
	/* ============ GESTION DES PRESTATIONS =============== */

	// ajouter une nouvelle prestation
	public Prestation addNewPrestation(Prestation prestation, Long domaineId, Long imputationId);

	// recuperer une prestation par son Id
	public Prestation getPrestationById(Long prestationId);

	// lister toutes les prestations
	public List<Prestation> getAllPrestation();

	// mis à jour d'une prestation
	public Prestation updatePrestation(Long prestationId, Prestation prestation);

	// suppression d'une prestation
	public void deletePrestation(Prestation prestationId);

	// lister toutes les prestations d'un domaine
	public List<Prestation> getPrestationsByDomaine(Long domaineId);
	
	// lister toutes les prestations d'une imputation
	public List<Prestation> getPrestationsByImputation(Long imputationId);
	
	
	/* ============ GESTION DES IMPUTATIONS =============== */

	public Imputation addNewImputation(Imputation imputation);

	public Imputation getImputationById(Long imputationId);

	public List<Imputation> getAllImputation();

	// mis à jour d'une imputation
	public Imputation updateImputation(Long imputationId, Imputation imputation);

	// suppression d'une imputation
	public void deleteImputation(Imputation imputationId);

}
