package co.iaf.service.pharmacie;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.entity.parametrage.Categorie;
import co.iaf.entity.pharmacie.Emplacement;
import co.iaf.entity.pharmacie.Famille;
import co.iaf.entity.pharmacie.Produit;

@Service
@Transactional
public interface GestionProduitService {

	/* ============ GESTION DES FAMILLES DE PRODUITS ============= */

	// créer famille de produit
	public Famille addNewFamille(Famille familleProduit);

	// mis à jour famille de produit
	public Famille updateFamille(Famille familleProduit, Long familleProduitId);

	// suppression d'un famille de produit
	public void deleteFamille(Long familleProduitId);

	// recupérer un famille de produit par son Id
	public Famille getFamilleById(Long familleProduitId);

	// recupérer tous les familles de produit
	public List<Famille> getAllFamilles();

	/* ============ GESTION DES CATEGORIES DE PRODUITS ============= */

	// créer categorie de produit
	public Categorie addNewCategorie(Categorie categorieProduit);

	// mis à jour categorie de produit
	public Categorie updateCategorie(Categorie categorieProduit, Long categorieProduitId);

	// suppression d'un categorie de produit
	public void deleteCategorie(Long categorieProduitId);

	// recupérer un categorie de produit par son Id
	public Categorie getCategorieById(Long categorieProduitId);

	// recupérer tous les categories de produit
	public List<Categorie> getAllCategories();

	/* ============ GESTION DES EMPLACEMENTS ============= */

	// créer emplacement de produit
	public Emplacement addNewEmplacement(Emplacement emplacement);

	// mis à jour emplacement de produit
	public Emplacement updateEmplacement(Emplacement emplacement, Long emplacementId);

	// suppression d'un emplacement de produit
	public void deleteEmplacement(Long emplacementId);

	// recupérer un emplacement de produit par son Id
	public Emplacement getEmplacementById(Long emplacementId);

	// recupérer tous les emplacements de produit
	public List<Emplacement> getAllEmplacements();

	/* ============ GESTION DES PRODUITS ============= */

	// créer un produit
	public Produit addNewProduit(Produit produit, Long familleId, Long categorieId, Long fournisseurId);

	// mis à jour de produit
	public Produit updateProduit(Long produitId, Produit produit);

	// suppression d'un produit
	public void deleteProduit(Long produitId);

	// recupérer un produit par son Id
	public Produit getProduitById(Long produitId);

	// recupérer tous les produit
	public List<Produit> getAllProduits();

	// Rechercher les produit par designation ou reference
	public List<Produit> getProduitByDesignationOrRef(String reference, String designation);

	// affecter des produits au services
	public void affecterProduit(Long serviceId, Long produitId);

	// desactiver un produit
	public void desactiveProduit();

	// liste des produits désactivés
	public List<Produit> getProduitsDesactive();

	// archiver un produit
	public void archiveProduit();

	// liste des produits archivés
	public List<Produit> getProduitsArchive();

	// lier un produit à une prestation
	public void liaisonPrestation();

	// grouper les produits
	public void grouperProduit(Long produitPrincipalId, Long produitSecondaireId);

	// calcul du PMUP
	public Double prixPMUP(Produit produit);

}
