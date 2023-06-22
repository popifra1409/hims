package co.iaf.entity.parametrage;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.admission.Batiment;
import co.iaf.entity.facturation.PrestationRegistration;
import co.iaf.entity.grh.Agent;
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

	// un service appartient à un type de domaine
	@ManyToOne
	@JoinColumn(name = "code_type")
	private TypeDomaine typeDomaine;

	// un service est logé dans un bâtiment
	@ManyToOne
	@JoinColumn(name = "batiment_id")
	private Batiment batiment;

	// un service peut avoir plusieurs agents
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Agent> agents = new ArrayList<>();

	// un service peut avoir plusieurs enregistrements de prestations
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PrestationRegistration> prestationsRegistration = new ArrayList<>();
}
