package co.iaf.entity.grh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

import co.iaf.entity.admission.Intervention;
import co.iaf.entity.enums.Sexe;
import co.iaf.entity.enums.TypeAgent;
import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_agents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String matricule;

	@Column(name = "nom_agent")
	private String nomAgent;

	@Column(name = "prenom_agent")
	private String prenomAgent;

	@Column(name = "adresse_agent")
	private String adresseAgent;

	private String telephone;

	private String nationalite;

	@Column(name = "sexe_agent")
	private Sexe sexeAgent;

	@Column(name = "date_naissance")
	private Date dateNaissanceAgent;

	@Column(name = "code_hospit")
	private String lieuNaissanceAgent;

	private String profession;

	private String email;

	private String religion;
	
	private TypeAgent typeAgent;

	// un agent peut effectuer une ou plusieurs interventions
	@OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Intervention> interventions = new ArrayList<>();

	// un agent est attaché à un service
	@ManyToOne
	@JoinColumn(name = "service_attache")
	private Services serviceAttache;

	// Un agent peut avoir plusieurs informations supplémentaires
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<AgentInfosSup> agentInfosSup = new ArrayList<>();
}
