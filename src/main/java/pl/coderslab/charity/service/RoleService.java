package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.authentication.Role;
import pl.coderslab.charity.repository.RoleRepository;

@Service
public class RoleService {


    @Autowired
    RoleRepository roleRepository;

   public Role findFirstByRoleName(String roleName){

        return roleRepository.findFirstByRoleName(roleName);
    }
    public Role save(Role role){
     return   roleRepository.save(role);
    }
}
