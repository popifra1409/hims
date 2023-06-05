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
public class LigneInventairePk implements Serializable{

	@Column(name = "lot_id")
	private Long LotId;

	@Column(name = "inventaire_id")
	private Long inventaireId;
}
