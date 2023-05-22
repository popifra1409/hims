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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.SensibiliteProduit;
import co.iaf.entity.generator.ProduitRefGenerator;
import co.iaf.entity.parametrage.Categorie;
import co.iaf.entity.parametrage.UniteStockage;
import co.iaf.entity.parametrage.UniteUtilisation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reference")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produit_ref")
	@GenericGenerator(name = "produit_ref", strategy = "co.iaf.entity.generator.ProduitRefGenerator", parameters = {
			@Parameter(name = ProduitRefGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = ProduitRefGenerator.VALUE_PREFIX_PARAMETER, value = "PH"),
			@Parameter(name = ProduitRefGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"), })
	@Column(name = "ref_produit")
	private String reference;

	@Column(name = "qr_code", unique = true, length = 100000)
	private String codebarre;

	@Column(name = "nom_produit")
	private String designation;

	@Column(name = "prix_unitaire")
	private float prixUnit;

	@Column(name = "seuil_reapprovisionnement")
	private int seuilReappro;

	@Column(name = "quantite_reapprovisionnement")
	private int qteReappro;

	@Column(name = "stock_alerte")
	private int stockAlerte;

	@ManyToOne
	@JoinColumn(name = "unite_stockage_id")
	private UniteStockage uniteStockage;

	@ManyToOne
	@JoinColumn(name = "unite_utilisation_id")
	private UniteUtilisation uniteUtilisation;

	@Column(name = "coefficient_unite")
	private int coefUnite;

	private String description;

	@Column(name = "is_archive")
	private boolean isArchive;

	@Column(name = "is_desactive")
	private boolean isDesactive;

	@Column(name = "is_disponible")
	private boolean isDiponible;

	@Column(name = "is_groupe_produit")
	private boolean isGroupeProduit;

	@Column(name = "is_refrigere")
	private boolean isRefrigere;

	private SensibiliteProduit sensibilite;

	private String dci;

	@Column(name = "nom_commercial")
	private String nomCommercial;

	@Column(name = "is_liaison_prestation")
	private boolean isLiaisonPrestation;

	@Column(name = "is_PMUP")
	private boolean isPMUP;

	@Column(name = "stock_theorique")
	private int stockTheorique;

	// un produit peut-être constitué par d'autres produits
	@OneToMany(mappedBy = "produitPrincipal", cascade = CascadeType.ALL)
	private Collection<GroupeProduit> groupeProduitsPrincipal = new ArrayList<>();

	@OneToMany(mappedBy = "produitSecondaire", cascade = CascadeType.ALL)
	private Collection<GroupeProduit> groupeProduitsSecondaire = new ArrayList<>();

	// un produit appartient à une famille
	@ManyToOne
	@JoinColumn(name = "famille_id")
	private Famille famille;

	// un produit appartient à une catégorie
	@ManyToOne
	@JoinColumn(name = "categorie_id")
	private Categorie categorie;

	// un produit peut figurer dans plusieurs lignes d'enregistrement
	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
	private Collection<LigneCommande> lignesCde = new ArrayList<>();

	// produit peut comporter plusieurs lots à la livraison
	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
	private Collection<Lot> lots = new ArrayList<>();

	// un produit peut figurer dans plusieurs lignes d'enregistrement de commande
	// interne
	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
	private Collection<LigneCommandeInterne> lignesCdeInt = new ArrayList<>();
}
