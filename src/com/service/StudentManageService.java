package com.service;

import java.util.List;

import com.bean.TakesId;

public interface StudentManageService {
	//����ѧ��
	public List<String> daoStudent(Short seid , List<String> list);
	//����ɾ��ѧ��ѡ��
	public void deleteTakes(TakesId takesId);
	//��ȡѧ����Ϣ
	public List getStudentMesssage(Integer cid, List<Short> seids,Integer page,Integer pageSize);//ʹ��cid��seid��ѯ
	//��ȡ����
	public Integer getStudentTotal(Integer cid, List<Short> seids);
}
