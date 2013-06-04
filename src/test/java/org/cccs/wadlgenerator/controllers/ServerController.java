package org.cccs.wadlgenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 8:40:45 PM
 */
@Controller
public class ServerController {

    @RequestMapping(value = "/servers", method = RequestMethod.GET)
    public void getServers() {
    }

    @RequestMapping(value = "/servers/{id}", method = RequestMethod.GET)
    public void getServer(@PathVariable("id") final String id) {
    }

    @RequestMapping(value = "/servers/{id}", method = RequestMethod.PUT)
    public void createServer(@PathVariable("id") final String id) {
    }

    @RequestMapping(value = "/servers/{id}", method = RequestMethod.POST)
    public void updateServer(@PathVariable("id") final String id) {
    }

    @RequestMapping(value = "/servers/{id}", method = RequestMethod.DELETE)
    public void deleteServer(@PathVariable("id") final String id) {
    }
}
