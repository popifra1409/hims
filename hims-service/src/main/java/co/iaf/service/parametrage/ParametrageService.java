package co.iaf.service.parametrage;

import java.util.List;

import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.ServiceDemandeur;
import co.iaf.entity.parametrage.ServiceRealisateur;
import co.iaf.entity.parametrage.TypeDomaine;

public interface ParametrageService {

	/* ============ GESTION DES SERVICES DEMANDEURS============= */

	// créer service demandeur
	public ServiceDemandeur addNewServiceDemandeur(ServiceDemandeur demandeur, Long batimentId, Long typeDomaineId,
			Long serviceId);

	// recupérer un service demandeur par son id
	public ServiceDemandeur getServiceDemandeurById(Long serviceId);

	/* ============ GESTION DES SERVICES REALISATEURS============= */
	// créer service realisateur
	public ServiceRealisateur addNewServiceRealisateur(ServiceRealisateur realisateur, Long batimentId,
			Long typeDomaineId, Long serviceId);

	// recupérer un service realisateur par son id
	public ServiceRealisateur getServiceRealisateurById(Long serviceId);

	/* =====================GESTION DES DOMAINES========================= */

	// créer un domaine
	public Domaine addNewDomaine(Domaine domaine, Long domaineParentId, Long typeDomaineId);

	// recuperer un domaine par son id
	public Domaine getDomaineById(Long domaineId);

	// liste de tout les domaines
	public List<Domaine> getAllDomaines();

	// suppression d'un domain
	public void deleteDomaine(Domaine domaine);

	// mis à jour d'un domaine
	public Domaine updateDomaine(Long domaineId, Domaine domaine);

	/*
	 * ============================GESTION DES TYPES DOMAINES======================
	 */

	// créer un type domaine
	public TypeDomaine addNewTypeDomaine(TypeDomaine typeDomaine);

	// recuperer un Type domaine par son id
	public TypeDomaine getTypeDomaineById(Long typeDomaineId);

	// liste de tout les domaines
	public List<TypeDomaine> getAllTypeDomaines();

	// suppression d'un domain
	public void deleteTypeDomaine(TypeDomaine typedomaine);

	// mis à jour d'un domaine
	public TypeDomaine updateTypeDomaine(Long typeDomaineId, TypeDomaine typeDomaine);

}
