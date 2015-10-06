package com.junjunguo.tsag.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
@WebService
public interface UserService {
    String register(
            @WebParam(name = "username")
            String username,
            @WebParam(name = "email")
            String email,
            @WebParam(name = "password")
            String password);

    String login(
            @WebParam(name = "email")
            String email,
            @WebParam(name = "password")
            String password);

    String checkLogin(
            @WebParam(name = "email")
            String email);
}
