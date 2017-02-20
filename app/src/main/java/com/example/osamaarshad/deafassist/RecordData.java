package com.example.osamaarshad.deafassist;

/**
 * Created by OsamaArshad on 19-Feb-17.
 */

public class RecordData {

    private String text;
    private String callid;

    public RecordData(String id, String text){this.callid=id;
    this.text=text;}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallid() {
        return callid;
    }

    public void setCallid(String callid) {
        this.callid = callid;
    }
}
