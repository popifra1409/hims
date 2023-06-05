package co.iaf.dao.authentification;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.authentification.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRoleName(String roleName);
}
