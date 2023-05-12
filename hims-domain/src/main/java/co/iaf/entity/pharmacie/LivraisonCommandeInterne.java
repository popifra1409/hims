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
@Table(name = "iaf_livraisons_commande_interne")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LivraisonCommandeInterne {

	@EmbeddedId
	private LivraisonCommandeInternePk id = new LivraisonCommandeInternePk();

	// un en enregistrement de livraison interne concerne une commande interne
	@ManyToOne
	@MapsId("commandeIntId")
	@JoinColumn(name = "commandeInt_id")
	private Commande commandeInterne;

	// une enregistrement de livraison interne concerne une livraison interne
	@ManyToOne
	@MapsId("livraisonIntId")
	@JoinColumn(name = "livraisonInt_id")
	private Livraison livraisonInterne;

	@Column(name = "qte_livraison_interne")
	private int qteLivreInt;

	@Column(name = "date_livraison_interne")
	private Date dateLivraisonInt;

}
