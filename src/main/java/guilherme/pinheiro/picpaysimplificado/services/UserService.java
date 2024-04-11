package guilherme.pinheiro.picpaysimplificado.services;

import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import guilherme.pinheiro.picpaysimplificado.domain.user.UserDTO;
import guilherme.pinheiro.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(UserDTO user){
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    private void saveUser(User newUser) {
        this.repository.save(newUser);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

}
