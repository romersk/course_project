package com.bsuir.config;

import com.bsuir.users.entity.AuthorityEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;

import java.util.List;

public class AuthorithyEvdokimovRD {

    public static String getAuthority(UserEvdokimovRD userObj) {
        List<AuthorityEvdokimovRD> auth = (List<AuthorityEvdokimovRD>) userObj.getAuthorities();
        return auth.get(0).getRoleCode();
    }
}
