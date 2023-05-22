package co.iaf.entity.pharmacie;

import java.util.ArrayList;
import java.util.Collection;

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

import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_emplacements")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Emplacement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "libelle_emplacement")
	private String libelleEmplacement;

	// un emplacement peut contenir plusieurs enregistrements de livraisons
	@OneToMany(mappedBy = "emplacement", cascade = CascadeType.ALL)
	private Collection<LivraisonCommande> livraisonsCde = new ArrayList<>();

	// un emplacement appartient à un service
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services service;
}
