package edu.hm.dropwizard.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 02.06.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class JSONRequest {
    @JsonProperty("Entries")
    private JSONChild[] entries;

    public JSONRequest() {

    }

    public JSONRequest(JSONChild[] entries) {
        this.entries = entries;
    }

    public  JSONChild[] getEntries(){
        return entries;
    }

}
