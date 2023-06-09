package co.iaf.service.facturation;

import java.util.List;

import co.iaf.entity.facturation.Imputation;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.Services;

public interface FacturationService {
	
	public Prestation addNewPrestation(Prestation prestation, Long domaineParentId, Long imputationId);
	
	public Prestation getPrestationById(Long prestationId);
	
	public List<Prestation> getAllPrestation();
	
	//recuperer un domaine par son id
	public Domaine getDomaineById(Long domaineId);
	
	// mis à d'une prestation
	public Prestation updatePrestation(Long prestationId, Prestation prestation);

		// suppression d'une prestation
	public void deletePrestation(Prestation prestationId);
	
	
	
	public Imputation addNewImputation(Imputation imputation);
	
	public Imputation getImputationById(Long imputationId);
	
	public List<Imputation> getAllImputation();
	
	// mis à jour d'une imputation
	public Imputation updateImputation(Long imputationId, Imputation imputation);

		// suppression d'une imputation
	public void deleteImputation(Imputation imputationId);

}
