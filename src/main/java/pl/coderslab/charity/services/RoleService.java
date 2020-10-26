package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.classes.Role;
import pl.coderslab.charity.repositories.RoleRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getByName (String name){
        return roleRepository.getByName(name);
    }
}
