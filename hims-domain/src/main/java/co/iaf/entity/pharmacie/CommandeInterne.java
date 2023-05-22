package co.iaf.entity.pharmacie;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "iaf_commandes_interne")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommandeInterne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_bon_commande_int")
	private String nmroBonCdeInt;

	// une commande est émise par un service demandeur
	@ManyToOne
	@JoinColumn(name = "serviced_id")
	private Services serviceDemandeur;

	// une commande est effectuée par un service fournisseur
	@ManyToOne
	@JoinColumn(name = "servicef_id")
	private Services serviceFournisseur;

	@Column(name = "motif_sortie")
	private String motifSortie;

	private String observation;

	@Column(name = "is_traitee")
	private boolean isTraitee;

	@Column(name = "is_livree")
	private boolean isLivree;

	@Column(name = "type_commande_interne")
	private TypeCommandeInterne typeCdeInt;

	@Column(name = "option_commande_interne")
	private OptionCommandeInterne optionCdeInt;

	// une commande peut avoir plusieurs lignes d'enregistrement de commande interne
	@OneToMany(mappedBy = "commandeInterne", cascade = CascadeType.ALL)
	private Collection<LigneCommandeInterne> lignesCdeInt = new ArrayList<>();
}
