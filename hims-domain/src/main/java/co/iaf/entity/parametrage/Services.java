package co.iaf.entity.parametrage;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_services")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_service", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code_service")
	private String codeService;

	@Column(name = "libelle_service")
	private String libelleService;

	@ManyToOne
	@JoinColumn(name = "service_parent_id")
	private Services serviceParent;
	/*
	 * // un service appartient à un type de domaine
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "code_type") private TypeDomaine typeDomaine;
	 * 
	 * @OneToMany(mappedBy = "serviceAccueil", cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY) private Collection<Hospitalisation> hospitalisations = new
	 * ArrayList<>();
	 * 
	 * @OneToMany(mappedBy = "serviceAttache", cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY) private Collection<Agent> agents = new ArrayList<>();
	 * 
	 * // un service est logé dans un bâtiment
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "batiment_id") private Batiment batiment;
	 * 
	 * // un service pour avoir plusieurs produits de pharmacie y affectés
	 * 
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "service_produit") private Collection<Produit> produits =
	 * new ArrayList<>();
	 * 
	 * // un service peut émettre plusieurs commandes
	 * 
	 * @OneToMany(mappedBy = "serviceDemandeur", cascade = CascadeType.ALL) private
	 * Collection<Commande> commandes = new ArrayList<>();
	 * 
	 * // un service peut recevoir plusieurs livraisons de produits
	 * 
	 * @OneToMany(mappedBy = "serviceRecepteur", cascade = CascadeType.ALL) private
	 * Collection<Livraison> livraisons = new ArrayList<>();
	 * 
	 * // un service peut subir plusieurs inventaires
	 * 
	 * @OneToMany(mappedBy = "service", cascade = CascadeType.ALL) private
	 * Collection<Inventaire> inventaires = new ArrayList<>();
	 * 
	 * // un service peut avoir plusieurs emplacements de stockage
	 * 
	 * @OneToMany(mappedBy = "service", cascade = CascadeType.ALL) private
	 * Collection<Emplacement> emplacements = new ArrayList<>();
	 * 
	 * //un service peut recevoir plusieurs commandes internes
	 * 
	 * @OneToMany(mappedBy = "serviceFournisseur", cascade = CascadeType.ALL)
	 * private Collection<CommandeInterne> commandesIntD = new ArrayList<>();
	 * 
	 * //un service peut soumettre plusieurs commandes internes
	 * 
	 * @OneToMany(mappedBy = "serviceDemandeur", cascade = CascadeType.ALL) private
	 * Collection<CommandeInterne> commandesIntF = new ArrayList<>();
	 */
}
