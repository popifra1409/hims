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
@Table(name = "iaf_livraisons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Livraison {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_bon_livraison")
	private String nmroBonLivraison;

	@Column(name = "is_commande")
	private boolean isCommande;

	@Column(name = "observation")
	private String observation;

	@Column(name = "prix_vente")
	private float prixVente;

	// une livraison peut avoir plusieurs lignes d'enregistrement de commande
	@OneToMany(mappedBy = "livraison", cascade = CascadeType.ALL)
	private Collection<LivraisonCommande> livraisonsCde = new ArrayList<>();

	// un livraison concerne plusieurs lots de produits
	@OneToMany(mappedBy = "livraison", cascade = CascadeType.ALL)
	private Collection<Lot> lots = new ArrayList<>();

	// une livraison est effectué par un fournisseur
	@ManyToOne
	@JoinColumn(name = "fournisseur_id")
	private Fournisseur fournisseur;

	// une livraison concerne un service
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services serviceRecepteur;
}
