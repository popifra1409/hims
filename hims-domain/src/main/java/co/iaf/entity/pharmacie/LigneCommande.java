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
@Table(name = "iaf_lignes_commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LigneCommande {

	@EmbeddedId
	private LigneCommandePk id = new LigneCommandePk();

	// une ligne de commande concerne un produit
	@ManyToOne
	@MapsId("reference")
	@JoinColumn(name = "reference")
	private Produit produit;

	// une ligne de commande concerne une commande
	@ManyToOne
	@MapsId("commandeId")
	@JoinColumn(name = "commande_id")
	private Commande commande;
	
	@Column(name = "date_commande")
	private Date dateCde;
	
	@Column(name = "quantite_commande")
	private int qteCde;
	
	@Column(name = "prix_commande")
	private double prixCde;
}
