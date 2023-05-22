package co.iaf.entity.parametrage;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import co.iaf.entity.pec.TiersPayeur;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("HOPITAL")
public class Etablissement extends TiersPayeur{

	@Column(name = "logo_hopital")
	private String logoHopital;
}
