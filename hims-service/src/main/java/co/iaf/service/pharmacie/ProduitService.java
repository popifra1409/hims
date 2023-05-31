package co.iaf.service.pharmacie;

import java.util.List;

import co.iaf.entity.pharmacie.Produit;
import co.iaf.entity.pharmacie.QrCodeProduit;

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

	// ajouter un nouveau Qr code pour le produit
	public void addNewQrCode(QrCodeProduit qrCodeProduit, String refprod);

	// generate QRcode du produit
	public String getQRCode(String reference);

	// recuperer le qrcode d'un produit
	public String getQrCodeProduit(Produit produit);

	// recuprer un produit par qr code
	public Produit getByQrCodeProduit(Long qrcodeId);

	// recuperer leQr Code par son ID
	public QrCodeProduit getQrCodeById(Long id);

	// grouper les produits
	// public GroupeProduit addNewGroupeProduit(GroupeProduit groupeProduit, String
	// ref1, String ref2);

}
