/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth;

import static com.example.demo.security.ApplicationUserRole.*;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author benjie_en
 */
@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter((t) -> t.getUsername().equals(username)).findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        ArrayList<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(ADMIN.getGrantedAuthorities(), passwordEncoder.encode("pass"), "admin", true, true, true, true),
                new ApplicationUser(USER.getGrantedAuthorities(), passwordEncoder.encode("pass"), "ben", true, true, true, true)
        );

        return applicationUsers;
    }

}
