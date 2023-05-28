package pl.project.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {


    private final RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
