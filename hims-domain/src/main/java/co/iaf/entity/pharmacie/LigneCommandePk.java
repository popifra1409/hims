package co.iaf.entity.pharmacie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandePk implements Serializable {

	@Column(name = "commande_id")
	private Long commandeId;

	@Column(name = "produit_id")
	private Long produitId;
}
