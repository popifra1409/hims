package co.iaf.entity.pharmacie;

import java.util.Date;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_facture_commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FactureCommande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_facture")
	private String numFacture;

	@Column(name = "numero_facture_fournisseur")
	private String numFactureFournisseur;

	@Column(name = "date_facture")
	private Date dateFactrure;

	@Column(name = "is_facture_pro")
	private boolean isFacturePro;

	@Column(name = "is_facture_def")
	private boolean isFactureDef;

	@Column(name = "is_payee")
	private boolean isPayee;

	@Column(name = "is_enagagee")
	private boolean isEngagee;

	@Column(name = "observation")
	private boolean observation;

	//une facture concerne une commande
	@ManyToOne
	@JoinColumn(name = "commande_id")
	private Commande commande;
}
