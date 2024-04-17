package guilherme.pinheiro.picpaysimplificado.controller;

import guilherme.pinheiro.picpaysimplificado.domain.transaction.Transaction;
import guilherme.pinheiro.picpaysimplificado.domain.transaction.TransactionDTO;
import guilherme.pinheiro.picpaysimplificado.services.NotificationService;
import guilherme.pinheiro.picpaysimplificado.services.TransactionService;
import guilherme.pinheiro.picpaysimplificado.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        var newTransaction = service.createTransaction(transaction);
        String notificationPayer = "payer notification: " + notificationService.sendNotification(userService.findUserById(transaction.payerId()), "Successful transaction");
        String notificationPayee = "payee notification: " + notificationService.sendNotification(userService.findUserById(transaction.payeeId()), "The amount of R$ " + newTransaction.getAmount() + " was transferred to your account");

        return ResponseEntity.ok().body(Arrays.asList(notificationPayer, notificationPayee));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll(){
        List<Transaction> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
