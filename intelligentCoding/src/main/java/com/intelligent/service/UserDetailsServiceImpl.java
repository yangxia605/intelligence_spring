package com.intelligent.service;

import com.intelligent.dao.UsersDao;
import com.intelligent.model.Users;
import com.intelligent.type.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UsersDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userDao.findByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User Not Found with -> username : " + username);
        }
        return UserPrinciple.build(user);
    }
}
