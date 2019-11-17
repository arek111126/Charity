package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.authentication.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT r From Role r  where r.roleName  = :roleName")
    Role findFirstByRoleName(@Param("roleName") String roleName);



}
