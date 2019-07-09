package com.chatRobot.controller;

//import com.centit.fileserver.client.FileClient;
//import com.centit.fileserver.client.po.FileStoreInfo;

import com.alibaba.fastjson.JSONObject;
import com.centit.fileserver.client.FileClient;
import com.centit.fileserver.client.po.FileStoreInfo;
import com.centit.fileserver.utils.FileStore;
import com.centit.fileserver.utils.OsFileStore;
import com.centit.fileserver.utils.SystemTempFileUtils;
import com.centit.framework.common.JsonResultUtils;
import com.centit.support.file.FileIOOpt;
import com.centit.support.file.FileMD5Maker;
import com.centit.support.file.FileSystemOpt;
import com.chatRobot.model.LearningModel;
import com.chatRobot.model.OneContent;
import com.chatRobot.service.IRobotService;
import com.chatRobot.utils.HttpUtils;
import com.chatRobot.utils.PropertiesUtils;
import com.chatRobot.utils.TalkUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.InputStream;

@Controller
@RequestMapping("/jarvers")
public class RobotController {


    @Resource
    private IRobotService robotService;
    private FileStore fileStore;
    private List<OneContent> oneContents;
    private List<OneContent> questionList;
    private OneContent oneContent;
    private LearningModel learningModel;
    {
        if(fileStore==null){
            fileStore=new OsFileStore(PropertiesUtils.getThe("fileRoot"));
        }
    }
    @RequestMapping("/leaning")
    public void Learning(HttpServletRequest request, HttpServletResponse response, String listenContent, String answerContent) {
//        userService.learning(listenContent,answerContent);
        request.getParameter("data");

        OneContent listenContent0 = new OneContent();
        OneContent answerContent0 = new OneContent();
//        if(!StringUtils.isEmpty(listenContent)&&!StringUtils.isEmpty(answerContent)){
//            listenContent0.setWords(listenContent);
//            answerContent0.setWords(answerContent);
//            listenContent0.setCreateDate(new Date());
//            answerContent0.setCreateDate(new Date());
//        }else {
//            return ;
//        }

//        File file = TalkUtils.getFile(request);
        MultipartHttpServletRequest multipartHttpServletRequest = TalkUtils.getMultipartHttpServletRequest(request);
//        List<CommonsMultipartFile> multipartFile = TalkUtils.getMultipartFile(multipartHttpServletRequest);
        JSONObject multipartParamters = TalkUtils.getMultipartParamters(multipartHttpServletRequest);

        listenContent0.setWords(multipartParamters.get("listenContent").toString());
        answerContent0.setWords(multipartParamters.get("answerContent").toString());


//        if (multipartFile != null && multipartFile.size() == 1) {
//            File file = TalkUtils.CommonsMultipartFileToFile(PropertiesUtils.getThe("filePath"), multipartFile.get(0));
            answerContent0 = hasFile(answerContent0,
                    multipartHttpServletRequest);//滴滴滴，当前默认为回答
//        }
        learningModel = new LearningModel();
        learningModel.setListenContent(listenContent0);
        learningModel.setAnswerContent(answerContent0);

        robotService.learning(listenContent0, answerContent0);

    }

    @RequestMapping("/talk")
    @ResponseBody
    public Map<String, Object> Talking(HttpServletRequest request, HttpServletResponse response, String listenContent) throws IOException {
//        userService.learning(listenContent,oneContent);
        oneContents = robotService.Answer(listenContent);
        String url = "";
//        request.setAttribute("back", oneContents);
        Map<String, Object> result = new HashMap<String, Object>();
        //滴滴滴，考虑扩展成一次显示多个
        OneContent oneContent1 = TalkUtils.findTheBestOne(oneContents);
        if (oneContent1.getFileId() != null) {
            url = downloadFile(oneContent1.getFileId());
        }
        oneContent = oneContent1;
//        oneContents.remove(oneContent1);
        result.put("back", oneContent1.getWords());
        result.put("url", url);//若有文件则url不为空
//        result.put("all", oneContents);
//        renderJson(result);
//        response.setStatus(200);
//        response.setCharacterEncoding("GBK");
//        response.getWriter().write(a);

        return result;

    }

    private String downloadFile(String fileId) {
        return PropertiesUtils.getThe("fileserver") + "/service/download/pfile/" + fileId;
    }

    /**
     * 更换另一个最合适的答案
     */
    @RequestMapping("/changeAnother")
    @ResponseBody
    public Map<String, String> Changing(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        List<oneContent> oneContents =robotService.Answer(listenContent);
//        request.setAttribute("back",oneContents);
//        System.out.println("aa");
        Map<String, String> result = new HashMap<String, String>();
        OneContent oneContent1 = TalkUtils.findTheBestOne(oneContents);
        oneContent = oneContent1;
        oneContents.remove(oneContent1);
        result.put("back", oneContent1.getWords());
        return result;

    }

    /**
     * 确定答案后的操作
     */
    @RequestMapping("/isThis")
    @ResponseBody
    public Map<String, Object> MakeSure(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();
        if (robotService.makeSureTheAnswer(oneContent) == 1) {
            result.put("back", "OK");
        } else {
            result.put("back", "notOK");
        }
        return result;

    }

    /**
     * 类似问题的
     */
    @RequestMapping("/getSimilerQuestions")
    @ResponseBody
    public Map<String, Object> getSimilerQuestions(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String word = request.getParameter("questionWord");
//        int ratio = Integer.parseInt(request.getParameter("ratio"));
        oneContent = new OneContent();
        oneContent.setWords(word);
        oneContent.setRatio(100);
        if (questionList == null) {
            questionList = robotService.getQuestions();
        }
        List<OneContent> theQuestions = TalkUtils.findTheQuestions(oneContent, questionList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("list", theQuestions);
        return result;
//
    }

    /**
     * 向文件服务器上传文件
     */
    private OneContent hasFile(OneContent oneContent, MultipartHttpServletRequest multiRequest) {
        String tempFilePath = SystemTempFileUtils.getRandomTempFilePath();

        try {
            Pair<String, InputStream> fileInfo = HttpUtils.fetchInputStreamFromRequest(multiRequest);
//            FileUtils.forceMkdir(new File(tempFilePath));
            int fileSize = FileIOOpt.writeInputStreamToFile(fileInfo.getRight(), tempFilePath);
            String fileMd5 = FileMD5Maker.makeFileMD5(new File(tempFilePath));

            fileStore.saveFile(tempFilePath);

            String fileId = fileMd5 + "_" + String.valueOf(fileSize);

            oneContent.setFileId(fileId);
            FileSystemOpt.deleteFile(tempFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oneContent;
    }

    public void uploadFile(HttpServletRequest request)
            throws IOException {
        request.setCharacterEncoding("utf8");
        String tempFilePath = PropertiesUtils.getThe("filePath");
        Map<String, String[]> parameterMap;

        MultipartHttpServletRequest multiRequest = HttpUtils.getMultiRequest(request);


        try {
            Pair<String, InputStream> fileInfo = HttpUtils.fetchInputStreamFromRequest(multiRequest);
            parameterMap = multiRequest.getParameterMap();
            int fileSize = FileIOOpt.writeInputStreamToFile(fileInfo.getRight(), tempFilePath);
            String fileMd5 = FileMD5Maker.makeFileMD5(new File(tempFilePath));
            //FileStore fs = FileStoreFactory.createDefaultFileStore();
//            Map<String, String[]> parameterMap = new CommonsMultipartResolver(request.getSession().getServletContext()).resolveMultipart(request).getParameterMap();
            fileStore.saveFile(tempFilePath);

            String fileId = fileMd5 + "_" + String.valueOf(fileSize);

//            completedStoreFile(fileStore, fileMd5, fileSize, fileInfo.getLeft(), response);
            FileSystemOpt.deleteFile(tempFilePath);
//            SaveOptStuInfo(fileId,fileMd5,fileInfo.getLeft(),parameterMap.get("nodeInstId")[0]);

        } catch (Exception e) {
            e.printStackTrace();
//            logger.error(e.getMessage(), e);
//            JsonResultUtils.writeErrorMessageJson(e.getMessage(), response);
        }
    }
}