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
public class LivraisonCommandeInternePk implements Serializable {

	@Column(name = "commandeInt_id")
	private Long commandeIntId;

	@Column(name = "livraisonInt_id")
	private Long livraisonIntId;
}
