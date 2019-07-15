package com.chatRobot.dao;


import com.centit.fileserver.client.po.FileStoreInfo;

import java.util.List;

public interface IFileDao {

    List<FileStoreInfo> listAllFile();
}