package co.iaf.controller.parametrage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	/*
	 * // liste des services
	 * 
	 * @GetMapping(path = "/services/all") public ResponseEntity<?> listBatiments()
	 * { Map<String, Object> map = new LinkedHashMap<String, Object>();
	 * List<Services> listservices = parametrageService.getAllServices(); if
	 * (!listservices.isEmpty()) { map.put("status", 1); map.put("data",
	 * listservices); return new ResponseEntity<>(map, HttpStatus.OK); } else {
	 * map.clear(); map.put("status", 0); map.put("message",
	 * "Aucune information trouvée"); return new ResponseEntity<>(map,
	 * HttpStatus.NOT_FOUND); } }
	 * 
	 * // ajouter un service
	 * 
	 * @PostMapping(path = "/services/{batimentId}/{typeDomaineId}/{serviceId}")
	 * public ResponseEntity<?> saveService(@PathVariable(name = "batimentId") Long
	 * batimentId,
	 * 
	 * @PathVariable(name = "typeDomaineId") Long typeDomaineId, @PathVariable(name
	 * = "serviceId") Long serviceId,
	 * 
	 * @RequestBody Services service) { Map<String, Object> map = new
	 * LinkedHashMap<String, Object>(); parametrageService.addNewService(service,
	 * batimentId, typeDomaineId, serviceId); map.put("status", 1); map.put("data",
	 * new ApiResponse("Service créé avec succès !", true)); return new
	 * ResponseEntity<>(map, HttpStatus.CREATED); }
	 * 
	 * // recupérer un service par son id
	 * 
	 * @GetMapping(path = "/services/{id}") public ResponseEntity<?>
	 * getServiceById(@PathVariable(name = "id") Long serviceId) { Map<String,
	 * Object> map = new LinkedHashMap<String, Object>(); try { Services service =
	 * parametrageService.getServiceById(serviceId); map.put("status", 1);
	 * map.put("data", service); return new ResponseEntity<>(map, HttpStatus.OK); }
	 * catch (Exception ex) { map.clear(); map.put("status", 0); map.put("message",
	 * new ApiResponse("Aucun service trouvé", false)); return new
	 * ResponseEntity<>(map, HttpStatus.NOT_FOUND); } }
	 */

	/*
	 * ==================GESTION DES DOMAINES======================================
	 */

	// ajouter un nouveau Domaine

	@PostMapping(path = "/domaines/save/{domaineParentId}/{typeDomaineId}")
	public ResponseEntity<?> saveDomaine(@PathVariable(name = "domaineParentId") Long parentId,
			@PathVariable(name = "typeDomaineId") Long typeId, @RequestBody Domaine domaine) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		parametrageService.addNewDomaine(domaine, parentId, typeId);
		map.put("status", 1);
		map.put("message", new ApiResponse("Domaine créé avec succès !", true));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// mettre à jour les infos du domaine

	@PutMapping("/domaines/update/{id}")
	public ResponseEntity<?> updateDomaine(@PathVariable(name = "id") Long domaineId, @RequestBody Domaine d) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Domaine domaine = parametrageService.updateDomaine(domaineId, d);
			map.put("status", 1);
			map.put("data", domaine);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun Domaine trouvé !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des domaines

	@GetMapping(path = "/domaines/all")
	public ResponseEntity<?> listdomaines() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Domaine> listdomaines = parametrageService.getAllDomaines();
		if (!listdomaines.isEmpty()) {
			map.put("status", 1);
			map.put("data", listdomaines);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune information trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// supprimer un domaine

	@DeleteMapping("/domaines/delete/{id}")
	public ResponseEntity<?> deleteDomaine(@PathVariable Long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Domaine domaine = parametrageService.getDomaineById(id);
			parametrageService.deleteDomaine(domaine);
			map.put("status", 1);
			map.put("message", "Domaine supprimé avec succès !");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun domaine trouvé !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// recupérer un domaine par son id

	@GetMapping(path = "/domaines/{id}")
	public ResponseEntity<?> getDomaineById(@PathVariable(name = "id") Long domaineId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Domaine domaine = parametrageService.getDomaineById(domaineId);
			map.put("status", 1);
			map.put("data", domaine);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun domaine trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * ============================GESTION DES TYPES DOMAINES======================
	 */

	// ajouter un nouveau Type Domaine

	@PostMapping(path = "/types-domaines/save")
	public ResponseEntity<?> saveTypeDomaine(@RequestBody TypeDomaine typeDomaine) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		parametrageService.addNewTypeDomaine(typeDomaine);
		map.put("status", 1);
		map.put("message", new ApiResponse("Type Domaine créé avec succès !", true));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un type domaine par son id
	@GetMapping(path = "/types-domaines/{id}")
	public ResponseEntity<?> getTypeDomaineById(@PathVariable(name = "id") Long typeDomaineId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			TypeDomaine typedomaine = parametrageService.getTypeDomaineById(typeDomaineId);
			map.put("status", 1);
			map.put("data", typedomaine);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun type domaine trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des types domaines
	@GetMapping(path = "/types-domaines/all")
	public ResponseEntity<?> listtypedomaines() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<TypeDomaine> listtypedomaines = parametrageService.getAllTypeDomaines();
		if (!listtypedomaines.isEmpty()) {
			map.put("status", 1);
			map.put("data", listtypedomaines);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune information trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// supprimer un type domaine
	@DeleteMapping("/types-domaines/delete/{id}")
	public ResponseEntity<?> deleteTypeDomaine(@PathVariable Long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			TypeDomaine typedomaine = parametrageService.getTypeDomaineById(id);
			parametrageService.deleteTypeDomaine(typedomaine);
			map.put("status", 1);
			map.put("message", "TypeDomaine supprimé avec succès !");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun type domaine trouvé !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du type domaine
	@PutMapping("/types-domaines/update/{id}")
	public ResponseEntity<?> updateTypeDomaine(@PathVariable(name = "id") Long typeDomaineId,
			@RequestBody TypeDomaine td) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			TypeDomaine typedomaine = parametrageService.updateTypeDomaine(typeDomaineId, td);
			map.put("status", 1);
			map.put("data", typedomaine);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun TypeDomaine trouvé !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
}
