package co.iaf.service.parametrage;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.dao.admission.BatimentRepository;
import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.facturation.PrestationRepository;
import co.iaf.dao.parametrage.DomaineRepository;
import co.iaf.dao.parametrage.ServiceAttacheRepository;
import co.iaf.dao.parametrage.ServiceDemandeurRepository;
import co.iaf.dao.parametrage.ServiceRealisateurRepository;
import co.iaf.dao.parametrage.TypeDomaineRepository;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.ServiceDemandeur;
import co.iaf.entity.parametrage.ServiceRealisateur;
import co.iaf.entity.parametrage.TypeDomaine;

@Service
@Transactional
public class ParametrageServiceImpl implements ParametrageService {

	private ServiceAttacheRepository serviceAttacheRepo;
	private ServiceDemandeurRepository serviceDemandeurRepo;
	private ServiceRealisateurRepository serviceRealisateurRepo;
	private BatimentRepository batimentRepo;
	private TypeDomaineRepository typeDomaineRepo;
	private DomaineRepository domaineRepo;
	private PrestationRepository prestationRepo;

	public ParametrageServiceImpl(ServiceAttacheRepository serviceAttacheRepo,
			ServiceDemandeurRepository serviceDemandeurRepo, ServiceRealisateurRepository serviceRealisateurRepo,
			BatimentRepository batimentRepo, TypeDomaineRepository typeDomaineRepo, DomaineRepository domaineRepo,
			PrestationRepository prestationRepo) {
		super();
		this.serviceAttacheRepo = serviceAttacheRepo;
		this.serviceDemandeurRepo = serviceDemandeurRepo;
		this.serviceRealisateurRepo = serviceRealisateurRepo;
		this.batimentRepo = batimentRepo;
		this.typeDomaineRepo = typeDomaineRepo;
		this.domaineRepo = domaineRepo;
		this.prestationRepo = prestationRepo;
	}

	@Override
	public ServiceDemandeur addNewServiceDemandeur(ServiceDemandeur demandeur, Long batimentId, Long typeDomaineId,
			Long serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDemandeur getServiceDemandeurById(Long serviceId) {
		ServiceDemandeur demandeur = this.serviceDemandeurRepo.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Demandeur", "Service Demandeur Id", 0));
		return demandeur;
	}

	@Override
	public ServiceRealisateur addNewServiceRealisateur(ServiceRealisateur realisateur, Long batimentId,
			Long typeDomaineId, Long serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceRealisateur getServiceRealisateurById(Long serviceId) {
		ServiceRealisateur realisateur = this.serviceRealisateurRepo.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Réalisateur", "Service Realisateur Id", 0));
		return realisateur;
	}

	/* =======================GESTION DES DOMAINES============================ */
	// créer un nouveau domaine
	@Override
	public Domaine addNewDomaine(Domaine domaine, Long domaineParentId, Long typeDomaineId) {
		// on recupère le type domaine auquel appartient le domaine
		TypeDomaine typeDomaine = getTypeDomaineById(typeDomaineId);
		// on recupère le domaine Parent s'il existe
		Domaine domaineParent = getDomaineById(domaineParentId);
		// on recupère les prestations associées au domaine
		Collection<Prestation> prestations = domaine.getPrestations();

		// on charge le type domaine et le domaine parent
		domaine.setTypeDomaine(typeDomaine);
		domaine.setDomaineParent(domaineParent);

		Domaine newDomaine = this.domaineRepo.save(domaine);

		if (prestations != null && !prestations.isEmpty()) {
			for (Prestation prestation : prestations) {
				prestation.setDomaine(newDomaine);
			}
			this.prestationRepo.saveAll(prestations); // Enregistrer tous les domaines en une seule fois
		}

		return newDomaine;
	}

	// récupérer un domaine par son id
	@Override
	public Domaine getDomaineById(Long domaineId) {
		Domaine domaine = this.domaineRepo.findById(domaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Domaine", "Domaine Id", 0));
		return domaine;
	}

	// recuperer tout les domaines
	@Override
	public List<Domaine> getAllDomaines() {
		return this.domaineRepo.findAll();
	}

	// supprimer un domaine
	@Override
	public void deleteDomaine(Domaine domaineId) {
		this.domaineRepo.delete(domaineId);

	}

	// mise à jour d'un domaine
	@Override
	public Domaine updateDomaine(Long domaineId, Domaine domaine) {
		Domaine d = getDomaineById(domaineId);
		d.setCompteDomaine(domaine.getCompteDomaine());
		d.setDescription(domaine.getDescription());
		d.setDesignation(domaine.getDesignation());
		d.setLettreCle(domaine.getLettreCle());
		d.setDomaineParent(domaine.getDomaineParent());

		return this.domaineRepo.save(d);
	}

	/*
	 * ====================Gestion DES TYPES
	 * DOMAINES================================
	 */

	// créer un nouveau type domaine
	@Override
	public TypeDomaine addNewTypeDomaine(TypeDomaine typeDomaine) {
		Collection<Domaine> domaines = typeDomaine.getDomaines();
		TypeDomaine newTypedomaine = this.typeDomaineRepo.save(typeDomaine);

		if (domaines != null && !domaines.isEmpty()) {
			for (Domaine domaine : domaines) {
				domaine.setTypeDomaine(newTypedomaine); // Définir le typeDomaine pour chaque domaine
			}
			this.domaineRepo.saveAll(domaines); // Enregistrer tous les domaines en une seule fois
		}
		return newTypedomaine;
	}

	// recuperer un type domaine par son id
	@Override
	public TypeDomaine getTypeDomaineById(Long typeDomaineId) {
		TypeDomaine typedomaine = this.typeDomaineRepo.findById(typeDomaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Domaine:", "TypeDomaine Id", 0));
		return typedomaine;
	}

	// recuperer tout les types domaines
	@Override
	public List<TypeDomaine> getAllTypeDomaines() {
		return this.typeDomaineRepo.findAll();
	}

	// supprimer un type domaine
	@Override
	public void deleteTypeDomaine(TypeDomaine typeDomaineId) {
		this.typeDomaineRepo.delete(typeDomaineId);

	}

	// mise à jour d'un type domaine
	@Override
	public TypeDomaine updateTypeDomaine(Long typeDomaineId, TypeDomaine typeDomaine) {
		TypeDomaine td = getTypeDomaineById(typeDomaineId);
		td.setCodeType(typeDomaine.getCodeType());
		td.setLibelleType(typeDomaine.getLibelleType());

		return this.typeDomaineRepo.save(td);
	}

}
