package co.iaf.service.pharmacie;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.dao.parametrage.CategorieRepository;
import co.iaf.dao.pharmacie.EmplacementRepository;
import co.iaf.dao.pharmacie.FamilleRepository;
import co.iaf.dao.pharmacie.ProduitRepository;
import co.iaf.entity.parametrage.Categorie;
import co.iaf.entity.pharmacie.Emplacement;
import co.iaf.entity.pharmacie.Famille;
import co.iaf.entity.pharmacie.Produit;

@Service
@Transactional
public class GestionProduitServiceImpl implements GestionProduitService {

	private ProduitRepository produitRepository;
	private FamilleRepository familleRepository;
	private EmplacementRepository emplacementRepository;
	private CategorieRepository categorieRepository;

	public GestionProduitServiceImpl(ProduitRepository produitRepository, FamilleRepository familleRepository,
			EmplacementRepository emplacementRepository, CategorieRepository categorieRepository) {
		this.produitRepository = produitRepository;
		this.familleRepository = familleRepository;
		this.emplacementRepository = emplacementRepository;
		this.categorieRepository = categorieRepository;
	}

	@Override
	public Famille addNewFamille(Famille familleProduit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Famille updateFamille(Famille familleProduit, Long familleProduitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFamille(Long familleProduitId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Famille getFamilleById(Long familleProduitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Famille> getAllFamilles() {
		return familleRepository.findAll();
	}

	@Override
	public Categorie addNewCategorie(Categorie categorieProduit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie updateCategorie(Categorie categorieProduit, Long categorieProduitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategorie(Long categorieProduitId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie getCategorieById(Long categorieProduitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}

	@Override
	public Emplacement addNewEmplacement(Emplacement emplacementProduit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emplacement updateEmplacement(Emplacement emplacementProduit, Long emplacementProduitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmplacement(Long emplacementProduitId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Emplacement getEmplacementById(Long emplacementProduitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emplacement> getAllEmplacements() {
		return emplacementRepository.findAll();
	}

	@Override
	public Produit addNewProduit(Produit produit, Long familleId, Long categorieId, Long fournisseurId) {

		return null;
	}

	@Override
	public Produit updateProduit(Long produitId, Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Long produitId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Produit getProduitById(Long produitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduits() {
		return produitRepository.findAll();
	}

	@Override
	public List<Produit> getProduitByDesignationOrRef(String reference, String designation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void affecterProduit(Long serviceId, Long produitId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactiveProduit() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Produit> getProduitsDesactive() {
		return produitRepository.findByIsDesactiveTrue();
	}

	@Override
	public void archiveProduit() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Produit> getProduitsArchive() {
		return produitRepository.findByIsArchiveTrue();
	}

	@Override
	public void liaisonPrestation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grouperProduit(Long produitPrincipalId, Long produitSecondaireId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Double prixPMUP(Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * ============ GESTION DES FAMILLES DE PRODUITS =============
	 * 
	 * // créer d'un nouveau famille public Famille addNewFamille(Famille famille) {
	 * return familleRepository.save(famille);
	 * 
	 * }
	 * 
	 * // mis à jour d'un famille public Famille updateFamille(Famille famille, Long
	 * familleId) { if (getFamilleById(familleId) != null) {
	 * famille.setId(familleId); return familleRepository.save(famille); } return
	 * null; }
	 * 
	 * // suppression d'un famille public void deleteFamille(Long familleId) {
	 * familleRepository.deleteById(familleId); }
	 * 
	 * // recupérer un famille par son id public Famille getFamilleById(Long
	 * familleId) { return familleRepository.findById(familleId) .orElseThrow(() ->
	 * new ResourceNotFoundException("Famille", "Famille Id", 0)); }
	 * 
	 * // recupérer tous les familles public List<Famille> getAllFamilles() { return
	 * familleRepository.findAll(); }
	 * 
	 * ============ GESTION DES EMPLACEMENTS DE PRODUITS =============
	 * 
	 * // créer d'un nouveau emplacement public Emplacement
	 * addNewEmplacement(Emplacement emplacement) { return
	 * emplacementRepository.save(emplacement);
	 * 
	 * }
	 * 
	 * // mis à jour d'un emplacement public Emplacement
	 * updateEmplacement(Emplacement emplacement, Long emplacementId) { if
	 * (getEmplacementById(emplacementId) != null) {
	 * emplacement.setId(emplacementId); return
	 * emplacementRepository.save(emplacement); } return null; }
	 * 
	 * // suppression d'un emplacement public void deleteEmplacement(Long
	 * emplacementId) { emplacementRepository.deleteById(emplacementId); }
	 * 
	 * // recupérer un emplacement par son id public Emplacement
	 * getEmplacementById(Long emplacementId) { return
	 * emplacementRepository.findById(emplacementId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Emplacement", "Emplacement Id", 0)); }
	 * 
	 * // recupérer tous les emplacements public List<Emplacement>
	 * getAllEmplacements() { return emplacementRepository.findAll(); }
	 * 
	 * ============ GESTION DES CATEGORIES DE PRODUITS =============
	 * 
	 * // créer d'un nouveau categorie public Categorie addNewCategorie(Categorie
	 * categorie) { return categorieRepository.save(categorie);
	 * 
	 * }
	 * 
	 * // mis à jour d'un categorie public Categorie updateCategorie(Categorie
	 * categorie, Long categorieId) { if (getCategorieById(categorieId) != null) {
	 * categorie.setId(categorieId); return categorieRepository.save(categorie); }
	 * return null; }
	 * 
	 * // suppression d'un categorie public void deleteCategorie(Long categorieId) {
	 * categorieRepository.deleteById(categorieId); }
	 * 
	 * // recupérer un categorie par son id public Categorie getCategorieById(Long
	 * categorieId) { return categorieRepository.findById(categorieId)
	 * .orElseThrow(() -> new ResourceNotFoundException("Categorie", "Categorie Id",
	 * 0)); }
	 * 
	 * // recupérer tous les categories public List<Categorie> getAllCategories() {
	 * return categorieRepository.findAll(); }
	 * 
	 * ============ GESTION DES PRODUITS =============
	 * 
	 * // créer d'un nouveau produit public Produit addNewProduit(Produit produit,
	 * Long categorieId, Long emplacementId, Long familleId) {
	 * 
	 * if (categorieId != null && familleId != null && emplacementId != null) {
	 * produit.setCategorie(getCategorieById(categorieId));
	 * produit.setFamille(getFamilleById(familleId));
	 * produit.setEmplacement(getEmplacementById(emplacementId)); return
	 * produitRepository.save(produit); } return null; }
	 * 
	 * // mise à jour d'un produit public Produit updateProduit(Produit produit,
	 * Long produitId, Long categorieId, Long emplacementId, Long familleId) {
	 * 
	 * Produit produitToUpdate = getProduitById(produitId); Categorie categorie =
	 * getCategorieById(categorieId); Emplacement emplacement =
	 * getEmplacementById(emplacementId); Famille famille =
	 * getFamilleById(familleId);
	 * 
	 * if (produit != null && produitToUpdate != null && getProduitById(produitId)
	 * != null && categorie != null && emplacement != null && famille != null) {
	 * produit.setCategorie(categorie); produit.setFamille(famille);
	 * produit.setEmplacement(emplacement); produit.setId(produitId); return
	 * produitRepository.save(produit); } return null; }
	 * 
	 * // suppression d'un produit public void deleteProduit(Long produitId) {
	 * produitRepository.deleteById(produitId); }
	 * 
	 * // recupérer un produit par son id public Produit getProduitById(Long
	 * produitId) { return produitRepository.findById(produitId) .orElseThrow(() ->
	 * new ResourceNotFoundException("Produit", "Produit Id", 0)); }
	 * 
	 * // recupérer tous les produits public List<Produit> getAllProduits() { return
	 * produitRepository.findAll(); }
	 * 
	 * // Rechercher les produit par mot clé et par lot public List<Produit>
	 * getProduitByNameContaining(String nomProduit) { return
	 * produitRepository.findByDesignationContaining(nomProduit); }
	 * 
	 * // affecter des produits au services public void affecterProduit(Service
	 * service, Long serviceId, Long produitId) { // Service ServiceToUpdate =
	 * serviceRepository.findById(serviceId); // Produit produitToUpdate =
	 * getProduitById(produitId); // if(produitToUpdate != null && serviceToUpdate
	 * != null) { // ServiceToUpdate.ge // serviceRepository.save(serviceToUpdate);
	 * // }
	 * 
	 * }
	 */
}
