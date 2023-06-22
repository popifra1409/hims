package co.iaf.service.grh;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.facturation.CaissierRepository;
import co.iaf.dao.grh.AgentPrescripteurRepository;
import co.iaf.dao.grh.AgentRealisateurRepository;
import co.iaf.entity.grh.AgentPrescripteur;
import co.iaf.entity.grh.AgentRealisateur;
import co.iaf.entity.grh.Caissier;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

	private AgentPrescripteurRepository prescripteurRepo;
	private AgentRealisateurRepository realisateurRepo;
	private CaissierRepository caissierRepo;

	public AgentServiceImpl(AgentPrescripteurRepository prescripteurRepo, AgentRealisateurRepository realisateurRepo,
			CaissierRepository caissierRepo) {
		super();
		this.prescripteurRepo = prescripteurRepo;
		this.realisateurRepo = realisateurRepo;
		this.caissierRepo = caissierRepo;
	}

	@Override
	public AgentPrescripteur addNewPrescripteur(AgentPrescripteur prescripteur, Long superieurId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgentPrescripteur getPrescripteurById(Long agentId) {
		return this.prescripteurRepo.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Prescripteur", "Prescripteur Id", 0));
	}

	@Override
	public AgentPrescripteur updatePrescripteur(Long agentId, AgentPrescripteur prescripteur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrescripteur(AgentPrescripteur prescripteur) {
		// TODO Auto-generated method stub

	}

	@Override
	public AgentRealisateur addNewRealisateur(AgentRealisateur realisateur, Long superieurId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgentRealisateur getRealisateurById(Long agentId) {
		return this.realisateurRepo.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Realisateur", "Realisateur Id", 0));
	}

	@Override
	public AgentRealisateur updateRealisateur(Long agentId, AgentRealisateur realisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRealisateur(AgentRealisateur realisateur) {
		// TODO Auto-generated method stub

	}

	@Override
	public Caissier addNewCaissier(Caissier caisse, Long superieurId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caissier getCaissierById(Long caissierId) {
		return this.caissierRepo.findById(caissierId)
				.orElseThrow(() -> new ResourceNotFoundException("Caissier", "Caissier Id", 0));
	}

}
