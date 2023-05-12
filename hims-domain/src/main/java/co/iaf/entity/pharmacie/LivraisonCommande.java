package co.iaf.entity.pharmacie;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_livraisons_commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LivraisonCommande {

	@EmbeddedId
	private LivraisonCommandePk id = new LivraisonCommandePk(); 

	// un en enregistrement de livraison concerne une commande
	@ManyToOne
	@MapsId("commandeId")
	@JoinColumn(name = "commande_id")
	private Commande commande;

	// une enregistrement de livraison concerne une livraison
	@ManyToOne
	@MapsId("livraisonId")
	@JoinColumn(name = "livraison_id")
	private Livraison livraison;

	@Column(name = "qte_livraison")
	private int qteLivre;

	@Column(name = "date_livraison")
	private Date dateLivraison;

	@Column(name = "prix_livraison")
	private double prixLivraison;

	@Column(name = "prix_vente")
	private double prixVente;

	@Column(name = "is_commande")
	private boolean isCommande;

	private String observation;

	// un enregistrement de livraison est logé à un emplacement
	@ManyToOne
	@JoinColumn(name = "emplacement_id")
	private Emplacement emplacement;

}
