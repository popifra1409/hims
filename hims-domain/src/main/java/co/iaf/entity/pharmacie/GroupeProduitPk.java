package co.iaf.entity.pharmacie;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupeProduitPk implements Serializable {

	private String referencePrincipalId;

	private String referenceSecondaireId;
}
