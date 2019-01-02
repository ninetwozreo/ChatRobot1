package com.chatRobot.model;

/**一次查询**/

public class LearningModel {

    private long id;
    private long outId;
    private long inId;
    private OneContent listenContent;
    private OneContent answerContent;

    public OneContent getListenContent() {
        return listenContent;
    }

    public void setListenContent(OneContent listenContent) {
        this.listenContent = listenContent;
    }

    public OneContent getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(OneContent answerContent) {
        this.answerContent = answerContent;
    }

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