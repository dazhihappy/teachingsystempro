package com.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUpload {
	private static String savePath="uploadfile"; //�����ļ��ϴ���Ŀ¼Ŀ¼
	private static String PICTURE ="/picture"; //����ͼƬĿ¼
	private static String AUDIO = "/audio"; //������ƵĿ¼
	private static String VIDEO = "/video"; //������ƵĿ¼
	private static String PPT = "/ppt"; //����pptĿ¼
	
	//�ϴ���Ƶ
	public static String uploadVideo(File file , String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, VIDEO, newName);
		return savePath+VIDEO+"/"+newName;
	}
	//�ϴ���Ƶ
	public static String uploadAudio(File file , String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, AUDIO, newName);
		return savePath+AUDIO+"/"+newName;
	}
	//�ϴ�ͼƬ
	public static String uploadPicture(File file , String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, PICTURE, newName);
		return savePath + PICTURE+"/"+newName;
	}
	
	//�ϴ�PPT
	public static String uploadPpt(File file ,String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, PPT, newName);
		return savePath + PPT+"/"+newName;
	}
	
	public static String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	
	//��ȡ����ļ���
	public static String getRandomFileName(String fileName){
		UUID uuid = UUID.randomUUID();
		return uuid.toString()+fileName.substring(fileName.lastIndexOf("."));
	}
	
	//�洢�ļ�
	public static void saveFile(File file , String path ,String fileName){
		if(file!= null){
			File dir = new File(getSavePath()+path);
			if(!dir.exists()){
				dir.mkdir();
			}
			File descFile = new File(getSavePath()+path+"/"+fileName);
			try {
				FileUtils.copyFile(file, descFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String dealUpload(String mediaType,File upload,String uploadFileName) throws Exception{
		String mediaUrl = "";
		if(upload != null && uploadFileName != null){
			if("photo".equals(mediaType)){
				mediaUrl = uploadPicture(upload, uploadFileName);
			}else if("music".equals(mediaType)){
				mediaUrl = uploadAudio(upload, uploadFileName);
			}else if("vidio".equals(mediaType)){
				mediaUrl = uploadVideo(upload, uploadFileName);
			}else{
				throw new Exception("�ϴ�����");
			}
		}
		return mediaUrl;
	}	
}
