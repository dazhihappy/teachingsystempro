package com.service;

import java.util.List;

public interface GradeService {
	//��ȡѧ���ɼ��б�
	public List getGradeList(Integer pfid , Integer page);
	//�洢ѧ���ɼ�
	public Integer saveStuGrade(String stid , Integer pfid);
}
