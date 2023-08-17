package com.transferenciasimplificada.services;

import com.transferenciasimplificada.domain.user.User;
import com.transferenciasimplificada.domain.user.UserType;
import com.transferenciasimplificada.dtos.UserDTO;
import com.transferenciasimplificada.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        checkUserAuthorizationTransaction(sender);
        checkInsufficientBalance(sender, amount);
    }

    public void checkUserAuthorizationTransaction(User sender) throws Exception {
        if (sender.getUserType() != UserType.COMMOM) {
            throw new Exception("Usuário do tipo Logista não está autorizado a realizar transação.");
        }
    }

    public void checkInsufficientBalance(User sender, BigDecimal amount) throws Exception {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo Insuficiente.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(
                () -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
