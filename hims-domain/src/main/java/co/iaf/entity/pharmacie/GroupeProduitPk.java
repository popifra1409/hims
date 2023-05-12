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
public class GroupeProduitPk implements Serializable {

	@Column(name = "produit_principal_id")
	private Long produitPrincipalId;

	@Column(name = "produit_secondaire_id")
	private Long produitSecondaireId;
}
