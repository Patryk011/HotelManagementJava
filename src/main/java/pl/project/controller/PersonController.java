package pl.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {


    private PersonService personService;
}
