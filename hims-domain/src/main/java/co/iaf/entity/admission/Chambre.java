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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_chambres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Chambre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String libelle;

	private String description;

	@Column(name = "prix_chambre")
	private float prixChambre;

	@ManyToOne
	@JoinColumn(name = "batiment_id")
	private Batiment batiment;
	// une chambre peut contenir plusieurs lits
	@OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Lit> lits = new ArrayList<>();;
}
