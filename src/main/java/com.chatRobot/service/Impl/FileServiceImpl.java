package com.chatRobot.service.Impl;

import com.centit.fileserver.client.po.FileStoreInfo;
import com.chatRobot.dao.IFileDao;
import com.chatRobot.dao.IUserDao;
import com.chatRobot.model.User;
import com.chatRobot.service.IFileService;
import com.chatRobot.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements IFileService {

    @Resource
    private IFileDao fileDao;


    @Override
    public List<FileStoreInfo> listAllFile() {
        return fileDao.listAllFile();
    }
}