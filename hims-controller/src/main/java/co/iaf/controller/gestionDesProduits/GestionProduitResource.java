package co.iaf.controller.gestionDesProduits;

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

import co.iaf.entity.parametrage.Categorie;
import co.iaf.entity.pharmacie.Emplacement;
import co.iaf.entity.pharmacie.Famille;
import co.iaf.entity.pharmacie.Produit;
import co.iaf.payloads.ApiResponse;
import co.iaf.service.pharmacie.GestionProduitService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class GestionProduitResource {

	private GestionProduitService gestionProduitService ;

	public GestionProduitResource(GestionProduitService groupeProduitService) {
		this.gestionProduitService = groupeProduitService;
	}

	
	
	/* ============ GESTION DES GROUPES DE PRODUIT ============= */
	
	// liste de tous les groupes de produits
	/*
	 * @GetMapping(path = "/groupe-produit") public ResponseEntity<?>
	 * listGroupeProduit() { Map<String, Object> map = new LinkedHashMap<String,
	 * Object>(); List<GroupeProduit> listGroupeProduits =
	 * gestionProduitService.getAllGroupeProduits(); if
	 * (!listGroupeProduits.isEmpty()) { map.put("status", 1); map.put("data",
	 * listGroupeProduits); return new ResponseEntity<>(map, HttpStatus.OK); } else
	 * { map.clear(); map.put("status", 0); map.put("message", new
	 * ApiResponse("Aucune information trouvée", false)); return new
	 * ResponseEntity<>(map, HttpStatus.NOT_FOUND); } }
	 */

	
	// ajouter un nouveau groupe de produit
	/*
	 * @PostMapping(path = "/groupe-produit/save") public ResponseEntity<?>
	 * saveGroupeProduit(@RequestBody GroupeProduit groupeProduit) { Map<String,
	 * Object> map = new LinkedHashMap<String, Object>();
	 * gestionProduitService.addNewGroupeProduit(groupeProduit); map.put("status",
	 * 1); map.put("message", new
	 * ApiResponse("Groupe de produit créé avec succès !", true)); return new
	 * ResponseEntity<>(map, HttpStatus.CREATED); }
	 */

	// recupérer un groupe de produit par son id
	//@GetMapping(path = "/groupe-produit/{id}")
	/*
	 * public ResponseEntity<?> getGroupeProduitById(@PathVariable(name = "id") Long
	 * groupeProduitId) { Map<String, Object> map = new LinkedHashMap<String,
	 * Object>(); try { GroupeProduit groupeProduit =
	 * gestionProduitService.getGroupeProduitById(groupeProduitId);
	 * map.put("status", 1); map.put("data", groupeProduit); return new
	 * ResponseEntity<>(map, HttpStatus.OK); } catch (Exception ex) { map.clear();
	 * map.put("status", 0); map.put("message", new
	 * ApiResponse("Aucun groupe de produit trouvé", false)); return new
	 * ResponseEntity<>(map, HttpStatus.NOT_FOUND); } }
	 */

	// mettre à jour un groupe de produit
	@PutMapping("/groupe-produit/update/{id}")
	/*
	 * public ResponseEntity<?> updateGroupeProduit(@PathVariable(name = "id") Long
	 * groupeProduitId, @RequestBody GroupeProduit groupeProduit) { Map<String,
	 * Object> map = new LinkedHashMap<String, Object>(); try { GroupeProduit
	 * groupeProduitUpdate =
	 * gestionProduitService.getGroupeProduitById(groupeProduitId);
	 * 
	 * //TODO appel de la méthode de mise à jour des produits de ce groupe de
	 * produit
	 * 
	 * map.put("status", 1); map.put("data", groupeProduitUpdate); return new
	 * ResponseEntity<>(map, HttpStatus.OK); } catch (Exception ex) { map.clear();
	 * map.put("status", 0); map.put("message", new ApiResponse("Data is not found",
	 * false)); return new ResponseEntity<>(map, HttpStatus.NOT_FOUND); } }
	 */
	
	
	
	/* ============ GESTION DES CATEGORIES DE PRODUIT ============= */

	// liste des categories
	@GetMapping(path = "/categorie/all")
	public ResponseEntity<?> listCategories() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Categorie> listcategories = gestionProduitService.getAllCategories();
		if (!listcategories.isEmpty()) {
			map.put("status", 1);
			map.put("data", listcategories);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save categorie
	@PostMapping(path = "/categorie")
	public ResponseEntity<?> saveCategorie(@RequestBody Categorie categorie) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.addNewCategorie(categorie);
		map.put("status", 1);
		map.put("data", "Categorie enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un categorie par son id
	@GetMapping(path = "/categorie/{id}")
	public ResponseEntity<?> getCategorieById(@PathVariable(name = "id") Long CategorieId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Categorie categorie = gestionProduitService.getCategorieById(CategorieId);
			map.put("status", 1);
			map.put("data", categorie);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune categorie trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du categorie
	@PutMapping("/categorie/update/{id}")
	public ResponseEntity<?> updateCategorie(@PathVariable(name = "id") Long categorieId, @RequestBody Categorie bat) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			gestionProduitService.updateCategorie(bat, categorieId);
			Categorie categorie = gestionProduitService.getCategorieById(categorieId);
			map.put("status", 1);
			map.put("data", categorie);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete categorie
	@PostMapping(path = "/categorie/delete/{id}")
	public ResponseEntity<?> deleteCategorie(@PathVariable(name = "id") Long categorieId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.deleteCategorie(categorieId);
		map.put("status", 1);
		map.put("data", "Categorie supprimée avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}


	
	/* ============ GESTION DES EMPLACEMENTS DE PRODUIT ============= */

	// liste des emplacements
	@GetMapping(path = "/emplacement/all")
	public ResponseEntity<?> listEmplacements() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Emplacement> listemplacements = gestionProduitService.getAllEmplacements();
		if (!listemplacements.isEmpty()) {
			map.put("status", 1);
			map.put("data", listemplacements);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save emplacement
	@PostMapping(path = "/emplacement")
	public ResponseEntity<?> saveEmplacement(@RequestBody Emplacement emplacement) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.addNewEmplacement(emplacement);
		map.put("status", 1);
		map.put("data", "Emplacement enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un emplacement par son id
	@GetMapping(path = "/emplacement/{id}")
	public ResponseEntity<?> getEmplacementById(@PathVariable(name = "id") Long EmplacementId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Emplacement emplacement = gestionProduitService.getEmplacementById(EmplacementId);
			map.put("status", 1);
			map.put("data", emplacement);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune emplacement trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du emplacement
	@PutMapping("/emplacement/update/{id}")
	public ResponseEntity<?> updateEmplacement(@PathVariable(name = "id") Long emplacementId, @RequestBody Emplacement bat) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			gestionProduitService.updateEmplacement(bat, emplacementId);
			Emplacement emplacement = gestionProduitService.getEmplacementById(emplacementId);
			map.put("status", 1);
			map.put("data", emplacement);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete emplacement
	@PostMapping(path = "/emplacement/delete/{id}")
	public ResponseEntity<?> deleteEmplacement(@PathVariable(name = "id") Long emplacementId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.deleteEmplacement(emplacementId);
		map.put("status", 1);
		map.put("data", "Emplacement supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}


	
	/* ============ GESTION DES FAMILLE DE PRODUIT ============= */

	// liste des familles
	@GetMapping(path = "/famille/all")
	public ResponseEntity<?> listFamilles() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Famille> listfamilles = gestionProduitService.getAllFamilles();
		if (!listfamilles.isEmpty()) {
			map.put("status", 1);
			map.put("data", listfamilles);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save famille
	@PostMapping(path = "/famille")
	public ResponseEntity<?> saveFamille(@RequestBody Famille famille) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.addNewFamille(famille);
		map.put("status", 1);
		map.put("data", "Famille enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// recupérer un famille par son id
	@GetMapping(path = "/famille/{id}")
	public ResponseEntity<?> getFamilleById(@PathVariable(name = "id") Long FamilleId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Famille famille = gestionProduitService.getFamilleById(FamilleId);
			map.put("status", 1);
			map.put("data", famille);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune famille trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du famille
	@PutMapping("/famille/update/{id}")
	public ResponseEntity<?> updateFamille(@PathVariable(name = "id") Long familleId, @RequestBody Famille bat) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			gestionProduitService.updateFamille(bat, familleId);
			Famille famille = gestionProduitService.getFamilleById(familleId);
			map.put("status", 1);
			map.put("data", famille);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Data is not found", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// delete famille
	@PostMapping(path = "/famille/delete/{id}")
	public ResponseEntity<?> deleteFamille(@PathVariable(name = "id") Long familleId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.deleteFamille(familleId);
		map.put("status", 1);
		map.put("data", "Famille supprimée avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}


	
	/* ============ GESTION DES PRODUIT ============= */

	// liste des produits
	@GetMapping(path = "/produit/all")
	public ResponseEntity<?> listProduits() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Produit> listproduits = gestionProduitService.getAllProduits();
		if (!listproduits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listproduits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save produit
	@PostMapping(path = "/produit/{idCategorie}/{idEmplacement}/{idFamille}")
	/*
	 * public ResponseEntity<?> saveProduit(@RequestBody Produit
	 * produit, @PathVariable(name = "idCategorie") Long
	 * categorieId, @PathVariable(name = "idEmplacement") Long
	 * emplacementId, @PathVariable(name = "idFamille") Long familleId) {
	 * Map<String, Object> map = new LinkedHashMap<String, Object>();
	 * gestionProduitService.addNewProduit(produit, categorieId, emplacementId,
	 * familleId); map.put("status", 1); map.put("data",
	 * "Produit enregistré avec succès"); return new ResponseEntity<>(map,
	 * HttpStatus.CREATED); }
	 */

	// recupérer un produit par son id
	@GetMapping(path = "/produit/{id}")
	public ResponseEntity<?> getProduitById(@PathVariable(name = "id") Long ProduitId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Produit produit = gestionProduitService.getProduitById(ProduitId);
			map.put("status", 1);
			map.put("data", produit);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucune produit trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// mettre à jour les infos du produit
	@PutMapping("/produit/update/{id}/{idCategorie}/{idEmplacement}/{idFamille}")
	/*
	 * public ResponseEntity<?> updateProduit(@RequestBody Produit
	 * prod, @PathVariable(name = "id") Long produitId , @PathVariable(name =
	 * "idCategorie") Long categorieId, @PathVariable(name = "idEmplacement") Long
	 * emplacementId, @PathVariable(name = "idFamille") Long familleId) {
	 * Map<String, Object> map = new LinkedHashMap<String, Object>(); try {
	 * gestionProduitService.updateProduit(prod, produitId, categorieId,
	 * emplacementId, familleId); Produit produit =
	 * gestionProduitService.getProduitById(produitId); map.put("status", 1);
	 * map.put("data", produit); return new ResponseEntity<>(map, HttpStatus.OK); }
	 * catch (Exception ex) { map.clear(); map.put("status", 0); map.put("message",
	 * new ApiResponse("Data is not found", false)); return new
	 * ResponseEntity<>(map, HttpStatus.NOT_FOUND); } }
	 */

	// delete produit
	@PostMapping(path = "/produit/delete/{id}")
	public ResponseEntity<?> deleteProduit(@PathVariable(name = "id") Long produitId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		gestionProduitService.deleteProduit(produitId);
		map.put("status", 1);
		map.put("data", "Produit supprimé avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	
}
