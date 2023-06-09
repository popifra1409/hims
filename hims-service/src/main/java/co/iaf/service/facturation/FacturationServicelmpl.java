package co.iaf.service.facturation;



import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.facturation.ImputationRepository;
import co.iaf.dao.facturation.PrestationRepository;
import co.iaf.dao.parametrage.DomaineRepository;
import co.iaf.entity.facturation.Imputation;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.Services;
import co.iaf.entity.parametrage.TypeDomaine;
import co.iaf.entity.pharmacie.Produit;


@Service
@Transactional
public class FacturationServicelmpl implements FacturationService {
	
	private PrestationRepository prestationRepository;
	private DomaineRepository domaineRepository;
	private ImputationRepository imputationRepository;
	
	
	public FacturationServicelmpl(PrestationRepository prestationRepository, DomaineRepository domaineRepository, ImputationRepository imputationRepository) {
		this.prestationRepository = prestationRepository;
		this.domaineRepository = domaineRepository;
		this.imputationRepository = imputationRepository;
	}
	
	/* =======================GESTION DES PRESTATIONS============================ */
	
	// créer une nouvelle prestation
	@Override
	public Prestation addNewPrestation(Prestation prestation, Long domaineParentId, Long imputationId) {
		
		Imputation imputation = getImputationById(imputationId);
	    Domaine domaineParent = getDomaineById(domaineParentId);
	

	    prestation.setImputation(imputation);
	    prestation.setDomaine(domaineParent);

	    Prestation newPrestation = this.prestationRepository.save(prestation);
	 // Récupérer les IDs associés
	    Long newPrestationId = newPrestation.getId();
	    Long newImputationId = newPrestation.getImputation().getId();
	    Long newDomaineParentId = newPrestation.getDomaine().getId();
	    
	    // Faire ce que vous voulez avec les IDs récupérés
	    
	    return newPrestation;
		}
	// recuperer une prestation par son id
	@Override
	public Prestation getPrestationById(Long prestationId) {
		Prestation prestation = this.prestationRepository.findById(prestationId)
				.orElseThrow(() -> new ResourceNotFoundException("Prestation", "Prestation Id", 0));
		return prestation;
	}

	public Domaine getDomaineById(Long domaineId) {
		Domaine domaine = this.domaineRepository.findById(domaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Domaine", "Domaine Id", 0));
		return domaine;
	}

	@Override
	public Imputation addNewImputation(Imputation imputation) {
		Collection<Prestation> prestations = imputation.getPrestations();
		Imputation newImputation = this.imputationRepository.save(imputation);

		if (prestations != null && !prestations.isEmpty()) {
			for (Prestation prestation : prestations) {
				prestation.setImputation(newImputation); // Définir l'imputation pour chaque prestation
			}
			this.prestationRepository.saveAll(prestations); // Enregistrer tous les imputations en une seule fois
		}
		return newImputation;
	}

	@Override
	public Imputation getImputationById(Long imputationId) {
		Imputation imputation = this.imputationRepository.findById(imputationId)
				.orElseThrow(() -> new ResourceNotFoundException("Imputation", "Imputation Id", 0));
		return imputation;
	}

	@Override
	public List<Prestation> getAllPrestation() {
		return this.prestationRepository.findAll();
	}

	@Override
	public Prestation updatePrestation(Long prestationId, Prestation prestation) {
		Prestation prest = getPrestationById(prestationId);
		prest.setCodePrestation(prestation.getCodePrestation());
		prest.setDesignationUsuelle(prestation.getDesignationUsuelle());
		prest.setDesingationConventionnelle(prestation.getDesingationConventionnelle());
		prest.setTrafiBase(prestation.getTrafiBase());
		prest.setDesactive(prestation.isDesactive());
		prest.setMasque(prestation.isMasque());
		prest.setTarifFixe(prestation.getTarifFixe());
		prest.setLettreCle(prestation.getLettreCle());
		prest.setCoefficient(prestation.getCoefficient());
		prest.setActeHospit(prestation.isActeHospit());
		prest.setExonere(prestation.isExonere());
		prest.setQuotepart(prestation.getQuotepart());
		prest.setNbrePrestParJour(prestation.getNbrePrestParJour());
		prest.setNbreTube(prestation.getNbreTube());
		prest.setDelaiExecution(prestation.getDelaiExecution());
		prest.setCommentaire(prestation.getCommentaire());
		prest.setTarifFormule(prestation.isTarifFormule());
		prest.setQuantifiable(prestation.isQuantifiable());
		prest.setProduitpharmacie(prestation.isProduitpharmacie());

		return this.prestationRepository.save(prest);
	}

	@Override
	public void deletePrestation(Prestation prestationId) {
		this.prestationRepository.delete(prestationId);
		
	}

	@Override
	public List<Imputation> getAllImputation() {
		return this.imputationRepository.findAll();
	}

	@Override
	public Imputation updateImputation(Long imputationId, Imputation imputation) {
		Imputation i = getImputationById(imputationId);
		i.setNmroCompte(imputation.getNmroCompte());
		i.setLibelle(imputation.getLibelle());
		i.setCommentaire(imputation.getCommentaire());
		
		return this.imputationRepository.save(i);
	}

	@Override
	public void deleteImputation(Imputation imputationId) {
		this.imputationRepository.delete(imputationId);
		
	}



	
	}


