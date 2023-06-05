package co.iaf.entity.parametrage;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.admission.Batiment;
import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.grh.Agent;
import co.iaf.entity.pharmacie.Commande;
import co.iaf.entity.pharmacie.CommandeInterne;
import co.iaf.entity.pharmacie.Emplacement;
import co.iaf.entity.pharmacie.Inventaire;
import co.iaf.entity.pharmacie.Livraison;
import co.iaf.entity.pharmacie.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_services")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code_service")
	private String codeService;

	@Column(name = "libelle_service")
	private String libelleService;

	@ManyToOne
	@JoinColumn(name = "service_parent")
	private Services serviceParent;

	// un service appartient à un type de domaine
	@ManyToOne
	@JoinColumn(name = "code_type")
	private TypeDomaine typeDomaine;

	@OneToMany(mappedBy = "serviceAccueil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Hospitalisation> hospitalisations = new ArrayList<>();

	@OneToMany(mappedBy = "serviceAttache", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Agent> agents = new ArrayList<>();

	// un service est logé dans un bâtiment
	@ManyToOne
	@JoinColumn(name = "batiment_id")
	private Batiment batiment;

	// un service pour avoir plusieurs produits de pharmacie y affectés
	@ManyToMany
	@JoinTable(name = "service_produit")
	private Collection<Produit> produits = new ArrayList<>();

	// un service peut émettre plusieurs commandes
	@OneToMany(mappedBy = "serviceDemandeur", cascade = CascadeType.ALL)
	private Collection<Commande> commandes = new ArrayList<>();

	// un service peut recevoir plusieurs livraisons de produits
	@OneToMany(mappedBy = "serviceRecepteur", cascade = CascadeType.ALL)
	private Collection<Livraison> livraisons = new ArrayList<>();

	// un service peut subir plusieurs inventaires
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private Collection<Inventaire> inventaires = new ArrayList<>();

	// un service peut avoir plusieurs emplacements de stockage
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private Collection<Emplacement> emplacements = new ArrayList<>();
	
	//un service peut recevoir plusieurs commandes internes
	@OneToMany(mappedBy = "serviceFournisseur", cascade = CascadeType.ALL)
	private Collection<CommandeInterne> commandesIntD = new ArrayList<>();
	
	//un service peut soumettre plusieurs commandes internes
	@OneToMany(mappedBy = "serviceDemandeur", cascade = CascadeType.ALL)
	private Collection<CommandeInterne> commandesIntF = new ArrayList<>();
}
