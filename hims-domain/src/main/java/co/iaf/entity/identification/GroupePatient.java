package co.iaf.entity.identification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.pec.PriseEnCharge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_groupes_patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GroupePatient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_groupe")
	private String nomGroupe;

	// un groupe patient peut être présent dans plusieurs groupes d'enregistrement
	@OneToMany(mappedBy = "groupePatient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<GroupeRegistration> groupesRegistration = new ArrayList<>();

	// un groupe patient peut avoir multiples prise en charge
	@OneToMany(mappedBy = "groupePatient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PriseEnCharge> prisesEnCharge = new ArrayList<>();
}
