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

    /*
     * @Summary: Users should create a new Account on the endpoint
     * 
     * @Param: Account to add
     * 
     * @Return: Response status and message body
     */

    public ResponseEntity<Account> registerAccount(Account newAccount) {
        // The registration will be successful if the username is not
        // blank, the password is at least 4 characters long, and an Account with that
        // username does not already exist.

        if (newAccount.getUsername() != "" && newAccount.getPassword().length() > 4) {
            Account doesAccountExist = accountRepo.findByUsername(newAccount.getUsername());

            if (doesAccountExist != null) {
                return new ResponseEntity<Account>(HttpStatus.CONFLICT); // Duplicate username, response status should
                                                                         // be 409. (Conflict)
            } else {
                accountRepo.save(newAccount);
                return new ResponseEntity<Account>(newAccount, HttpStatus.OK); // The response status should be 200 OK
            }

        }

        return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST); // Not successful for some other reason, the status
                                                                    // should be 400. (Client
        // error)

    }

    /*
     * @Summary: Users should verify login
     * 
     * @Param: Account to login
     * 
     * @Return: Response status and message body
     */
    public ResponseEntity<Account> loginAccount(Account accountToLogin) {
        Account doesAccountExist = accountRepo.findByUsernameAndPassword(accountToLogin.getUsername(),
                accountToLogin.getPassword());
        if (doesAccountExist != null) {
            return new ResponseEntity<Account>(doesAccountExist, HttpStatus.OK); // If successful, response status
                                                                                 // should be 200 OK
        } else
            return new ResponseEntity<Account>(HttpStatus.UNAUTHORIZED); // Not successful, the response status is
                                                                         // 401 (Unauthorized)
    }

}
