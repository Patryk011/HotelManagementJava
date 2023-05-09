package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.RoleDTO;
import pl.project.entity.Role;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {


    public RoleDTO mapToDto(Role role){
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setAdmin(roleDTO.isAdmin());
        roleDTO.setWorker(roleDTO.isWorker());

        return roleDTO;
    }

    public List<RoleDTO> mapToDto(Collection<Role> roles){
        return roles.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Role mapFromDto(RoleDTO roleDTO){
        Role role = new Role();

        role.setAdmin(roleDTO.isAdmin());
        role.setWorker(roleDTO.isWorker());

        return role;
    }

    public Role mapFromDto(Role role, RoleDTO roleDTO){
        role.setAdmin(roleDTO.isAdmin());
        role.setWorker(roleDTO.isWorker());

        return role;
    }
}

