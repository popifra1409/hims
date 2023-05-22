package co.iaf.controller.pharmacie;

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

import co.iaf.entity.identification.Patient;
import co.iaf.entity.pharmacie.Produit;
import co.iaf.payloads.ApiResponse;
import co.iaf.service.pharmacie.ProduitService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class ProduitResource {

	private ProduitService produitService;

	public ProduitResource(ProduitService produitService) {
		super();
		this.produitService = produitService;
	}

	/* ============ GESTION DES GROUPES DE PRODUIT ============= */
	// ajouter un nouveau produit
	@PostMapping(path = "/produits/save")
	public ResponseEntity<?> saveProduit(@RequestBody Produit produit) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		produitService.addNewProduit(produit);
		map.put("status", 1);
		map.put("message", new ApiResponse("Produit créé avec succès !", true));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	// afficher la liste des produits
	@GetMapping(path = "/produits/all")
	public ResponseEntity<?> listproduits() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Produit> listproduits = produitService.getAllProduits();
		if (!listproduits.isEmpty()) {
			map.put("status", 1);
			map.put("data", listproduits);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun produit trouvée", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	// recupérer un produit par son id
	
	@GetMapping(path = "/produits/{id}")
	public ResponseEntity<?> getProduitById(@PathVariable(name = "id") String reference) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Produit produit = produitService.getProduitById(reference);
			map.put("status", 1);
			map.put("data", produit);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", new ApiResponse("Aucun produit trouvé", false));
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	// supprimer un produit 
		@DeleteMapping("/produits/delete/{id}")
		public ResponseEntity<?> deleteProduit(@PathVariable String id) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			try {
				Produit produit = produitService.getProduitById(id);
				produitService.deleteProduit(produit);
				map.put("status", 1);
				map.put("message", "Produit supprimé avec succès !");
				return new ResponseEntity<>(map, HttpStatus.OK);
			} catch (Exception ex) {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucun produit trouvé !", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}
		
		// mettre à jour les informations du produit (test OK)
		@PutMapping("/produits/update/{id}")
		public ResponseEntity<?> updateProduit(@PathVariable(name = "id") String reference, @RequestBody Produit prod) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			try {
				Produit produit = produitService.updateProduit(reference, prod);
				map.put("status", 1);
				map.put("data", produit);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} catch (Exception ex) {
				map.clear();
				map.put("status", 0);
				map.put("message", new ApiResponse("Aucun produit trouvé !", false));
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}

}
