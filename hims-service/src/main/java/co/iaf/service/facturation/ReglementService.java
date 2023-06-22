package co.iaf.service.facturation;

import co.iaf.entity.facturation.Reglement;

public interface ReglementService {

	// ajouter un nouveau reglement
	public Reglement addNewReglement(Reglement reglement, String factureId, String patientId, Long caissierId);
}
