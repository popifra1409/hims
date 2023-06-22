package co.iaf.service.facturation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.iaf.dao.facturation.ReglementRepository;
import co.iaf.entity.facturation.Facture;
import co.iaf.entity.facturation.Reglement;
import co.iaf.entity.grh.Caissier;
import co.iaf.entity.identification.Patient;
import co.iaf.service.grh.AgentService;
import co.iaf.service.identification.IdentificationService;

@Service
@Transactional
public class ReglementServiceImpl implements ReglementService {

	@Autowired
	private FacturationService facturationService;
	@Autowired
	private IdentificationService identificationService;
	@Autowired
	private AgentService agentService;

	private ReglementRepository reglementRepo;

	public ReglementServiceImpl(ReglementRepository reglementRepo) {
		this.reglementRepo = reglementRepo;
	}

	@Override
	public Reglement addNewReglement(Reglement reglement, String factureId, String patientId, Long caissierId) {
		Facture facture = facturationService.getFactureById(factureId);
		Patient patient = identificationService.getPatientById(patientId);
		Caissier caissier = agentService.getCaissierById(caissierId);

		reglement.setDocument(facture);
		reglement.setPatient(patient);
		reglement.setAgent(caissier);

		return this.reglementRepo.save(reglement);
	}

}
