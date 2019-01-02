package com.chatRobot.utils;

import com.centit.fileserver.client.DefaultFileClient;
import com.centit.framework.appclient.AppSession;

//
public class HttpUtils {
    /**
     *
     */
    public static DefaultFileClient getFileClient(){
        DefaultFileClient fileClient;
        AppSession appSession = new AppSession(PropertiesUtils.getThe("filesever"));//配置文件文件服务器路径
        fileClient = new DefaultFileClient();
        fileClient.setAppSession(appSession);
        fileClient.setFileServerExportUrl(PropertiesUtils.getThe("filesever"));
        return fileClient;
    }
}