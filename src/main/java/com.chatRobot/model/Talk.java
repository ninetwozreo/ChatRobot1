package com.chatRobot.model;

import java.util.Date;
/**一次会话类**/

public class Talk {

    private long id;
    private long outId;
    private long inId;


    public long getOutId() {
        return outId;
    }

    public void setOutId(long outId) {
        this.outId = outId;
    }

    public long getInId() {
        return inId;
    }

    public void setInId(long inId) {
        this.inId = inId;
    }
}