package co.iaf.controller.pharmacie;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.iaf.service.pharmacie.ProduitService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class ProduitResource {

	private ProduitService produitService ;

	public ProduitResource(ProduitService groupeProduitService) {
		this.produitService = groupeProduitService;
	}

	
	/* ============ GESTION DES GROUPES DE PRODUIT ============= */
	
}
