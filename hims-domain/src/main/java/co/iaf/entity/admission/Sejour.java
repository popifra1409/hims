package co.iaf.entity.admission;


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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_sejour")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sejour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code_sejour")
	private String codeSejour;

	@Column(name = "libelle_sejour")
	private String libelleSejour;

	@Column(name = "date_debut")
	private Date dateDebut;

	@Column(name = "date_fin")
	private Date dateFin;

	private String motif;

	@Column(name = "is_sejour_cloture")
	private boolean isSejourCloture;

	// un sejour pour avoir plusieurs hospitalisation
	@OneToMany(mappedBy = "sejour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Hospitalisation> hospitalisations = new ArrayList<>();

}
