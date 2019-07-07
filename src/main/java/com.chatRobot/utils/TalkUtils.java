package com.chatRobot.utils;

import com.chatRobot.model.OneContent;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//
public class TalkUtils {
    /**
     * 找出最合适的回答语句
     */
    public static OneContent findTheBestOne(List<OneContent> oneContents) {
        OneContent oneContent1 = new OneContent();
        oneContent1.setTimes(-1);
        for (OneContent oneContent : oneContents) {//滴滴滴，考虑查找次数相等的情况
            oneContent1 = oneContent.getTimes() >= oneContent1.getTimes() ? oneContent : oneContent1;
        }
        return oneContent1;
    }

    /**
     * 找出相似的问题
     */
    public static List<OneContent> findTheQuestions(OneContent oneContent, List<OneContent> allList) {
        String contentWord = oneContent.getWords();
        List<String> contentWords = new ArrayList<String>();
        List<OneContent> backList = new ArrayList<OneContent>();
        //输入字数大于分辨率
        if (contentWord.length() > oneContent.getRatio()) {
            for (int i = 0; i < contentWord.length() - oneContent.getRatio(); i++) {
                contentWord = contentWord.substring(i, oneContent.getRatio());
                contentWords.add(contentWord);
            }
        } else {
            contentWords.add(contentWord);
        }
        Iterator<OneContent> iterator = allList.iterator();
        while (iterator.hasNext()) {
            OneContent content = iterator.next();
            Iterator<String> iter = contentWords.iterator();
            while (iter.hasNext()) {
                String iterContentWords = iter.next();
                if (content.getWords().contains(iterContentWords)) {
                    backList.add(content);
                }
            }
        }
        //大于的情况
//        else {
//
//        }
//        List<OneContent> list = getResoluTionRatio(oneContent.getRatio(), allList);
        return backList;
    }

    //将字节数组转化为文件
    public static File ByteToFile(byte[] bytes, String outputfile) {
        File ret = null;
        BufferedOutputStream stream = null;
        try {
            ret = new File(outputfile);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }


    //获取前台文件
    public static File getFile(HttpServletRequest request) {
        //获取前台上传的文件
        CommonsMultipartFile items = fileUploadOne(request);
        //设置路径
        String originalFilename = items.getOriginalFilename();

        if (StringUtils.isEmpty(originalFilename)){
            return null;
        }
        String path = request.getSession().getServletContext().getRealPath("")
                + File.separator + "ofd" + File.separator;//+usercode+File.separator
        File file = new File(path + originalFilename);
        //转换成file，生成虚拟文件，成功之后，删除文件放在finally中
        try {
            FileUtils.copyInputStreamToFile(items.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public static JsonObject getMultipartParamters(MultipartHttpServletRequest multiRequest) {
        multiRequest.getParameter("");
        return null;
    }
    public static List<CommonsMultipartFile> getMultipartFile(MultipartHttpServletRequest multiRequest) {
        Map<String, MultipartFile> map = multiRequest.getFileMap();
        List<CommonsMultipartFile> listFile=new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
            CommonsMultipartFile cMultipartFile = (CommonsMultipartFile) entry.getValue();
            listFile.add(cMultipartFile);
        }
        return listFile;
    }
    public static MultipartHttpServletRequest getMultipartHttpServletRequest(HttpServletRequest request) {
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        return resolver.resolveMultipart(request);
    }

    public static CommonsMultipartFile fileUploadOne(HttpServletRequest request) {
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
        Map<String, MultipartFile> map = multiRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
            CommonsMultipartFile cMultipartFile = (CommonsMultipartFile) entry.getValue();
            return cMultipartFile;
        }
        return null;
    }


    //base64转图片，base64和路径
    public static boolean Base64ToImg(String base64, String imgFilePath) {
        if (base64 == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream outputStream = new FileOutputStream(imgFilePath);
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //图片转base64  路径
    public static String imgToBase64(String imgFilePath) {
        InputStream inputStream = null;
        byte[] data = null;

        try {
            inputStream = new FileInputStream(imgFilePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        if (data != null) {
//        return "data:image/jpeg;base64,"+encoder.encode(data);
            return encoder.encode(data);
        }
        return null;
    }

//    /**
//     * 分辨率问题
//     */
//    public static List<OneContent> getResoluTionRatio(int ratio, List<OneContent> list) {
//
//        return null;
//    }
//
//    private static Gson gson = new Gson();
//
//
//    /**
//     * @param src :将要被转化的对象
//     * @return :转化后的JSON串
//     * @MethodName : toJson
//     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
//     */
//    public static String toJson(Object src) {
//        if (src == null) {
//            return gson.toJson(JsonNull.INSTANCE);
//        }
//        return gson.toJson(src);
//    }
}