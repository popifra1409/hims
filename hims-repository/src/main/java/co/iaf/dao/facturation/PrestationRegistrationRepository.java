package co.iaf.dao.facturation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.facturation.PrestationRegistration;
import co.iaf.entity.facturation.PrestationRegistrationPk;

public interface PrestationRegistrationRepository extends JpaRepository<PrestationRegistration, PrestationRegistrationPk>{

}
