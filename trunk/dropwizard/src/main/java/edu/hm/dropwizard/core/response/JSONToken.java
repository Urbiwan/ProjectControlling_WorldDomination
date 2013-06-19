package edu.hm.dropwizard.core.response;

/**
 * Created with IntelliJ IDEA.
 * User: qriz
 * Date: 19.06.13
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */
public class JSONToken {
    private int token;

    public JSONToken(int token) {
        this.token = token;
    }

    public JSONToken() {

    }

    public  int getToken() {
        return token;
    }
}
