package co.iaf.dao.admission;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.admission.Sejour;

public interface SejourRepository extends JpaRepository<Sejour, Long> {

	public Sejour findSejourByCodeSejour(String codeSejour);
}
