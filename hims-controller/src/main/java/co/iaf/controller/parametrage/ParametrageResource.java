package co.iaf.controller.parametrage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.iaf.entity.identification.Patient;
import co.iaf.entity.parametrage.Domaine;
import co.iaf.entity.parametrage.Services;
import co.iaf.entity.parametrage.TypeDomaine;
import co.iaf.payloads.ApiResponse;
import co.iaf.service.parametrage.ParametrageService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class ParametrageResource {

	private ParametrageService parametrageService;

	public ParametrageResource(ParametrageService parametrageService) {
		this.parametrageService = parametrageService;
	}

	// liste des services
	@GetMapping(path = "/services/all")
	public ResponseEntity<?> listBatiments() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Services> listservices = parametrageService.getAllServices();
		if (!listservices.isEmpty()) {
			map.put("status", 1);
			map.put("data", listservices);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// ajouter un service
	@PostMapping(path = "/services/{batimentId}/{typeDomaineId}/{serviceId}")
	public ResponseEntity<?> saveService(@PathVariable(name = "batimentId") Long batimentId,
			@PathVariable(name = "typeDomaineId") Long typeDomaineId, @PathVariable(name = "serviceId") Long serviceId,
			@RequestBody Services service) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		parametrageService.addNewService(service, batimentId, typeDomaineId, serviceId);
		map.put("status", 1);
		map.put("data", new ApiResponse("Service créé avec succès !", true));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un service par son id
	@GetMapping(path = "/services/{id}")
	public ResponseEntity<?> getServiceById(@PathVariable(name = "id") Long serviceId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Services service = parametrageService.getServiceById(serviceId);
			map.put("status", 1);
			map.put("data", service);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun service trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du service
	@PutMapping("/services/update/{id}")
	public ResponseEntity<?> updateService(@PathVariable(name = "id") Long serviceId, @RequestBody Services service) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Services serviceUpdate = parametrageService.updateService(serviceId, service);
			map.put("status", 1);
			map.put("data", serviceUpdate);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune information Trouvée !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	// ajouter un nouveau Domaine 
		@PostMapping(path = "/domaines/save")
		public ResponseEntity<?> saveDomaine(@RequestBody Domaine domaine) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			parametrageService.addNewDomaine(domaine);
			map.put("status", 1);
			map.put("message", new ApiResponse("Domaine créé avec succès !", true));
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		
		// ajouter un nouveau Domaine 
				@PostMapping(path = "/types-domaines/save")
				public ResponseEntity<?> saveTypeDomaine(@RequestBody TypeDomaine typeDomaine) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					parametrageService.addNewTypeDomaine(typeDomaine);
					map.put("status", 1);
					map.put("message", new ApiResponse("Type Domaine créé avec succès !", true));
					return new ResponseEntity<>(map, HttpStatus.CREATED);
				}
}
