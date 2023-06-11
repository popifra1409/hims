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
import co.iaf.dao.parametrage.TypeDomaineRepository;
import co.iaf.entity.admission.Batiment;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.Services;
import co.iaf.entity.parametrage.TypeDomaine;

@Service
@Transactional
public class ParametrageServiceImpl implements ParametrageService {

	private ServiceAttacheRepository serviceAttacheRepository;
	private BatimentRepository batimentRepository;
	private TypeDomaineRepository typeDomaineRepository;
	private DomaineRepository domaineRepository;
	private PrestationRepository prestationRepository;

	public ParametrageServiceImpl(ServiceAttacheRepository serviceAttacheRepository, BatimentRepository batimentRepository,
			TypeDomaineRepository typeDomaineRepository, DomaineRepository domaineRepository,
			PrestationRepository prestationRepository) {
		this.serviceAttacheRepository = serviceAttacheRepository;
		this.batimentRepository = batimentRepository;
		this.typeDomaineRepository = typeDomaineRepository;
		this.domaineRepository = domaineRepository;
		this.prestationRepository = prestationRepository;
	}

	/* ============ GESTION DES SERVICES ============= */

	// créer d'un nouveau service
	public Services addNewService(Services service, Long batimentId, Long typeDomaineId, Long serviceId) {
		Batiment batiment = this.batimentRepository.findById(batimentId)
				.orElseThrow(() -> new ResourceNotFoundException("Batiment:", "Batiment Id", batimentId));
		TypeDomaine typeDomaine = this.typeDomaineRepository.findById(typeDomaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Domaine:", "Type Domaine Id", typeDomaineId));
		Services serviceParent = this.serviceAttacheRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Parent:", "Service Id", serviceId));

		/*
		 * service.setBatiment(batiment); service.setTypeDomaine(typeDomaine);
		 * service.setServiceParent(serviceParent);
		 */

		return this.serviceAttacheRepository.save(service);
	}

	// mis à jour d'un service
	public Services updateService(Long serviceId, Services service) {
		if (getServiceById(serviceId) != null) {
			service.setId(serviceId);
			return this.serviceAttacheRepository.save(service);
		}
		return null;
	}

	// suppression d'un service
	public void deleteService(Long serviceId) {
		this.serviceAttacheRepository.deleteById(serviceId);
	}

	// recupérer un service par son id
	public Services getServiceById(Long serviceId) {
		return this.serviceAttacheRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "Service Id", serviceId));
	}

	// recupérer tous les services
	public List<Services> getAllServices() {
		return this.serviceAttacheRepository.findAll();
	}

	/* =======================GESTION DES DOMAINES============================ */
	// créer un nouveau domaine
	@Override
	public Domaine addNewDomaine(Domaine domaine, Long domaineParentId, Long typeDomaineId) {
		// on recupère le type domaine auquel appartient le domaine
		TypeDomaine typeDomaine = getTypeDomaineById(typeDomaineId);
		//on recupère le domaine Parent s'il existe
		Domaine domaineParent = getDomaineById(domaineParentId);
		// on recupère les prestations associées au domaine
		Collection<Prestation> prestations = domaine.getPrestations();

		// on charge le type domaine et le domaine parent
		domaine.setTypeDomaine(typeDomaine);
		domaine.setDomaineParent(domaineParent);

		Domaine newDomaine = this.domaineRepository.save(domaine);

		if (prestations != null && !prestations.isEmpty()) {
			for (Prestation prestation : prestations) {
				prestation.setDomaine(newDomaine);
			}
			this.prestationRepository.saveAll(prestations); // Enregistrer tous les domaines en une seule fois
		}

		return newDomaine;
	}

	// récupérer un domaine par son id
	@Override
	public Domaine getDomaineById(Long domaineId) {
		Domaine domaine = this.domaineRepository.findById(domaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Domaine", "Domaine Id", 0));
		return domaine;
	}

	// recuperer tout les domaines
	@Override
	public List<Domaine> getAllDomaines() {
		return this.domaineRepository.findAll();
	}

	// supprimer un domaine
	@Override
	public void deleteDomaine(Domaine domaineId) {
		this.domaineRepository.delete(domaineId);

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

		return this.domaineRepository.save(d);
	}

	/*
	 * ====================Gestion DES TYPES
	 * DOMAINES================================
	 */

	// créer un nouveau type domaine
	@Override
	public TypeDomaine addNewTypeDomaine(TypeDomaine typeDomaine) {
		Collection<Domaine> domaines = typeDomaine.getDomaines();
		TypeDomaine newTypedomaine = this.typeDomaineRepository.save(typeDomaine);

		if (domaines != null && !domaines.isEmpty()) {
			for (Domaine domaine : domaines) {
				domaine.setTypeDomaine(newTypedomaine); // Définir le typeDomaine pour chaque domaine
			}
			this.domaineRepository.saveAll(domaines); // Enregistrer tous les domaines en une seule fois
		}
		return newTypedomaine;
	}

	// recuperer un type domaine par son id
	@Override
	public TypeDomaine getTypeDomaineById(Long typeDomaineId) {
		TypeDomaine typedomaine = this.typeDomaineRepository.findById(typeDomaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Domaine:", "TypeDomaine Id", 0));
		return typedomaine;
	}

	// recuperer tout les types domaines
	@Override
	public List<TypeDomaine> getAllTypeDomaines() {
		return this.typeDomaineRepository.findAll();
	}

	// supprimer un type domaine
	@Override
	public void deleteTypeDomaine(TypeDomaine typeDomaineId) {
		this.typeDomaineRepository.delete(typeDomaineId);

	}

	// mise à jour d'un type domaine
	@Override
	public TypeDomaine updateTypeDomaine(Long typeDomaineId, TypeDomaine typeDomaine) {
		TypeDomaine td = getTypeDomaineById(typeDomaineId);
		td.setCodeType(typeDomaine.getCodeType());
		td.setLibelleType(typeDomaine.getLibelleType());

		return this.typeDomaineRepository.save(td);
	}
}
