package com.service;

import com.bean.Instructor;

public interface InformationService {
	//�޸Ļ�����Ϣ
	public boolean changeBasicInfo(Instructor instructor);
	//�޸�����
	public boolean changePassInfo(String iid ,String ipassword , String newpassword);
	//�޸�ͷ��
	public boolean changePicInfo(String iid , String iurl);
	//��ȡ��Ϣ
	public Instructor getInfo(String iid);
}
