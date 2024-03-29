package co.iaf.entity.admission;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.NatureParametre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_parametre_soins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ParametreDeSoin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private String unite;
	private NatureParametre natureParametre;
	@Column(name = "is_controle")
	private boolean isControle;
	@Column(name = "valeur_min")
	private float valeurMin;
	@Column(name = "valeur_max")
	private float valeurMax;

	// un parametre de soin appartient à un type de parametre
	@ManyToOne
	@JoinColumn(name = "type_parametre_id")
	private TypeParametreSoin typeParametreSoin;

	// un paramètre de soins peut avoir plusieurs prises
	@OneToMany(mappedBy = "parametreDeSoin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<PriseParametreSoin> prisesParametreSoin = new ArrayList<>();
}
