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
@Table(name = "iaf_lignes_commandes_interne")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LigneCommandeInterne {

	@EmbeddedId
	private LigneCommandeInternePk id = new LigneCommandeInternePk();

	// un enregistrement de commande interne concerne un produit
	@ManyToOne
	@MapsId("reference")
	@JoinColumn(name = "reference")
	private Produit produit;

	// un enregistrement de commandeinterne concerne une commande interne
	@ManyToOne
	@MapsId("commandeIntId")
	@JoinColumn(name = "commandeInt_id")
	private Commande commandeInterne;

	@Column(name = "date_commande_interne")
	private Date dateCdeInt;

	@Column(name = "quantite_commande_interne")
	private int qteCdeInt;
	
}
