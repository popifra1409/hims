package co.iaf.entity.pharmacie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_lots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_lot")
	private String nmroLot;

	private Date datePerremption;

	private int qteLot;

	// un lot concerne un produit
	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;

	// un lot figure dans une livraison
	@ManyToOne
	@JoinColumn(name = "livraison_id")
	private Livraison livraison;

	// un lot peut apparaitre dans plusieurs liges d'inventaire
	@OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
	private Collection<LigneInventaire> lignesIventaire = new ArrayList<>();
}
