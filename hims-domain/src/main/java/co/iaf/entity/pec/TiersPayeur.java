package co.iaf.entity.pec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_tiers_payeurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_tiers", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class TiersPayeur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_court")
	private String nomCourt;

	@Column(name = "nom_tiers")
	private String nomTiers;

	@Column(name = "logo_tiers")
	private String logoTiers;

	@Column(name = "numero_identification_unique")
	private String niu;

	@Column(name = "nom_responsable")
	private String nomResponsable;

	private String telephone;

	private String adresse;

	private String fax;

	private boolean isDesactive;

	private float tva;

	// un tiers payeurs peut gérer plusierus prise en charge
	@OneToMany(mappedBy = "tiersPayeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PriseEnCharge> prisesEnCharge = new ArrayList<>();
}
