package org.cccs.wadlgenerator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 4:34:46 PM
 */
public class Resource {
    public String path;
    public List<Method> methods;

    public Resource(String path) {
        this.path = path;
        this.methods = new ArrayList<Method>();
    }    
}
