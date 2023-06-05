package co.iaf.service.parametrage;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import co.iaf.dao.admission.BatimentRepository;
import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.parametrage.DomaineRepository;
import co.iaf.dao.parametrage.ServiceRepository;
import co.iaf.dao.parametrage.TypeDomaineRepository;
import co.iaf.entity.admission.Batiment;
import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.identification.QrCodePatient;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.Services;
import co.iaf.entity.parametrage.TypeDomaine;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParametrageServiceImpl implements ParametrageService {

	private ServiceRepository serviceRepository;
	private BatimentRepository batimentRepository;
	private TypeDomaineRepository typeDomaineRepository;
	private DomaineRepository domaineRepository;
	

	public ParametrageServiceImpl(ServiceRepository serviceRepository, BatimentRepository batimentRepository,
			TypeDomaineRepository typeDomaineRepository, DomaineRepository domaineRepository) {
		this.serviceRepository = serviceRepository;
		this.batimentRepository = batimentRepository;
		this.typeDomaineRepository = typeDomaineRepository;
		this.domaineRepository = domaineRepository;
	}

	/* ============ GESTION DES SERVICES ============= */

	// créer d'un nouveau service
	public Services addNewService(Services service, Long batimentId, Long typeDomaineId, Long serviceId) {
		Batiment batiment = this.batimentRepository.findById(batimentId)
				.orElseThrow(() -> new ResourceNotFoundException("Batiment:", "Batiment Id", batimentId));
		TypeDomaine typeDomaine = this.typeDomaineRepository.findById(typeDomaineId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Domaine:", "Type Domaine Id", typeDomaineId));
		Services serviceParent = this.serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Parent:", "Service Id", serviceId));

		service.setBatiment(batiment);
		service.setTypeDomaine(typeDomaine);
		service.setServiceParent(serviceParent);

		return this.serviceRepository.save(service);
	}

	// mis à jour d'un service
	public Services updateService(Long serviceId, Services service) {
		if (getServiceById(serviceId) != null) {
			service.setId(serviceId);
			return this.serviceRepository.save(service);
		}
		return null;
	}

	// suppression d'un service
	public void deleteService(Long serviceId) {
		this.serviceRepository.deleteById(serviceId);
	}

	// recupérer un service par son id
	public Services getServiceById(Long serviceId) {
		return this.serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "Service Id", serviceId));
	}

	// recupérer tous les services
	public List<Services> getAllServices() {
		return this.serviceRepository.findAll();
	}

	@Override
	public Domaine addNewDomaine(Domaine domaine) {
		
			Domaine domain = this.domaineRepository.save(domaine);
			
			return domain;
			
	}

	@Override
	public TypeDomaine addNewTypeDomaine(TypeDomaine typeDomaine) {
		TypeDomaine typedomain = this.typeDomaineRepository.save(typeDomaine);
		
		return typedomain;
	}
	
	

}
