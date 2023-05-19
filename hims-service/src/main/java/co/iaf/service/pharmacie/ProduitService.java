package co.iaf.service.pharmacie;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.entity.pharmacie.Produit;

@Service
@Transactional
public interface ProduitService {

	/* ============ GESTION DE PRODUITS ============= */

	// créer un produit
	public Produit addNewProduit(Produit produit);

	// mis à jour Produit
	public Produit updateProduit(String produitId, Produit produit);

	// delete Produit
	public void deleteProduit(Produit produit);

	// recupérer un Produit par son Id
	public Produit getProduitById(String produitId);

	// recupérer la liste des tous les Produits
	public List<Produit> getAllProduits();

}
