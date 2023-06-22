package co.iaf.service.grh;

import co.iaf.entity.grh.AgentPrescripteur;
import co.iaf.entity.grh.AgentRealisateur;
import co.iaf.entity.grh.Caissier;

public interface AgentService {

	// ========= GESTION DES PRESCRIPTEURS =======/
	// créer un nouveau prescripteur
	public AgentPrescripteur addNewPrescripteur(AgentPrescripteur prescripteur, Long superieurId);

	// recuperer un prescripteur par son Id
	public AgentPrescripteur getPrescripteurById(Long agentId);

	// mettre à jour un prescripteur
	public AgentPrescripteur updatePrescripteur(Long agentId, AgentPrescripteur prescripteur);

	// supprimer un prescripteur
	public void deletePrescripteur(AgentPrescripteur prescripteur);

	// ========= GESTION DES REALISATEURS =======/
	// créer un nouveau realisateur
	public AgentRealisateur addNewRealisateur(AgentRealisateur realisateur, Long superieurId);

	// recuperer un realisateur par son Id
	public AgentRealisateur getRealisateurById(Long agentId);

	// mettre à jour un prescripteur
	public AgentRealisateur updateRealisateur(Long agentId, AgentRealisateur realisateur);

	// supprimer un prescripteur
	public void deleteRealisateur(AgentRealisateur realisateur);

	// ========= GESTION DES CAISSIERS =======/
	// crer un nouveau caissier
	public Caissier addNewCaissier(Caissier caisse, Long superieurId);

	// recuperer un ciassier par son Id
	public Caissier getCaissierById(Long caissierId);

}
