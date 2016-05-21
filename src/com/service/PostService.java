package com.service;

import java.util.List;

import com.bean.Posts;

public interface PostService {
	//��ȡ����
	public List getPosts(Integer cid ,Integer page ,Integer pageSize);
	//��ȡ��������
	public Long getPostsCount(Integer cid);	
	//��������
	public void createPost(Posts posts);
	//��ȡ���˻ش���
	public List getNoreplyPosts(Integer cid ,Integer page ,Integer pageSize);
	//��ȡ���˻ظ�����
	public Long getNoReplyCount(Integer cid);
	
}
