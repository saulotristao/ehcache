package be.g00glen00b.web;

import be.g00glen00b.dto.TaskDTO;
import be.g00glen00b.dto.User;
import be.g00glen00b.service.TaskServiceImpl;
import be.g00glen00b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getOne (@PathVariable("id") int id) {
        return service.getUser(id);
    }

    @PostMapping
    public User insertOne(@RequestParam("id") int id,
                          @RequestParam("f_name") String f_name,
                          @RequestParam("l_name") String l_name){
        return service.addUser(id, f_name, l_name);
    }

}
