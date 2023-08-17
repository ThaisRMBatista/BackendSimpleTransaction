package com.transferenciasimplificada.services;

import com.transferenciasimplificada.domain.transaction.Transaction;
import com.transferenciasimplificada.domain.user.User;
import com.transferenciasimplificada.dtos.TransactionDTO;
import com.transferenciasimplificada.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    protected TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());
        checkAuthorization(transaction, sender);
        Transaction newTransaction = createNewTransaction(transaction, sender, receiver);
//        sendNotificationTransaction(sender, receiver);
        return newTransaction;
    }

    private void checkAuthorization(TransactionDTO transaction, User sender) throws Exception {
        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }
    }

    private Transaction createNewTransaction(TransactionDTO transaction, User sender, User receiver) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        saveTransaction(newTransaction);
        updateBalanceSender(transaction, sender);
        updateBalanceReceiver(transaction, receiver);
        return newTransaction;
    }

    private void updateBalanceReceiver(TransactionDTO transaction, User receiver) {
        receiver.setBalance(receiver.getBalance().add(transaction.value()));
        this.userService.saveUser(receiver);
    }

    private void updateBalanceSender(TransactionDTO transaction, User sender) {
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        this.userService.saveUser(sender);
    }

    private void saveTransaction(Transaction newTransaction) {
        this.repository.save(newTransaction);
    }

    private void sendNotificationTransaction(User sender, User receiver) throws Exception {
        this.notificationService.sendNotification(sender, "Transação realizada com Sucesso!");
        this.notificationService.sendNotification(receiver, "Transação recebida com Sucesso!");
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
        return authorizationResponse.getStatusCode() == HttpStatus.OK && authorizationResponse.getBody().get("message").equals("Autorizado");
    }
}
