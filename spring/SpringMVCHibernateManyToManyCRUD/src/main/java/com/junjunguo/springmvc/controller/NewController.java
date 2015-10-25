package com.junjunguo.springmvc.controller;

import com.junjunguo.springmvc.model.User;
import com.junjunguo.springmvc.service.UserProfileService;
import com.junjunguo.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 23/10/15.
 */

@RestController
@RequestMapping(value = "/new",
                headers = "Accept=*/*",
                produces = "application/json")
public class NewController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;


    @Autowired
    MessageSource messageSource;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/list"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUsers() {
        System.out.println("new/ list all users -----");
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(
                    HttpStatus.NOT_FOUND);
        }
        System.out.println("users: " + users);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    //    @RequestMapping(value = {"/list"},
    //                    method = RequestMethod.GET)
    //    public String listUsers() {
    //        System.out.println("new/ list all users -----");
    //        List<User> users = userService.findAllUsers();
    //        //        if (users.isEmpty()) {
    //        //            return new ResponseEntity<List<User>>(
    //        //                    HttpStatus.NOT_FOUND);
    //        //        }
    //        System.out.println("users: " + users);
    //        return users.toString();
    //    }


    @RequestMapping(value = "/id/{id}/",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserByName(
            @PathVariable("id")
            String id) {
        User user = userService.findBySSO(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            //            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        System.out.println("User with id " + id + " = " + user);
        return user.toString();
    }


    @RequestMapping(value = "/sso/{sso}/",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByEmail(
            @PathVariable("sso")
            String sso) {
        System.out.println("Fetching User with sso  " + sso);
        User user = userService.findBySSO(sso);
        if (user == null) {
            System.out.println("User with sso " + sso + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/create",
                    method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(
            @RequestBody
            User user, UriComponentsBuilder ucBuilder) {
        System.out.println("####1# create user -- user controller " + user);
        if (userService.findBySSO(user.getSsoId()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        System.out.println("####2# create user -- user controller " + user);
        userService.saveUser(user);
        System.out.println("####3# create user -- user controller " + user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/id/{id}").buildAndExpand(user.getSsoId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * This method will be called on form submission, handling POST request for updating user in database. It also
     * validates the user input
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"},
                    method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(
            @Valid
            User user, BindingResult result,
            ModelMap model,
            @PathVariable
            String ssoId) {

        if (result.hasErrors()) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        userService.updateUser(user);

        model.addAttribute("success",
                "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //
    //    /**
    //     * This method will delete an user by it's SSOID value.
    //     */
    //    @RequestMapping(value = {"/delete-user-{ssoId}"},
    //                    method = RequestMethod.GET)
    //    public String deleteUser(
    //            @PathVariable
    //            String ssoId) {
    //        userService.deleteUserBySSO(ssoId);
    //        return "redirect:/app/list";
    //    }


    //    /**
    //     * This method will provide UserProfile list to views
    //     */
    //    @ModelAttribute("roles")
    //    public List<UserProfile> initializeProfiles() {
    //        System.out.println("================ " + userProfileService.findAll());
    //        return userProfileService.findAll();
    //    }

}
