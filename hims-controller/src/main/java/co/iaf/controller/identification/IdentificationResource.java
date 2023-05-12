package co.iaf.controller.identification;

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

import co.iaf.entity.identification.GroupePatient;
import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;
import co.iaf.payloads.ApiResponse;
import co.iaf.service.identification.IdentificationService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class IdentificationResource {

	private IdentificationService identificationService;

	public IdentificationResource(IdentificationService identificationService) {
		this.identificationService = identificationService;
	}

	/* ===================gestion des patients ========================== */
	// liste des patients (test OK)
	@GetMapping(path = "/patients/all")
	public ResponseEntity<?> listpatients() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Patient> listpatients = identificationService.getAllPatients();
		if (!listpatients.isEmpty()) {
			map.put("status", 1);
			map.put("data", listpatients);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune information trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// ajouter un nouveau patient (test OK)
	@PostMapping(path = "/patients/save")
	public ResponseEntity<?> savePatient(@RequestBody Patient patient) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		identificationService.addNewPatient(patient);
		map.put("status", 1);
		map.put("message", new ApiResponse("Patient créé avec succès !", true));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un patient par son id (test OK)
	@GetMapping(path = "/patients/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable(name = "id") String patientId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Patient patient = identificationService.getPatientById(patientId);
			map.put("status", 1);
			map.put("data", patient);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun patient trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du patient (test OK)
	@PutMapping("/patients/update/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable(name = "id") String patientId, @RequestBody Patient p) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Patient patient = identificationService.updatePatient(patientId, p);
			map.put("status", 1);
			map.put("data", patient);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun patient trouvé !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// supprimer un patient (test OK)
	@DeleteMapping("/patients/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable String id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Patient patient = identificationService.getPatientById(id);
			identificationService.deletePatient(patient);
			map.put("status", 1);
			map.put("message", "Patient supprimé avec succès !");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun patient trouvé !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// rechercher un patient par son nom ou son prénom (test OK)
	@GetMapping("/patients/search/{keyword}")
	public ResponseEntity<?> getPatientByLastNameOrFirstName(@PathVariable String keyword) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Patient> listpatients = identificationService.getPatientsByLastNameOrFirstName(keyword, keyword);
		if (!listpatients.isEmpty()) {
			map.put("status", 1);
			map.put("data", listpatients);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune information trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// rechercher un patient par son codebarre (A revoir)
	@GetMapping(path = "/patients/barcode/{barcode}")
	public ResponseEntity<?> getPatientByBarCode(@PathVariable String barcode) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Patient patient = identificationService.getPatientByBarCode(barcode);
			map.put("status", 1);
			map.put("data", patient);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun patient trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * ===================gestion des infos supplémentaires du patient
	 * ==========================
	 */
	// ajouter une info supplémentaire (Test OK)
	@PostMapping(path = "/infossup/save/{patientId}")
	public ResponseEntity<?> saveInfosSup(@RequestBody InfosSup info, @PathVariable String patientId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		identificationService.addNewInfoToPatient(info, patientId);
		map.put("status", 1);
		map.put("message", new ApiResponse("Info supplémentaire créé avec succès !", true));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// liste des infos supplémentaires d'un patient (Test OK)
	@GetMapping("/infossup/patient/{patientId}")
	public ResponseEntity<?> listinfosup(@PathVariable String patientId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Patient patient = identificationService.getPatientById(patientId);
		List<InfosSup> listinfosup = identificationService.getAllInfosSup(patient);
		if (!listinfosup.isEmpty()) {
			map.put("status", 1);
			map.put("data", listinfosup);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune information trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// recupérer une info supplémentaire (Test OK)
	@GetMapping(path = "/infossup/{id}")
	public ResponseEntity<?> getInfosSupById(@PathVariable(name = "id") Long infoId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			InfosSup info = identificationService.getInfosSupById(infoId);
			map.put("status", 1);
			map.put("data", info);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune info supplémentaire trouvée !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos supplémentaires du patient (Test Ok)
	@PutMapping("/infossup/update/{id}")
	public ResponseEntity<?> updateInfosSup(@PathVariable(name = "id") Long infoId, @RequestBody InfosSup info) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			InfosSup infoSup = identificationService.updateInfosSup(infoId, info);
			map.put("status", 1);
			map.put("data", infoSup);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune info supplémentaire trouvée !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// supprimer une info supplémentaire (Test OK)
	@DeleteMapping("/infossup/delete/{id}")
	public ResponseEntity<?> deleteInfoSup(@PathVariable Long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			InfosSup info = identificationService.getInfosSupById(id);
			identificationService.deleteInfosSup(info);
			map.put("status", 1);
			map.put("message", "Info supplémentaire supprimée avec succès !");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune info supplémentaire trouvée !", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	/* ===================gestion groupe des patients ========================== */
	// liste des groupes des patients ()
		@GetMapping(path = "/groupepatients/all")
		public ResponseEntity<?> listgroupepatients() {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			List<GroupePatient> listgroupepatients = identificationService.getAllGroupesPatients();
			if (!listgroupepatients.isEmpty()) {
				map.put("status", 1);
				map.put("data", listgroupepatients);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucune information trouvée", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}
}