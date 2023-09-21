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
        // The registration will be successful if the username is not
        // blank, the password is at least 4 characters long, and an Account with that
        // username does not already exist.

        if (newAccount.getUsername() != "" && newAccount.getPassword().length() > 4) {
            Account doesAccountExist = accountRepo.findByUsername(newAccount.getUsername());

            if (doesAccountExist != null) {

                // Duplicate username, response status should be 409. (Conflict)

                return new ResponseEntity<Account>(HttpStatus.CONFLICT);
            } else {
                // The response status should be 200 OK
                accountRepo.save(newAccount);
                return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
            }

        }
        // Not successful for some other reason, the status should be 400. (Client
        // error)
        return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);

    }

}
