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

import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_lignes_inventaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LigneInventaire {

	@EmbeddedId
	private LigneInventairePk id = new LigneInventairePk();

	// une ligne d'inventaire concerne un inventaire
	@ManyToOne
	@MapsId("inventaireId")
	@JoinColumn(name = "inventaire_id")
	private Inventaire inventaire;

	// une ligne d'inventaire concerne un lot de produit
	@ManyToOne
	@MapsId("lotId")
	@JoinColumn(name = "lot_id")
	private Lot lot;

	// une ligne d'inventaire concerne un emplacement
	@ManyToOne
	@MapsId("emplacementId")
	@JoinColumn(name = "emplacement_id")
	private Emplacement emplacement;

	// une ligne d'inventaire concerne un service
	@ManyToOne
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Services service;

	@Column(name = "date_inventaire")
	private Date dateInventaire;

	@Column(name = "quantite_inventaire")
	private int qteInventaire;

	private String commentaire;
}
