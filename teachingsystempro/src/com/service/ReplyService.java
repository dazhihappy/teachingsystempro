package com.service;

import java.util.List;

public interface ReplyService {
	//�ظ�
	public List reply(String iid , Integer pid , String content);
	//��ȡ�ظ���Ϣ
	public List getReplys(Integer pid);//����:����ID
}
