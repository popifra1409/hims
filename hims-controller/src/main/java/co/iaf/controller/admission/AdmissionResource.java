package co.iaf.controller.admission;

import java.sql.Date;
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

import co.iaf.entity.admission.Batiment;
import co.iaf.entity.admission.Chambre;
import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.admission.Lit;
import co.iaf.entity.admission.ParametreDeSoin;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.admission.Sejour;
import co.iaf.entity.admission.TransfertHospit;
import co.iaf.entity.parametrage.Services;
import co.iaf.payloads.ApiResponse;
import co.iaf.service.admission.AdmissionService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class AdmissionResource {

	private AdmissionService admissionService;

	public AdmissionResource(AdmissionService admissionService) {
		this.admissionService = admissionService;
	}

	/* ============ GESTION DES BATIMENTS ============= */

	// liste des batiments
	@GetMapping(path = "/batiments/all")
	public ResponseEntity<?> listBatiments() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Batiment> listbatiments = admissionService.getAllBatiments();
		if (!listbatiments.isEmpty()) {
			map.put("status", 1);
			map.put("data", listbatiments);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save batiment
	@PostMapping(path = "/batiments/save")
	public ResponseEntity<?> saveBatiment(@RequestBody Batiment batiment) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewBatiment(batiment);
		map.put("status", 1);
		map.put("data", "Bâtiment créé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un batiment par son id
	@GetMapping(path = "/batiment/{id}")
	public ResponseEntity<?> getBatimentById(@PathVariable(name = "id") Long BatimentId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Batiment batiment = admissionService.getBatimentById(BatimentId);
			map.put("status", 1);
			map.put("data", batiment);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun batiment trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du batiment
	@PutMapping("/batiment/update/{id}")
	public ResponseEntity<?> updateBatiment(@PathVariable(name = "id") Long batimentId, @RequestBody Batiment bat) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateBatiment(bat, batimentId);
			Batiment batiment = admissionService.getBatimentById(batimentId);
			map.put("status", 1);
			map.put("data", batiment);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete batiment
	@PostMapping(path = "/batiment/delete/{id}")
	public ResponseEntity<?> deleteBatiment(@PathVariable(name = "id") Long batimentId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deleteBatiment(batimentId);
		map.put("status", 1);
		map.put("data", "Bâtiment supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	/* ============ GESTION DES CHAMBRES ============= */

	// liste des chambres
	@GetMapping(path = "/chambre/all")
	public ResponseEntity<?> listChambres() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Chambre> listChambres = admissionService.getAllChambres();
		if (!listChambres.isEmpty()) {
			map.put("status", 1);
			map.put("data", listChambres);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save chambre
	@PostMapping(path = "/chambre/{idBatiment}")
	public ResponseEntity<?> saveChambre(@PathVariable(name = "idBatiment") Long batimentId,
			@RequestBody Chambre chambre) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewChambre(chambre, batimentId);
		map.put("status", 1);
		map.put("data", "Chambre enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer une chambre par son id
	@GetMapping(path = "/chambre/{id}")
	public ResponseEntity<?> getChambreById(@PathVariable(name = "id") Long ChambreId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Chambre chambre = admissionService.getChambreById(ChambreId);
			map.put("status", 1);
			map.put("data", chambre);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun batiment trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos de la chambre
	@PutMapping("/chambre/update/{id}")
	public ResponseEntity<?> updateChambre(@RequestBody Chambre cham, @PathVariable(name = "id") Long chambreId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateChambre(cham, chambreId);
			Chambre chambre = admissionService.getChambreById(chambreId);
			map.put("status", 1);
			map.put("data", chambre);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete chambre
	@PostMapping(path = "/chambre/delete/{id}")
	public ResponseEntity<?> deleteChambre(@PathVariable(name = "id") Long chambreId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deleteChambre(chambreId);
		map.put("status", 1);
		map.put("data", "Chambre supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	/* ============ GESTION DES LITS ============= */

	// liste des lits
	@GetMapping(path = "/lit/all")
	public ResponseEntity<?> listLits() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Lit> listLits = admissionService.getAllLits();
		if (!listLits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listLits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save lit
	@PostMapping(path = "/lit/{idChambre}")
	public ResponseEntity<?> saveLit(@PathVariable(name = "idChambre") Long chambreId, @RequestBody Lit lit) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewLit(lit, chambreId);
		map.put("status", 1);
		map.put("data", "Lit enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un lit par son id
	@GetMapping(path = "/lit/{id}")
	public ResponseEntity<?> getLitById(@PathVariable(name = "id") Long litId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Lit lit = admissionService.getLitById(litId);
			map.put("status", 1);
			map.put("data", lit);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun batiment trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos d'un lit
	@PutMapping("/lit/update/{id}")
	public ResponseEntity<?> updateLit(@RequestBody Lit lt, @PathVariable(name = "id") Long litId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateLit(lt, litId);
			Lit lit = admissionService.getLitById(litId);
			map.put("status", 1);
			map.put("data", lit);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete lit
	@PostMapping(path = "/lit/delete/{id}")
	public ResponseEntity<?> deleteLit(@PathVariable(name = "id") Long litId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deleteLit(litId);
		map.put("status", 1);
		map.put("data", "Chambre supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	/* ============ GESTION DES PARAMETRES DE SOIN ============= */

	// liste des paramètres de soin
	@GetMapping(path = "/parametre-soin/all")
	public ResponseEntity<?> listParametreDeSoin() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<ParametreDeSoin> listParametreDeSoins = admissionService.getAllParametreDeSoins();
		if (!listParametreDeSoins.isEmpty()) {
			map.put("status", 1);
			map.put("data", listParametreDeSoins);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save paramètres de soin
	@PostMapping(path = "/parametre-soin")
	public ResponseEntity<?> saveParametreDeSoin(@RequestBody ParametreDeSoin parametreDeSoin) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewParametreDeSoin(parametreDeSoin);
		map.put("status", 1);
		map.put("data", "paramètre de soin enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un paramètres de soin par son id
	@GetMapping(path = "/parametre-soin/{id}")
	public ResponseEntity<?> getParametreDeSoinById(@PathVariable(name = "id") Long parametreDeSoinId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			ParametreDeSoin parametreDeSoin = admissionService.getParametreDeSoinById(parametreDeSoinId);
			map.put("status", 1);
			map.put("data", parametreDeSoin);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun paramètre de soin trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos d'un paramètre de soin
	@PutMapping("/parametre-soin/update/{id}")
	public ResponseEntity<?> updateParametreDeSoin(@RequestBody ParametreDeSoin param,
			@PathVariable(name = "id") Long parametreDeSoinId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateParametreDeSoin(param, parametreDeSoinId);
			ParametreDeSoin parametre = admissionService.getParametreDeSoinById(parametreDeSoinId);
			map.put("status", 1);
			map.put("data", parametre);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete paramètre de soin
	@PostMapping(path = "/parametre-soin/delete/{id}")
	public ResponseEntity<?> deleteParametreDeSoin(@PathVariable(name = "id") Long parametreDeSoinId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deleteParametreDeSoin(parametreDeSoinId);
		map.put("status", 1);
		map.put("data", "Paramètre de soin supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	/* ============ GESTION DES PRISES DE PARAMETRE DE SOIN ============= */

	// liste des prises de paramètre de soin
	@GetMapping(path = "/prise-parametre-soin/all")
	public ResponseEntity<?> listaddNewPriseParametreSoins() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<PriseParametreSoin> listPriseParametreSoins = admissionService.getAllPriseParametreSoins();
		if (!listPriseParametreSoins.isEmpty()) {
			map.put("status", 1);
			map.put("data", listPriseParametreSoins);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save prise de paramètre de soin
	@PostMapping(path = "/prise-parametre-soin/{idPatient}")
	public ResponseEntity<?> savePriseParametreSoin(@PathVariable(name = "idPatient") String patientId,
			@RequestBody PriseParametreSoin priseParametreSoin) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewPriseParametreSoin(priseParametreSoin, patientId);
		map.put("status", 1);
		map.put("data", "prise de paramètre enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un paramètre de soin par son id
	@GetMapping(path = "/prise-parametre-soin/{id}")
	public ResponseEntity<?> getPriseParametreSoinById(@PathVariable(name = "id") Long priseParametreSoinId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			PriseParametreSoin priseParametreSoin = admissionService.getPriseParametreSoinById(priseParametreSoinId);
			map.put("status", 1);
			map.put("data", priseParametreSoin);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune prise de paramètre trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos d'un paramètre de soin
	@PutMapping("/prise-parametre-soin/update/{id}")
	public ResponseEntity<?> updateLit(@RequestBody PriseParametreSoin prise,
			@PathVariable(name = "id") Long priseParametreSoinId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updatePriseParametreSoin(prise, priseParametreSoinId);
			PriseParametreSoin priseParametreSoin = admissionService.getPriseParametreSoinById(priseParametreSoinId);
			map.put("status", 1);
			map.put("data", priseParametreSoin);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete paramètre de soin
	@PostMapping(path = "/prise-parametre-soin/delete/{id}")
	public ResponseEntity<?> deletePriseParametreSoin(@PathVariable(name = "id") Long priseParametreSoinId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deletePriseParametreSoin(priseParametreSoinId);
		map.put("status", 1);
		map.put("data", "prise paramètre de soin supprimée avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	/* ============ GESTION DES SEJOURS ============= */

	// liste des sejours
	@GetMapping(path = "/sejour/all")
	public ResponseEntity<?> listSejours() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Sejour> listsejours = admissionService.getAllSejours();
		if (!listsejours.isEmpty()) {
			map.put("status", 1);
			map.put("data", listsejours);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save sejour
	@PostMapping(path = "/sejour")
	public ResponseEntity<?> saveSejour(@RequestBody Sejour sejour) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewSejour(sejour);
		map.put("status", 1);
		map.put("data", "Sejour enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un sejour par son id
	@GetMapping(path = "/sejour/{id}")
	public ResponseEntity<?> getSejourById(@PathVariable(name = "id") Long SejourId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Sejour sejour = admissionService.getSejourById(SejourId);
			map.put("status", 1);
			map.put("data", sejour);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun sejour trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du sejour
	@PutMapping("/sejour/update/{id}")
	public ResponseEntity<?> updateSejour(@PathVariable(name = "id") Long sejourId, @RequestBody Sejour bat) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateSejour(bat, sejourId);
			Sejour sejour = admissionService.getSejourById(sejourId);
			map.put("status", 1);
			map.put("data", sejour);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete sejour
	@PostMapping(path = "/sejour/delete/{id}")
	public ResponseEntity<?> deleteSejour(@PathVariable(name = "id") Long sejourId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deleteSejour(sejourId);
		map.put("status", 1);
		map.put("data", "Bâtiment supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	/* ============ GESTION DES HOSPITALISATION ============= */

	// liste des hospits
	@GetMapping(path = "/hospit/all")
	public ResponseEntity<?> listHospits() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getAllHospits();
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save hospitalisation
	@PostMapping(path = "/hospit/{idSejour}/{idService}/{idPatient}/{idLit}")
	public ResponseEntity<?> saveHospit(@RequestBody Hospitalisation hospit,
			@PathVariable(name = "idSejour") Long sejourId, @PathVariable(name = "idService") Long serviceId,
			@PathVariable(name = "idPatient") String patientId, @PathVariable(name = "idLit") Long litId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.addNewHospit(hospit, sejourId, serviceId, patientId, litId);
		map.put("status", 1);
		map.put("data", "Hospitalisation enregistrée avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// mettre à jour les infos du hospit
	@PutMapping("/hospit/update/{id}")
	public ResponseEntity<?> updateHospit(@PathVariable(name = "id") Long hospitId, @RequestBody Hospitalisation hospit) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateHospit(hospitId, hospit);
			Hospitalisation hospitUpdate = admissionService.getHospitById(hospitId);
			map.put("status", 1);
			map.put("data", hospitUpdate);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// recupérer un hospit par son code
	@GetMapping(path = "/hospit/{code}")
	public ResponseEntity<?> getHospitByCode(@PathVariable(name = "code") String HospitCode) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Hospitalisation hospit = admissionService.getHospitByCode(HospitCode);
			map.put("status", 1);
			map.put("data", hospit);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune hospitalisation trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete hospit
	@PostMapping(path = "/hospit/delete/{id}")
	public ResponseEntity<?> deleteHospit(@PathVariable(name = "id") Long hospitId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.deleteHospit(hospitId);
		map.put("status", 1);
		map.put("data", "Hospitalisation supprimée avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// liste des hospits d'un séjour
	@GetMapping(path = "/hospit/by-sejour")
	public ResponseEntity<?> getHospitBySejour(@RequestBody Sejour sejour) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getHospitsBySejour(sejour);
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des hospits par séjour
	@GetMapping(path = "/hospit/by-code-sejour/{codeSejour}")
	public ResponseEntity<?> getHospitByCodeSejour(@PathVariable(name = "codeSejour") String sejourCode) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getHospitsBySejour(sejourCode);
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des hospits par date d'entrée
	@GetMapping(path = "/hospit/by-date-entree")
	public ResponseEntity<?> getHospitByDateEntree(@RequestBody Date dateEntree) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getHospitsByDateEntree(dateEntree);
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des hospits par date de sortie
	@GetMapping(path = "/hospit/by-date-sortie")
	public ResponseEntity<?> getHospitByDateSortie(@RequestBody Date dateSortie) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getHospitsByDateEntree(dateSortie);
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des hospitalisations encours dans un service
	@GetMapping(path = "/hospit-encours/by-service")
	public ResponseEntity<?> getHospitEncoursByService(@RequestBody Services service) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getHospitsEncoursByService(service);
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des hospitalisations cloturées
	@GetMapping(path = "/hospit/closed")
	public ResponseEntity<?> getHospitClosed() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getClosedHospits();
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// liste des hospitalisations non cloturées
	@GetMapping(path = "/hospit/unclosed")
	public ResponseEntity<?> getHospitUnClosed() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getUnClosedHospits();
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// send Avis hospitalisation
	@PostMapping(path = "/avis-hospit/send/{idSejour}/{idService}/{idPatient}")
	public ResponseEntity<?> saveAvisHospit(@RequestBody Hospitalisation hospit,
			@PathVariable(name = "idSejour") Long sejourId, @PathVariable(name = "idService") Long serviceId,
			@PathVariable(name = "idPatient") String patientId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		admissionService.sendAvisHospit(hospit, sejourId, serviceId, patientId);
		map.put("status", 1);
		map.put("data", "Avis d'hospitalisation enregistrée avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// récupération d'un avis d'hospitalisation
	@GetMapping(path = "/avis-hospit/all")
	public ResponseEntity<?> getAvisHospits() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Hospitalisation> listhospits = admissionService.getAvisHospits();
		if (!listhospits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listhospits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	// transformer un avis d'hospitalisation en hospitalisation
	@PutMapping("/hospit/avis-hospit/{id}")
	public ResponseEntity<?> avisToHospit(@PathVariable(name = "id") Long hospitId, @RequestBody Hospitalisation hospit) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			admissionService.updateHospit(hospitId, hospit);
			Hospitalisation hospitUpdate = admissionService.avisToHospit(hospitId, hospit);
			map.put("status", 1);
			map.put("data", hospitUpdate);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	// delete hospit
		@PostMapping(path = "/avis-hospit/delete/{id}")
		public ResponseEntity<?> deleteAvisHospit(@PathVariable(name = "id") Long hospitId) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			admissionService.deleteAvisHospit(hospitId);
			map.put("status", 1);
			map.put("data", "Avis d'hospitalisation supprimé avec succès");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		
	
		// save tansfert hospitalisation
		@PostMapping(path = "/hospit/{idHospit}/{idService}")
		public ResponseEntity<?> transfertHospit(@RequestBody TransfertHospit transfert,
				@PathVariable(name = "idHospit") Long hospitId, @PathVariable(name = "idService") Long serviceId) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			admissionService.transfertHospit(transfert, hospitId, serviceId);
			map.put("status", 1);
			map.put("data", "Hospitalisation transférée avec succès");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		
		// save tansfert hospitalisation
		@PostMapping(path = "/transfert-hospit/delete/{id}")
		public ResponseEntity<?> deleteTransfertHospit(@RequestBody TransfertHospit transfert,
				@PathVariable(name = "idHospit") Long hospitId, @PathVariable(name = "id") Long transfertId) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			admissionService.deleteTransfertHospit(transfertId);
			map.put("status", 1);
			map.put("data", "Hospitalisation transférée avec succès");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		
		

}
