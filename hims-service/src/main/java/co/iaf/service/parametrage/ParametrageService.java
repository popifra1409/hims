package co.iaf.service.parametrage;

import java.util.List;

import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.Services;
import co.iaf.entity.parametrage.TypeDomaine;

public interface ParametrageService {

	/* ============ GESTION DES SERVICES ============= */

	// créer service
	public Services addNewService(Services service, Long batimentId, Long typeDomaineId, Long serviceId);

	// mis à jour service
	public Services updateService(Long serviceId, Services service);

	// suppression d'un service
	public void deleteService(Long serviceId);

	// recupérer un service par son Id
	public Services getServiceById(Long serviceId);

	// recupérer tous les services
	public List<Services> getAllServices();
	
	// créer un domaine
	public Domaine addNewDomaine(Domaine domaine);
	
	// créer un type domaine
	public TypeDomaine addNewTypeDomaine(TypeDomaine typeDomaine);
	
	
	
	
}
