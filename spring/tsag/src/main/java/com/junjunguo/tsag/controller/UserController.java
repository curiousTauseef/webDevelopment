package com.junjunguo.tsag.controller;

import com.junjunguo.tsag.model.User;
import com.junjunguo.tsag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    UserService userService;//Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(
                    HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    //-------------------Retrieve Single User------------------------------------------------------

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByName(
            @PathVariable("name")
            String name) {
        System.out.println("Fetching User with name " + name);
        User user = userService.findByName(name);
        if (user == null) {
            System.out.println("User with name " + name + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByEmail(
            @PathVariable("email")
            String email) {
        System.out.println("Fetching User with email " + email);
        User user = userService.findByEmail(email);
        if (user == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(
            @RequestBody
            User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());

        if (userService.isUserExist(user.getEmail())) {
            System.out.println("A User with email " + user.getEmail() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        System.out.println("adding User " + user.getName());
        userService.addUser(user);
        System.out.println("adding User finished" + user.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/email/{email}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "{email:.+}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(
            @PathVariable("email")
            String email,
            @RequestBody
            User user) {
        System.out.println("Updating User " + email);

        User currentUser = userService.findByEmail(email);

        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.updateUser(user);

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "{email:.+}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(
            @PathVariable("email")
            String email) {
        System.out.println("Fetching & Deleting User with email " + email);

        User user = userService.findByEmail(email);
        if (user == null) {
            System.out.println("Unable to delete. User with email " + email + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserByEmail(email);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
