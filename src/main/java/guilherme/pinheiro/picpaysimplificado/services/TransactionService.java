package guilherme.pinheiro.picpaysimplificado.services;

import guilherme.pinheiro.picpaysimplificado.domain.transaction.Transaction;
import guilherme.pinheiro.picpaysimplificado.domain.transaction.TransactionDTO;
import guilherme.pinheiro.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
            var payer = userService.findUserById(transaction.payerId());
            var payee = userService.findUserById(transaction.payeeId());

            userService.validateUser(payer, transaction.amount());

            boolean isAuthorized = authorizeTransaction();

            if (!isAuthorized){
                throw new Exception("Unauthorized transaction");
            }

            Transaction newTransaction = new Transaction();
            newTransaction.setAmount(transaction.amount());
            newTransaction.setPayer(payer);
            newTransaction.setPayee(payee);
            newTransaction.setTransactionTime(LocalDateTime.now());

            payer.setBalance(payer.getBalance().subtract(transaction.amount()));
            payee.setBalance(payee.getBalance().add(transaction.amount()));

            repository.save(newTransaction);
            userService.saveUser(payer);
            userService.saveUser(payee);

            notificationService.sendNotification(payer, "Successful transaction");
            notificationService.sendNotification(payee, "The amount of R$ " + newTransaction.getAmount() + " was transferred to your account");

            return newTransaction;
    }

    public boolean authorizeTransaction(){
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (response.getStatusCode() == HttpStatus.OK){
            String message = (String) response.getBody().get("message");
            "Authorized".equalsIgnoreCase(message);
            return true;
        }
        else {
            return false;
        }
    }
}
