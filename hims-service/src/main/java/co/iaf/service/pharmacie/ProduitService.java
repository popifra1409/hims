package co.iaf.service.pharmacie;

import java.util.List;

import co.iaf.entity.pharmacie.Produit;

public interface ProduitService {

	/* ============ GESTION DE PRODUITS ============= */

	// créer un produit
	public Produit addNewProduit(Produit produit);
	
	// modifier un produit

	 public Produit updateProduit(String produitId, Produit produit);
	 

	// supprimer un produit par son id
	 public void deleteProduit(Produit produit);
	

	// recupérer un Produit par son Id
	public Produit getProduitById(String produitId);

	// recupérer la liste des tous les Produits
	public List<Produit> getAllProduits();

	// generate QRcode du produit
	public String getQRCode(Produit produit);

}
