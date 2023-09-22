package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepo;

    public ResponseEntity<Account> registerAccount(Account newAccount) {

        if (newAccount.getUsername() != "" && newAccount.getPassword().length() > 4) {
            Account doesAccountExist = accountRepo.findByUsername(newAccount.getUsername());

            if (doesAccountExist != null) {
                return new ResponseEntity<Account>(HttpStatus.CONFLICT);
            } else {
                accountRepo.save(newAccount);
                return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
            }

        }

        return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Account> loginAccount(Account accountToLogin) {
        Account doesAccountExist = accountRepo.findByUsernameAndPassword(accountToLogin.getUsername(),
                accountToLogin.getPassword());
        if (doesAccountExist != null) {
            return new ResponseEntity<Account>(doesAccountExist, HttpStatus.OK);
        } else
            return new ResponseEntity<Account>(HttpStatus.UNAUTHORIZED);
    }

    public boolean getAccountById(Integer posted_by) {

        return !accountRepo.findById(posted_by).isEmpty();
    }

}
