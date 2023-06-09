package co.iaf.controller.facturation;

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

import co.iaf.entity.facturation.Imputation;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.parametrage.TypeDomaine;
import co.iaf.payloads.ApiResponse;
import co.iaf.service.facturation.FacturationService;


@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class FacturationResource {
	
	private FacturationService facturationService;


	public FacturationResource(FacturationService facturationService) {
		this.facturationService = facturationService;
	}
	
	// ajouter une nouvelle Prestation

		@PostMapping(path = "/prestations/save/{domaineParentId}/{imputationId}")
		public ResponseEntity<?> saveDomaine(@PathVariable(name = "domaineParentId") Long parentId,@PathVariable(name = "imputationId") Long imputationId,
				 @RequestBody Prestation prestation) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			facturationService.addNewPrestation(prestation, parentId, imputationId);
			map.put("status", 1);
			map.put("message", new ApiResponse("Prestation créé avec succès !", true));
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		
		
		// recupérer une prestation par son id
				@GetMapping(path = "/prestations/{id}")
				public ResponseEntity<?> getPrestationById(@PathVariable(name = "id") Long prestationId) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					try {
					Prestation prestation = facturationService.getPrestationById(prestationId);
						map.put("status", 1);
						map.put("data", prestation);
						return new ResponseEntity<>(map, HttpStatus.OK);
					} catch (Exception ex) {
						map.clear();
						map.put("status", 0);
						map.put("message", new ApiResponse("Aucune prestation trouvé", false));
						return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
					}
				}
				
				// liste des prestations
				@GetMapping(path = "/prestations/all")
				public ResponseEntity<?> listPrestation() {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					List<Prestation> listPrestation = facturationService.getAllPrestation();
					if (!listPrestation.isEmpty()) {
						map.put("status", 1);
						map.put("data", listPrestation);
						return new ResponseEntity<>(map, HttpStatus.OK);
					} else {
						map.clear();
						map.put("status", 0);
						map.put("message", new ApiResponse("Aucune prestation trouvée", false));
						return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
					}
				}
				
				
				// supprimer une prestation
				@DeleteMapping("/prestations/delete/{id}")
				public ResponseEntity<?> deletePrestation(@PathVariable Long id) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					try {
						Prestation prestation = facturationService.getPrestationById(id);
						facturationService.deletePrestation(prestation);
						map.put("status", 1);
						map.put("message", "prestation supprimé avec succès !");
						return new ResponseEntity<>(map, HttpStatus.OK);
					} catch (Exception ex) {
						map.clear();
						map.put("status", 0);
						map.put("message", new ApiResponse("Aucune prestation trouvé !", false));
						return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
					}
				}
				
				// mettre à jour une prestation
				@PutMapping("/prestations/update/{id}")
				public ResponseEntity<?> updatePrestation(@PathVariable(name = "id") Long prestationId,
						@RequestBody Prestation pt) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					try {
						Prestation prestation = facturationService.updatePrestation(prestationId, pt);
						map.put("status", 1);
						map.put("data", prestation);
						return new ResponseEntity<>(map, HttpStatus.OK);
					} catch (Exception ex) {
						map.clear();
						map.put("status", 0);
						map.put("message", new ApiResponse("Aucune prestation trouvé !", false));
						return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
					}
				}
		
		/*============================GESTION DES IMPUTATIONS=========================*/
		
		// ajouter une nouvelle Imputation

		@PostMapping(path = "/imputations/save")
		public ResponseEntity<?> saveImputation(@RequestBody Imputation imputation) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			facturationService.addNewImputation(imputation);
			map.put("status", 1);
			map.put("message", new ApiResponse("Imputation créé avec succès !", true));
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		
		// recupérer un imputation par son id
		@GetMapping(path = "/imputations/{id}")
		public ResponseEntity<?> getImputationById(@PathVariable(name = "id") Long imputationId) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			try {
			Imputation imputation = facturationService.getImputationById(imputationId);
				map.put("status", 1);
				map.put("data", imputation);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} catch (Exception ex) {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucune imputation trouvé", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}
		
		// liste des imputations
		@GetMapping(path = "/imputations/all")
		public ResponseEntity<?> listImputation() {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			List<Imputation> listImputation = facturationService.getAllImputation();
			if (!listImputation.isEmpty()) {
				map.put("status", 1);
				map.put("data", listImputation);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucune imputation trouvée", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}
		
		// supprimer une imputation
		@DeleteMapping("/imputations/delete/{id}")
		public ResponseEntity<?> deleteImputation(@PathVariable Long id) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			try {
				Imputation imputation = facturationService.getImputationById(id);
				facturationService.deleteImputation(imputation);
				map.put("status", 1);
				map.put("message", "Imputation supprimé avec succès !");
				return new ResponseEntity<>(map, HttpStatus.OK);
			} catch (Exception ex) {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucun imputation trouvé !", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}
		
		// mettre à jour une imputations
		@PutMapping("/imputations/update/{id}")
		public ResponseEntity<?> updateImputation(@PathVariable(name = "id") Long imputationId,
				@RequestBody Imputation ip) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			try {
				Imputation imputation = facturationService.updateImputation(imputationId, ip);
				map.put("status", 1);
				map.put("data", imputation);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} catch (Exception ex) {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucune imputation trouvé !", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}


}
