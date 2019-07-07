//package com.chatRobot.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.centit.apprFlow.po.OptStuffInfo;
//import com.centit.apprFlow.service.OptStuffInfoService;
//import com.centit.fileserver.utils.FileServerConstant;
//import com.centit.fileserver.utils.FileStore;
//import com.centit.fileserver.utils.SystemTempFileUtils;
//import com.centit.fileserver.utils.UploadDownloadUtils;
//import com.centit.framework.common.JsonResultUtils;
//import com.centit.framework.common.ObjectException;
//import com.centit.framework.core.controller.BaseController;
//import com.centit.support.algorithm.NumberBaseOpt;
//import com.centit.support.file.FileIOOpt;
//import com.centit.support.file.FileMD5Maker;
//import com.centit.support.file.FileSystemOpt;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.tuple.ImmutablePair;
//import org.apache.commons.lang3.tuple.Pair;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/upload")
//
//public class UploadFileController  {
//
//    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
//
//    @Autowired
//    protected FileStore fileStore;
//    @Autowired
//    protected OptStuffInfoService optStuffInfoService;
//
//    /**
//     * 判断文件是否存在，如果文件已经存在可以实现秒传
//     *
//     * @param token    String
//     * @param size     size
//     * @param response HttpServletResponse
//     * @throws IOException IOException
//     */
//    @CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 86400,
//            allowedHeaders = "*", methods = RequestMethod.GET)
//    @RequestMapping(value = "/exists", method = RequestMethod.GET)
//    public void checkFileExists(String token, long size, HttpServletResponse response)
//            throws IOException {
//
//        JsonResultUtils.writeOriginalObject(fileStore.checkFile(token, size), response);
//    }
//
//    /**
//     * 获取文件 断点位置，前端根据断点位置续传
//     *
//     * @param token    String
//     * @param size     size
//     * @param request  HttpServletRequest
//     * @param response HttpServletResponse
//     * @throws IOException IOException
//     */
//    @CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 86400, methods = RequestMethod.GET)
//    @RequestMapping(value = "/range", method = {RequestMethod.GET})
//    public void checkFileRange(String token, long size,
//                               HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        //FileRangeInfo fr = new FileRangeInfo(token,size);
//        Pair<String, InputStream> fileInfo = fetchInputStreamFromRequest(request);
//
//        long tempFileSize;
//        // 如果文件已经存在则完成秒传，无需再传
//        if (fileStore.checkFile(token, size)) {//如果文件已经存在 系统实现秒传
//            //添加完成 后 相关的处理  类似与 uploadRange
//            completedStoreFile(fileStore, token, size, fileInfo.getLeft(), response);
//            return;
//        }
//        //检查临时目录中的文件大小，返回文件的其实点
//        //String tempFilePath = FileUploadUtils.getTempFilePath(token, size);
//        tempFileSize = SystemTempFileUtils.checkTempFileSize(
//                SystemTempFileUtils.getTempFilePath(token, size));
//
//        JsonResultUtils.writeOriginalJson(
//                UploadDownloadUtils.makeRangeUploadJson(tempFileSize).toJSONString(), response);
//    }
//
//
//    /*
//     * 保存文件
//     */
//
//    private void completedStoreFile(FileStore fs, String fileMd5, long size,
//                                    String fileName, HttpServletResponse response) {
//        try {
//
//            String fileId = fileMd5 + "_" + String.valueOf(size);
////                    + "." + FileType.getFileExtName(fileName);
////            String filePath = fs.getFileStoreUrl(fileMd5,size);
//            // 返回响应
//            JSONObject json = new JSONObject();
//            json.put("start", size);
//            json.put("name", fileName);
//            json.put("token", fileMd5);
//            json.put("success", true);
//            json.put("fileId", fileId);
//            json.put("fileName", fileName);
//            json.put("result", " 上传成功！");
//            json.put("src", "workflow/download/download/" + fileId + "?fileName=" + fileName);
//            json.put("deleteSrc", "workflow/upload/delete/" + fileId);
//
//
//            //            Map<String,String> json1 = new HashMap<>();
////            json1.put("src","workflow/download/download/"+fileId+"?fileName="+fileName);
////            json1.put("fileId", fileId);
////            json1.put("token", fileMd5);
////            json1.put("size", String.valueOf(size));
////            json1.put("name", fileName);
////            json.put(ResponseData.RES_CODE_FILED, 0);
////            json.put(ResponseData.RES_MSG_FILED, "上传成功");
////            json.put(ResponseData.RES_DATA_FILED, json1);
//            JsonResultUtils.writeSingleDataJson(json, response);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            JsonResultUtils.writeErrorMessageJson(
//                    FileServerConstant.ERROR_FILE_PRETREAT,
//                    "文件上传成功，但是在保存前：" + e.getMessage(), response);
//        }
//    }
//
//
//    private Pair<String, InputStream> fetchInputStreamFromRequest(HttpServletRequest request) throws IOException {
//        String fileName = request.getParameter("name");
//        if (StringUtils.isBlank(fileName))
//            fileName = request.getParameter("fileName");
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        if (!isMultipart)
//            return new ImmutablePair<>(fileName, request.getInputStream());
//
//        Map<String, MultipartFile> map;
//        if(request  instanceof DefaultMultipartHttpServletRequest){
//            map = ((MultipartHttpServletRequest)request).getFileMap();
//        }else {
//            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
//            map = multiRequest.getFileMap();
//        }
//
////        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//        InputStream fis = null;
//
//        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
//            CommonsMultipartFile cMultipartFile = (CommonsMultipartFile) entry.getValue();
//            FileItem fi = cMultipartFile.getFileItem();
//            if (!fi.isFormField()) {
//                fileName = fi.getName();
//                fis = fi.getInputStream();
//                if (fis != null)
//                    break;
//            }
//        }
//        return new ImmutablePair<>(fileName, fis);
//    }
//
//    /**
//     * 续传文件（range） 如果文件已经传输完成 对文件进行保存
//     *
//     * @param token    String
//     * @param size     size
//     * @param request  HttpServletRequest
//     * @param response HttpServletResponse
//     * @throws IOException IOException
//     */
//    @CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 86400, methods = RequestMethod.POST)
//    @RequestMapping(value = "/range", method = {RequestMethod.POST})
//    public void uploadRange(
//            String token, long size,
//            HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//
//        Pair<String, InputStream> fileInfo = fetchInputStreamFromRequest(request);
//        String tempFilePath = SystemTempFileUtils.getTempFilePath(token, size);
//
//        //FileStore fs = FileStoreFactory.createDefaultFileStore();
//        if (fileStore.checkFile(token, size)) {// 如果文件已经存在则完成秒传，无需再传。
//            completedStoreFile(fileStore, token, size, fileInfo.getLeft(), response);
//            return;
//        }
//
//        try {
//            long uploadSize = UploadDownloadUtils.uploadRange(tempFilePath, fileInfo.getRight(), token, size, request);
//            if (uploadSize == 0) {
//                //上传到临时区成功
//                fileStore.saveFile(tempFilePath, token, size);
//                completedStoreFile(fileStore, token, size, fileInfo.getLeft(), response);
//                FileSystemOpt.deleteFile(tempFilePath);
//                return;
//            } else if (uploadSize > 0) {
//
//                JsonResultUtils.writeOriginalJson(UploadDownloadUtils.
//                        makeRangeUploadJson(uploadSize).toJSONString(), response);
//            }
//
//        } catch (ObjectException e) {
//            logger.error(e.getMessage(), e);
//            JsonResultUtils.writeErrorMessageJson(e.getExceptionCode(),
//                    e.getMessage(), response);
//        }
//    }
//
//    /**
//     * 上传整个文件适用于IE8
//     *
//     * @param request  HttpServletRequest
//     * @param response HttpServletResponse
//     * @throws IOException IOException
//     */
//    @CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 86400, methods = RequestMethod.POST)
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public void uploadFile(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        request.setCharacterEncoding("utf8");
//        String tempFilePath = SystemTempFileUtils.getRandomTempFilePath();
//        Map<String, String[]> parameterMap ;
//
//        MultipartHttpServletRequest multiRequest= getMultiRequest(request);
//
//
//        try {
//            Pair<String, InputStream> fileInfo = fetchInputStreamFromRequest(multiRequest);
//            parameterMap=multiRequest.getParameterMap();
//            int fileSize = FileIOOpt.writeInputStreamToFile(fileInfo.getRight(), tempFilePath);
//            String fileMd5 = FileMD5Maker.makeFileMD5(new File(tempFilePath));
//            //FileStore fs = FileStoreFactory.createDefaultFileStore();
////            Map<String, String[]> parameterMap = new CommonsMultipartResolver(request.getSession().getServletContext()).resolveMultipart(request).getParameterMap();
//            fileStore.saveFile(tempFilePath);
//
//            String fileId = fileMd5 + "_" + String.valueOf(fileSize) ;
//
//            completedStoreFile(fileStore, fileMd5, fileSize, fileInfo.getLeft(), response);
//            FileSystemOpt.deleteFile(tempFilePath);
//            SaveOptStuInfo(fileId,fileMd5,fileInfo.getLeft(),parameterMap.get("nodeInstId")[0]);
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            JsonResultUtils.writeErrorMessageJson(e.getMessage(), response);
//        }
//    }
//
//    private MultipartHttpServletRequest getMultiRequest(HttpServletRequest request) {
//        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        return resolver.resolveMultipart(request);
////        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//    }
////插入一条文件信息到表中
//
//    private void SaveOptStuInfo(String fileId,String fileMd5,
//                                String fileName,String nodeInstId) {
//        OptStuffInfo optStuffInfo=new OptStuffInfo();
////        optStuffInfo.setFileType(fileType);
//        optStuffInfo.setFileId(fileMd5);
//        optStuffInfo.setFileName(fileName);
////        optStuffInfo.setDownloadUrl(fileId);
//        optStuffInfo.setNodeInstId(Long.parseLong(nodeInstId));
//
//        optStuffInfo.setStuffId(fileId);
//        optStuffInfo.setCreateTime(new Date());
//        optStuffInfoService.saveOptStuffInfo(optStuffInfo);
//    }
//
//
//    /**
//     * 根据文件的id物理删除文件(同时删除文件和数据库记录)
//     *
//     * @param fileId   文件ID
//     * @param response HttpServletResponse
//     */
//    @RequestMapping(value = "/delete/{fileId}")
//    public void delete(@PathVariable("fileId") String fileId, HttpServletResponse response) {
//        String fileMd5 = fileId.substring(0, 32);
//        OptStuffInfo optStuffInfo = new OptStuffInfo();
//        optStuffInfo.setStuffId(fileId);
//        optStuffInfoService.deleteObject(optStuffInfo);
//        int pos = fileId.indexOf('.');
//        //String extName = md5SizeExt.substring(pos);
//        long fileSize = pos < 0 ? NumberBaseOpt.parseLong(fileId.substring(33), 0l)
//                : NumberBaseOpt.parseLong(fileId.substring(33, pos), 0l);
//
//        String filePath = fileStore.getFileStoreUrl(fileMd5, fileSize);
//        try {
//            fileStore.deleteFile(filePath);
////            optStuffInfoService.deleteObjectByRecordId(0l,fileId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        }
//        JsonResultUtils.writeSuccessJson(response);
//    }
//}