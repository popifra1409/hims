package co.iaf.entity.parametrage;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import co.iaf.entity.pharmacie.Commande;
import co.iaf.entity.pharmacie.Livraison;
import co.iaf.entity.pharmacie.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_fournisseurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Fournisseur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_fournisseur")
	private String nmroFournisseur;

	@Column(name = "nom_societe")
	private String nomSociete;

	@Column(name = "nom_contact")
	private String nomContact;

	@Column(name = "niu")
	private String niu;

	@Column(name = "numero_registre_commence")
	private String nmroRegistreCmrce;

	@Column(name = "refBancaire")
	private String refBancaire;

	@Column(name = "pays")
	private String pays;

	@Column(name = "ville")
	private String ville;

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "codePostal")
	private String codePostalr;

	@Column(name = "email")
	private String email;

	@Column(name = "observations")
	private String observations;

	// un fournisseur peut livrer plusieurs produits
	@ManyToMany
	@JoinTable(name = "fournisseur_produit")
	private Collection<Produit> produits = new ArrayList<>();

	// un fournisseur peut recevoir plusieurs commandes
	@OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
	private Collection<Commande> commandes = new ArrayList<>();

	// un fournisseur peut effectuer plusierus livraisons
	@OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
	private Collection<Livraison> livraisons = new ArrayList<>();
}
