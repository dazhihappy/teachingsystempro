package com.service;

import java.util.List;

import com.bean.Media;

public interface VideoService {
	//��ȡ��Ƶ��Դ
	public List<Media> getVideos(Integer cid);
	//ɾ����Ƶ
	public void deleteVideo(Media media);
	//�ı���Ƶ״̬
	public void updateVideo(Media media);
	//�洢Media
	public void saveVideo(Media media);
	//��ȡĳ��ĳ�ڵ���Ƶ����
	public Integer getVideoCount(Integer cid ,Short chapter);
}
