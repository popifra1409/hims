package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Produit;
import co.iaf.entity.pharmacie.QrCodeProduit;

public interface QrCodeProduitRepository extends JpaRepository<QrCodeProduit, Long>{

	//recuperer le qrcode d'un produit
	QrCodeProduit findByProduit(Produit produit);
}
