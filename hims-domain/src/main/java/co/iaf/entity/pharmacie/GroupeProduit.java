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
@Table(name = "iaf_groupe_produits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GroupeProduit {

	@EmbeddedId
	private GroupeProduitPk id = new GroupeProduitPk();

	// un groupe produit regroupe plusieurs lignes de produit principal
	@ManyToOne
	@MapsId("produitPrincipalId")
	@JoinColumn(name = "produit_principal_id")
	private Produit produitPrincipal;

	// un groupe produit regroupe plusieurs lignes de produit secondaire
	@ManyToOne
	@MapsId("produitSecondaireId")
	@JoinColumn(name = "produit_secondaire_id")
	private Produit produitSecondaire;

	@Column(name = "quantite_produit")
	private int qteProduit;

	@Column(name = "prix_produit")
	private double prixProduit;
}
