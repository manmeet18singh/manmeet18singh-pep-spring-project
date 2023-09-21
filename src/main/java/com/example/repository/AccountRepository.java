package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

public interface AccountRepository extends JpaRepository <Account, Integer>{
    // Repos are where we do our queries... simillar to the DAO files in the previous project

    
    Account findByUsername(String username);

}
