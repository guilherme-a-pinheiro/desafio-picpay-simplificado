package guilherme.pinheiro.picpaysimplificado.controller;

import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import guilherme.pinheiro.picpaysimplificado.domain.user.UserDTO;
import guilherme.pinheiro.picpaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = service.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
