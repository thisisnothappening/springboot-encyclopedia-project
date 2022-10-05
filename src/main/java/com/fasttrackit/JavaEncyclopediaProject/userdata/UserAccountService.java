package com.fasttrackit.JavaEncyclopediaProject.userdata;

import com.fasttrackit.JavaEncyclopediaProject.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount createAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public List<UserAccount> getUsers() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUser(Integer id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    public void deleteUser(Integer id) {
        userAccountRepository.delete(userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!")));
    }

    public UserAccount editUser(Integer id, UserAccount userAccount) {
        UserAccount existingUser = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        existingUser.setUsername(userAccount.getUsername());
        existingUser.setPassword(userAccount.getPassword());
        return userAccountRepository.save(existingUser);
    }
}
