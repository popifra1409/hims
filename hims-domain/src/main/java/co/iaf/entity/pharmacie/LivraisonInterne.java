package co.iaf.entity.pharmacie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.OptionCommandeInterne;
import co.iaf.entity.enums.TypeCommandeInterne;
import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_livraisons_Interne")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LivraisonInterne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_bon_livraison_interne")
	private String nmroBonLivraisonInt;

	@Column(name = "is_commande_int")
	private boolean isCommandeInt;

	@Column(name = "observation")
	private String observation;

	@Column(name = "type_commande_interne")
	private TypeCommandeInterne typeCdeInt;

	@Column(name = "option_commande_interne")
	private OptionCommandeInterne optionCdeInt;

	// une livraison est effectuée par un service fournisseur
	@ManyToOne
	@JoinColumn(name = "serviced_id")
	private Services serviceDemandeur;

	// une livraison est destinée à un service demandeur
	@ManyToOne
	@JoinColumn(name = "servicef_id")
	private Services serviceFournisseur;

}
