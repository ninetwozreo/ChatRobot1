package com.chatRobot.model;

//import com.chatRobot.commons.po.BasePo;

import com.centit.fileserver.client.po.FileStoreInfo;

import java.nio.file.FileStore;
import java.util.Date;
import java.util.List;

/**created by shark 2018-9-11
 * 会话或返回内容类**/

public class OneContent {


    private long id;
    private long times;
    //语言相关
    private String words;
    //条目类型（0：问题；1：回答）
    private int charatered;
    //文件相关
    private String fileId;
    //
//    private List<FileStoreInfo> fileMsg;
    //类型（words：语言；file：文件）
    private String types;
    //查找分辨率
    private int ratio;
    //创建时间
    private Date createDate;

//    public List<FileStoreInfo> getFileMsg() {
//        return fileMsg;
//    }

//    public void setFileMsg(List<FileStoreInfo> fileMsg) {
//        this.fileMsg = fileMsg;
//    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getCharatered() {
        return charatered;
    }

    public void setCharatered(int charatered) {
        this.charatered = charatered;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}