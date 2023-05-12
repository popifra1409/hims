package co.iaf.service.pharmacie;

import java.util.Date;
import java.util.List;

import co.iaf.entity.pharmacie.Commande;
import co.iaf.entity.pharmacie.LigneCommande;

public interface MouvementExterneService {

	// ======== traitement des commandes aux fournisseurs =======

	// ajouter une commande
	public Commande addNewCommande(Commande commande, Long fournisseurId, Long serviceId);

	// recupérer une commande par son Id
	public Commande getCommandeById(Long commandeId);

	// mise à jour d'une commande
	public Commande updateCommande(Long commandeId, Commande commande);

	// annuler une commande
	public void deleteCommande(Long commandeId);

	// transmettre une commande
	public void tramsmettreCommande();

	// liste des commandes
	public List<Commande> getAllCommandes();

	// rechercher une commande par date
	public List<LigneCommande> getCommandesByDate(Date dateCde);

	// filtre des commandes transmises
	public void getTransmiseCde();

	// filtrer les commandes livrées
	public void getLivreCde();

	// lister les produits d'une commande

	// rechercher une commande par son numéro de bon

	// ======= traitement des livraisons ========

	// ajouter une livraison

	// recupérer une livraison par son id;

	// mise à jour d'une livraison

	// annuler une livraison

	// listes des livraisons

	// rechercher une livraison par date

	// appliquer une formule de calcul du prix de vente;

	// =========== enregistrement des commandes internes===============

	// =========== enregistrement des inventaires =================

}
