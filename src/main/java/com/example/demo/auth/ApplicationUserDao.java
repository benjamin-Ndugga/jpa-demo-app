/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth;

import java.util.Optional;

/**
 *
 * @author benjie_en
 */
public interface ApplicationUserDao {
    
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
