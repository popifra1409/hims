package co.iaf.controller.pharmacie;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
