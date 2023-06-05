	package co.iaf.dao.identification;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.GroupeRegistration;
import co.iaf.entity.identification.GroupeRegistrationPk;

public interface GroupeRegistrationRepository extends JpaRepository<GroupeRegistration, GroupeRegistrationPk>{

}
