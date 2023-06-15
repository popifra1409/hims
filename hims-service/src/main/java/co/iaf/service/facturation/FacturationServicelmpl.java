package co.iaf.service.facturation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.facturation.FactureRepository;
import co.iaf.dao.facturation.ImputationRepository;
import co.iaf.dao.facturation.PrestationRepository;
import co.iaf.entity.facturation.Facture;
import co.iaf.entity.facturation.Imputation;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.facturation.PrestationRegistration;
import co.iaf.entity.grh.AgentPrescripteur;
import co.iaf.entity.grh.AgentRealisateur;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.ServiceDemandeur;
import co.iaf.entity.parametrage.ServiceRealisateur;
import co.iaf.service.grh.AgentService;
import co.iaf.service.identification.IdentificationService;
import co.iaf.service.parametrage.ParametrageService;

@Service
@Transactional
public class FacturationServicelmpl implements FacturationService {

	private PrestationRepository prestationRepo;
	private ImputationRepository imputationRepo;
	private FactureRepository factureRepo;

	@Autowired
	private ParametrageService parametrageService;
	@Autowired
	private IdentificationService identificationService;
	@Autowired
	private AgentService agentService;

	public FacturationServicelmpl(PrestationRepository prestationRepo, ImputationRepository imputationRepo,
			FactureRepository factureRepo) {
		this.prestationRepo = prestationRepo;
		this.imputationRepo = imputationRepo;
		this.factureRepo = factureRepo;
	}

	@Override
	public Prestation addNewPrestation(Prestation prestation, Long domaineId, Long imputationId) {

		Imputation imputation = getImputationById(imputationId);
		Domaine domaine = parametrageService.getDomaineById(domaineId);

		prestation.setImputation(imputation);
		prestation.setDomaine(domaine);

		Prestation newPrestation = this.prestationRepo.save(prestation);

		return newPrestation;
	}

	// recuperer une prestation par son id
	@Override
	public Prestation getPrestationById(Long prestationId) {
		Prestation prestation = this.prestationRepo.findById(prestationId)
				.orElseThrow(() -> new ResourceNotFoundException("Prestation", "Prestation Id", 0));
		return prestation;
	}

	@Override
	public List<Prestation> getAllPrestation() {
		return this.prestationRepo.findAll();
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

		return this.prestationRepo.save(prest);
	}

	@Override
	public void deletePrestation(Prestation prestationId) {
		this.prestationRepo.delete(prestationId);

	}

	@Override
	public List<Prestation> getPrestationsByDomaine(Long domaineId) {
		return prestationRepo.findByDomaine(parametrageService.getDomaineById(domaineId));
	}

	@Override
	public List<Prestation> getPrestationsByImputation(Long imputationId) {
		return prestationRepo.findByImputation(getImputationById(imputationId));
	}

	@Override
	public Imputation addNewImputation(Imputation imputation) {
		Collection<Prestation> prestations = imputation.getPrestations();
		Imputation newImputation = this.imputationRepo.save(imputation);

		if (prestations != null && !prestations.isEmpty()) {
			for (Prestation prestation : prestations) {
				prestation.setImputation(newImputation); // Définir l'imputation pour chaque prestation
			}
			this.prestationRepo.saveAll(prestations); // Enregistrer tous les imputations en une seule fois
		}
		return newImputation;
	}

	@Override
	public Imputation getImputationById(Long imputationId) {
		Imputation imputation = this.imputationRepo.findById(imputationId)
				.orElseThrow(() -> new ResourceNotFoundException("Imputation", "Imputation Id", 0));
		return imputation;
	}

	@Override
	public List<Imputation> getAllImputation() {
		return this.imputationRepo.findAll();
	}

	@Override
	public Imputation updateImputation(Long imputationId, Imputation imputation) {
		Imputation i = getImputationById(imputationId);
		i.setNmroCompte(imputation.getNmroCompte());
		i.setLibelle(imputation.getLibelle());
		i.setCommentaire(imputation.getCommentaire());

		return this.imputationRepo.save(i);
	}

	@Override
	public void deleteImputation(Imputation imputationId) {
		this.imputationRepo.delete(imputationId);

	}

	@Override
	public Facture addNewFacture(Facture facture, String patientId) {
		Patient patient = identificationService.getPatientById(patientId);
		Collection<PrestationRegistration> prestationRegistrations = facture.getPrestationRegistration();

		Facture newFacture = new Facture();
		newFacture.setIntitule(facture.getIntitule());
		newFacture.setPatient(patient);

		newFacture.getPrestationRegistration().addAll(prestationRegistrations.stream().map(prestationReg -> {
			Prestation prestation = getPrestationById(prestationReg.getPrestation().getId());
			AgentPrescripteur agentPrescripteur = agentService
					.getPrescripteurById(prestationReg.getAgentPrescripteur().getId());
			AgentRealisateur agentRealisateur = agentService
					.getRealisateurById(prestationReg.getAgentRealisateur().getId());
			ServiceDemandeur demandeur = parametrageService
					.getServiceDemandeurById(prestationReg.getServiceDemandeur().getId());
			ServiceRealisateur realisateur = parametrageService
					.getServiceRealisateurById(prestationReg.getServiceRealisateur().getId());

			PrestationRegistration newPrestationRegistration = new PrestationRegistration();
			newPrestationRegistration.setPrestation(prestation);
			newPrestationRegistration.setAgentPrescripteur(agentPrescripteur);
			newPrestationRegistration.setAgentRealisateur(agentRealisateur);
			newPrestationRegistration.setServiceDemandeur(demandeur);
			newPrestationRegistration.setServiceRealisateur(realisateur);
			newPrestationRegistration.setQuantite(prestationReg.getQuantite());
			newPrestationRegistration.setTicketModerateur(prestationReg.getTicketModerateur());
			newPrestationRegistration.setTarif(prestationReg.getTarif());
			newPrestationRegistration.setTva(prestationReg.getTva());

			return newPrestationRegistration;
		}).collect(Collectors.toList()));

		return this.factureRepo.save(newFacture);
	}

}
