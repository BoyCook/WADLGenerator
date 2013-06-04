package org.cccs.wadlgenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 8:40:32 PM
 */
@Controller
public class UserController {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public void getServers() {
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public void getUser(@PathVariable("id") final String id) {
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public void createUser(@PathVariable("id") final String id) {
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public void updateUser(@PathVariable("id") final String id) {
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") final String id) {
    }
}
