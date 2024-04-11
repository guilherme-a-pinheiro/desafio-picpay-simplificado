package guilherme.pinheiro.picpaysimplificado.controller;

import guilherme.pinheiro.picpaysimplificado.domain.transaction.Transaction;
import guilherme.pinheiro.picpaysimplificado.domain.transaction.TransactionDTO;
import guilherme.pinheiro.picpaysimplificado.services.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transcations")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionDTO transaction){
        var newTransaction = service.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
