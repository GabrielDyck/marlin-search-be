package com.pet.planet.domain.service.login;

import com.pet.planet.api.login.LoginResponseType;
import com.pet.planet.api.login.LoginRequest;
import com.pet.planet.domain.dao.repository.sql.UserAccountDao;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.utils.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gabrieldyck on 24/03/17.
 */
@Service
public class LoginService {

    @Autowired
    private UserAccountDao userAccountDao;


    public String login(LoginRequest request) {
        UserAccount user = userAccountDao.findByUsername(request.getUser());
        String accessToken = null;

        if (user == null) {
            return LoginResponseType.USER_NOT_EXIST.name();
        }

        if (!user.getPassToken().equalsIgnoreCase(request.getPassword())) {
            return LoginResponseType.WRONG_PASSWORD.name();
        }
        accessToken = (AccessToken.generateAccessToken(request.getUser()));
        user.setAccessToken(accessToken);
        userAccountDao.save(user);
        return accessToken;
    }

}
