package pl.coderslab.sports_betting.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
