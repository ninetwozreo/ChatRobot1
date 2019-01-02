package com.chatRobot.controller;

//import com.centit.fileserver.client.FileClient;
//import com.centit.fileserver.client.po.FileStoreInfo;
import com.centit.fileserver.client.FileClient;
import com.centit.fileserver.client.po.FileStoreInfo;
import com.chatRobot.model.LearningModel;
import com.chatRobot.model.OneContent;
import com.chatRobot.service.IRobotService;
import com.chatRobot.utils.HttpUtils;
import com.chatRobot.utils.PropertiesUtils;
import com.chatRobot.utils.TalkUtils;
//import com.mchange.v2.util.PropertiesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.misc.FieldUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jarvers")
public class RobotController {

    @Resource
    private IRobotService robotService;
    private List<OneContent> oneContents;
    private List<OneContent> questionList;
    private OneContent oneContent;
    private LearningModel learningModel;

    @RequestMapping("/leaning")
    public void Learning(HttpServletRequest request, HttpServletResponse response, String listenContent, String answerContent) {
//        userService.learning(listenContent,answerContent);
        OneContent listenContent0 = new OneContent();
        OneContent answerContent0 = new OneContent();
        listenContent0.setWords(listenContent);
        answerContent0.setWords(answerContent);

        File file = TalkUtils.getFile(request);
        if (file != null) {
//            answerContent0 = hasFile(answerContent0, file);//滴滴滴，当前默认为回答
        }

        learningModel.setListenContent(listenContent0);
        learningModel.setAnswerContent(answerContent0);
        robotService.learning(listenContent0,  answerContent0);

    }

    @RequestMapping("/talk")
    @ResponseBody
    public Map<String, Object> Talking(HttpServletRequest request, HttpServletResponse response, String listenContent) throws IOException {
//        userService.learning(listenContent,oneContent);
        oneContents = robotService.Answer(listenContent);
        String url = "";
//        request.setAttribute("back", oneContents);
        Map<String, Object> result = new HashMap<>();
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
//    private OneContent hasFile(OneContent oneContent, File file) {
////        userService.learning(listenContent,answerContent);
////        OneContent oneContent = new OneContent();
//        FileStoreInfo fileStoreInfo1 = new FileStoreInfo();
//        fileStoreInfo1.setFileName(file.getName());
//        fileStoreInfo1.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
//        FileClient fileClient = HttpUtils.getFileClient();
//        FileStoreInfo fileStoreInfo;
//
//        try {
//            fileStoreInfo = fileClient.uploadFile(fileClient.getHttpClient(), fileStoreInfo1, file);
//            oneContent.setFileId(fileStoreInfo.getFileId());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return oneContent;
//    }
}