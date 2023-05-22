package co.iaf.dao.pharmacie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {

	// get product by name containt
	List<Produit> findByReferenceContainingOrDesignationContaining(String reference, String designation);

	// liste des produits désactivés
	List<Produit> findByIsDesactiveTrue();
	
	//liste des produits archivés
	List<Produit> findByIsArchiveTrue();
}
