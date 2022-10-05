package com.fasttrackit.JavaEncyclopediaProject.userdata;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping
    UserAccount createAccount(@RequestBody UserAccount userAccount) {
        return userAccountService.createAccount(userAccount);
    }

    @GetMapping
    List<UserAccount> getUsers() {
        return userAccountService.getUsers();
    }

    @GetMapping("{id}")
    UserAccount getUser(@PathVariable Integer id) {
        return userAccountService.getUser(id);
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable Integer id) {
        userAccountService.deleteUser(id);
    }

    @PutMapping("{id}")
    UserAccount editUser(@PathVariable Integer id, @RequestBody UserAccount userAccount) {
        return userAccountService.editUser(id, userAccount);
    }
}
