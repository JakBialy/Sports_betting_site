package pl.coderslab.sports_betting.Service;


import pl.coderslab.sports_betting.Entity.Role;

public interface RoleService {

    Role getOrCreate(String roleName);
}
