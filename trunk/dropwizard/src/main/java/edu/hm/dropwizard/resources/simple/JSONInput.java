package edu.hm.dropwizard.resources.simple;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 17.06.13
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */
public class JSONInput {
    @JsonProperty("input")
    private int input;

    public JSONInput() {

    }

    public  JSONInput(int input) {
        this.input = input;
    }

    public int getInput() {
        return input;
    }
}
