package com.chatRobot.service;


import com.centit.fileserver.client.po.FileStoreInfo;

import java.util.List;

public interface IFileService {


    List<FileStoreInfo> listAllFile();
}