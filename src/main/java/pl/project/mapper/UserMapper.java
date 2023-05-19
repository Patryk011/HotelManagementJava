package pl.project.mapper;


import org.springframework.stereotype.Component;
import pl.project.dto.RoleDTO;
import pl.project.dto.UserDTO;
import pl.project.entity.Role;
import pl.project.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public UserDTO mapToDto(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleId(user.getRoleId().getId());

        return userDTO;
    }

    public List<UserDTO> mapToDto(Collection<User> users){
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public User mapFromDto(UserDTO userDTO){
        User user = new User();

        Role role = new Role();
        role.setId(userDTO.getId());

        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRoleId(role);

        return user;
    }

    public User mapFromDto(UserDTO userDTO, User user){

        Role role = new Role();
        role.setId(userDTO.getId());

        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRoleId(role);

        return user;
    }
}
