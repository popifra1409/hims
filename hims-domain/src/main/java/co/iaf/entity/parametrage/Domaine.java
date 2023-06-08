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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.facturation.Prestation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_domaines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Domaine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "compte_domaine")
	private String compteDomaine;

	@Column(name = "nom_domaine")
	private String designation;

	@Column(name = "lettre_cle")
	private String lettreCle;

	private String description;

	@ManyToOne
	@JoinColumn(name = "domaine_parent")
	private Domaine domaineParent;

	//un domaine appartient à un type de domaine
	@ManyToOne
	@JoinColumn(name = "code_type")
	private TypeDomaine typeDomaine;
	
	//un domaine peut contenir plusieurs prestations
	@OneToMany(mappedBy = "domaine", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Collection<Prestation> prestations = new ArrayList<>();
}
