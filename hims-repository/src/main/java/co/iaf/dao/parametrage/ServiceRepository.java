package co.iaf.dao.parametrage;

import org.springframework.data.jpa.repository.JpaRepository;
import co.iaf.entity.parametrage.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {

}
