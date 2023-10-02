package com.staragile.Banking.Finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDAORepository accountDAORepository;

    public List<Account> getAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountDAORepository.findAll().forEach(accountList::add);
        return accountList;
    }

    public Optional<Account> getAccount(String id) {
        return accountDAORepository.findById(id);
    }

    public void addAccount(Account account) {
        accountDAORepository.save(account);
    }

    public void updateAccount(String id, Account account) {
        accountDAORepository.save(account);
    }

    public void deleteAccount(String id) {
        accountDAORepository.deleteById(id);
    }
}
