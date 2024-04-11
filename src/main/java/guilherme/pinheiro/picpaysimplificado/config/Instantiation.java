package guilherme.pinheiro.picpaysimplificado.config;

import guilherme.pinheiro.picpaysimplificado.domain.transaction.TransactionDTO;
import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import guilherme.pinheiro.picpaysimplificado.domain.user.UserDTO;
import guilherme.pinheiro.picpaysimplificado.domain.user.UserType;
import guilherme.pinheiro.picpaysimplificado.repositories.UserRepository;
import guilherme.pinheiro.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        UserDTO dto1 = new UserDTO("Jorge", "987456123", "jorge@email", "123456" , UserType.MERCHANT, new BigDecimal(500));
        UserDTO dto2 = new UserDTO("Guilherme", "123456789", "gui@email", "123456" , UserType.COMMON, new BigDecimal(1000));

        User jorge = new User(dto1);
        User gui = new User(dto2);

        userRepository.saveAll(Arrays.asList(jorge, gui));

        transactionService.createTransaction(new TransactionDTO(new BigDecimal(500), gui.getId(), jorge.getId()));
    }
}