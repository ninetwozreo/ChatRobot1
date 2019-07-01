package com.chatRobot.utils;

import com.centit.fileserver.client.DefaultFileClient;
import com.centit.framework.appclient.AppSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

//
public class HttpUtils {
    /**
     *
     */
    public static DefaultFileClient getFileClient(){
        DefaultFileClient fileClient;
        AppSession appSession = new AppSession(PropertiesUtils.getThe("fileserver"));//配置文件文件服务器路径
        fileClient = new DefaultFileClient();
        fileClient.setAppSession(appSession);
        fileClient.setFileServerExportUrl(PropertiesUtils.getThe("fileserver"));
        return fileClient;
    }
    public static MultipartHttpServletRequest getMultiRequest(HttpServletRequest request) {
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        return resolver.resolveMultipart(request);
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
    }
    public static Pair<String, InputStream> fetchInputStreamFromRequest(HttpServletRequest request) throws IOException {
        String fileName = request.getParameter("name");
        if (StringUtils.isBlank(fileName))
            fileName = request.getParameter("fileName");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart)
            return new ImmutablePair<>(fileName, request.getInputStream());

        Map<String, MultipartFile> map;
        if(request  instanceof DefaultMultipartHttpServletRequest){
            map = ((MultipartHttpServletRequest)request).getFileMap();
        }else {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            map = multiRequest.getFileMap();
        }

        InputStream fis = null;

        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
            CommonsMultipartFile cMultipartFile = (CommonsMultipartFile) entry.getValue();
            FileItem fi = cMultipartFile.getFileItem();
            if (!fi.isFormField()) {
                fileName = fi.getName();
                fis = fi.getInputStream();
                if (fis != null)
                    break;
            }
        }

        return new ImmutablePair<>(fileName, fis);
    }

}