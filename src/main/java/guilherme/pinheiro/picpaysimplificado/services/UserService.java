package guilherme.pinheiro.picpaysimplificado.services;

import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import guilherme.pinheiro.picpaysimplificado.domain.user.UserDTO;
import guilherme.pinheiro.picpaysimplificado.domain.user.UserType;
import guilherme.pinheiro.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(UserDTO user){
        User newUser = new User(user);
        saveUser(newUser);
        return newUser;
    }

    public void saveUser(User newUser) {
        this.repository.save(newUser);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public boolean validateUser(User payer, BigDecimal amount) throws Exception {

        if (payer.getUserType() == UserType.MERCHANT){
            throw new Exception("Merchant user can't transfer");
        }

        if(payer.getBalance().compareTo(amount) < 0){
            throw new Exception("Unenough balance");
        }

        return true;
    }
}
