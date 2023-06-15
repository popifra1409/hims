package co.iaf.service.grh;

import co.iaf.entity.grh.AgentPrescripteur;
import co.iaf.entity.grh.AgentRealisateur;

public interface AgentService {

	// ========= GESTION DES PRESCRIPTEURS =======/
	// créer un nouveau prescripteur
	public AgentPrescripteur addNewPrescripteur(AgentPrescripteur prescripteur);

	// recuperer un prescripteur par son Id
	public AgentPrescripteur getPrescripteurById(Long agentId);

	// mettre à jour un prescripteur
	public AgentPrescripteur updatePrescripteur(Long agentId, AgentPrescripteur prescripteur);

	// supprimer un prescripteur
	public void deletePrescripteur(AgentPrescripteur prescripteur);

	// ========= GESTION DES REALISATEURS =======/
	// créer un nouveau realisateur
	public AgentRealisateur addNewRealisateur(AgentRealisateur realisateur);

	// recuperer un realisateur par son Id
	public AgentRealisateur getRealisateurById(Long agentId);

	// mettre à jour un prescripteur
	public AgentRealisateur updateRealisateur(Long agentId, AgentRealisateur realisateur);

	// supprimer un prescripteur
	public void deleteRealisateur(AgentRealisateur realisateur);

}
