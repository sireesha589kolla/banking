package com.staragile.Banking.Finance;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountSvc;

    @RequestMapping("/hello")
    public String doSomething() {
        return "Hello World";
    }

    @RequestMapping("/seeddata")
    public String seedData() {
        System.out.println("Inside seeddata");
        Account account1 = new Account("1", "Vilas", "Vilas Address", "123456789");
        accountSvc.addAccount(account1);

        Account account2 = new Account("2", "Tousif", "Tousif Address", "987654321");
        accountSvc.addAccount(account2);

        return "Data seeded successfully!!!";
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccountById(@PathVariable String id) {
        System.out.println("Inside getAccountById");
        return accountSvc.getAccount(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountSvc.getAccounts();
    }

    @PostMapping
    public String addAccount(@RequestBody Account account) {
        accountSvc.addAccount(account);
        return "Successfully added!!";
    }

    @PutMapping("/{id}")
    public String updateAccount(@RequestBody Account account, @PathVariable String id) {
        accountSvc.updateAccount(id, account);
        return "Updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable String id) {
        accountSvc.deleteAccount(id);
        return "Deleted successfully";
    }
}
