package com.serviceImpl;

import java.util.List;

import com.bean.Posts;
import com.dao.PostsDAO;
import com.service.PostService;

public class PostServiceImpl implements PostService {
	private PostsDAO postsDAO;
	//��ȡposts����
	@Override
	public Long getPostsCount(Integer cid) {
		return postsDAO.getPostsCount(cid);
	}
	//��ȡÿҳpost
	@Override
	public List getPosts(Integer cid, Integer page, Integer pageSize) {
		return postsDAO.getPostsList(cid, page, pageSize);
	}
	
	//��ȡ���˻ظ�������
	@Override
	public List getNoreplyPosts(Integer cid, Integer page, Integer pageSize) {
		return postsDAO.getNoReplyList(cid, page, pageSize);
	}

	//��ȡ���˻ظ�post����
	@Override
	public Long getNoReplyCount(Integer cid) {
		return postsDAO.getNoReplyPosts(cid);
	}
	
	//��������
	@Override
	public void createPost(Posts posts) {
	
	}

	public PostsDAO getPostsDAO() {
		return postsDAO;
	}

	public void setPostsDAO(PostsDAO postsDAO) {
		this.postsDAO = postsDAO;
	}
}
