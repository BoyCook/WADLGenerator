package org.cccs.wadlgenerator.domain;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 4:34:03 PM
 */
public class Method {
    public String httpMethod;
    public String clazz;
    public String method;

    public Method(String httpMethod, String clazz, String method) {
        this.httpMethod = httpMethod;
        this.clazz = clazz;
        this.method = method;
    }
}
