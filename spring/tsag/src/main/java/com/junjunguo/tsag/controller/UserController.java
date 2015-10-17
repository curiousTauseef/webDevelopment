package com.junjunguo.tsag.controller;

import com.junjunguo.tsag.model.Tag;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;//Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    //-------------------Retrieve All tags--------------------------------------------------------

    @RequestMapping(value = "/tag/list/", method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> listAllTags() {
        log("retrieve all tags !");
        List<Tag> tags = userService.findAllTags();
        if (tags.isEmpty()) {
            return new ResponseEntity<List<Tag>>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }

    //-------------------Retrieve Single User------------------------------------------------------

    @RequestMapping(value = "/name/{name}/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByName(
            @PathVariable("name")
            String name) {
        log("Fetching User with name " + name);
        User user = userService.findByName(name);
        if (user == null) {
            log("User with name " + name + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/email/{email:.+}/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByEmail(
            @PathVariable("email")
            String email) {
        log("Fetching User with email " + email);
        User user = userService.findByEmail(email);
        if (user == null) {
            log("User with email " + email + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(
            @RequestBody
            User user, UriComponentsBuilder ucBuilder) {
        log("Creating User " + user.toString());
        if (userService.isUserExist(user.getEmail())) {
            log("A User with email " + user.getEmail() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userService.addUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/email/{email}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Create a Tag--------------------------------------------------------

        @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
        public ResponseEntity<Void> createUser(
                @RequestBody
                String tag, UriComponentsBuilder ucBuilder) {
            log("Creating tag " + tag);
            log("has tag ? " + userService.hasTag(tag));
            if (userService.hasTag(tag)) {
                log("tag " + tag + " already exist");
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
            }

            userService.addTag(tag);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/user/tag/{tag}").buildAndExpand(tag).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }

    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(
            @RequestBody
            User user) {
        if (userService.findByEmail(user.getEmail()) == null) {
            log("User with email " + user.getEmail() + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "{email:.+}/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(
            @PathVariable("email")
            String email) {
        log("Fetching & Deleting User with email " + email);

        User user = userService.findByEmail(email);
        if (user == null) {
            log("Unable to delete. User with email " + email + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserByEmail(email);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    public void log(String s) {
        System.out.print("----------" + this.getClass().getSimpleName() + " " + s);
    }
}
