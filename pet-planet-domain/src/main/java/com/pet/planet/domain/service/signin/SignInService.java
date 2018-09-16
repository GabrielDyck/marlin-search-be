package com.pet.planet.domain.service.signin;

import com.pet.planet.api.signin.SignInRequestDTO;
import com.pet.planet.domain.dao.repository.sql.UserAccountDao;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.utils.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gabrieldyck on 18/03/17.
 */
@Service
public class SignInService {

    @Autowired
    private UserAccountDao userAccountDao;

    public void createUser(SignInRequestDTO request){
        UserAccount user= new UserAccount();
        user.setUsername(request.getUsername());
        user.setMail(request.getMail());
        user.setPhone(request.getPhone());
        user.setPassToken(request.getPassword().toUpperCase());
        user.setFullname(request.getFullname());
        user.setValidated('N');
        user.setAccessToken(AccessToken.generateAccessToken(user.getUsername()));
        userAccountDao.save(user);
    }

    public boolean validEmail(String email){
        return (userAccountDao.findByMail(email)==null);
    }

    public boolean validUser(String user){
        return (userAccountDao.findByUsername(user)==null);
    }

}