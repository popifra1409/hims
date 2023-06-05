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

import co.iaf.entity.parametrage.Fournisseur;
import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_commandes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_bon_commande")
	private String nmroBonCde;

	@Column(name = "is_transmis")
	private boolean isTransmis;

	@Column(name = "is_livre")
	private boolean isLivre;

	@Column(name = "observation")
	private String observation;

	// une commande peut avoir plusieurs lignes d'enregistrement
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
	private Collection<LigneCommande> lignesCde = new ArrayList<>();

	// une commande peut avoir plusieurs lignes d'enregistrement de livraison
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
	private Collection<LivraisonCommande> livraisonsCde = new ArrayList<>();

	// une commande est adressée à un fournisseur
	@ManyToOne
	@JoinColumn(name = "fournisseur_id")
	private Fournisseur fournisseur;

	// une commande est émise par un service
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services serviceDemandeur;
}
