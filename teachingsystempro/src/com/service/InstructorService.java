package com.service;

import com.bean.AddCourseIns;

public interface InstructorService {
	//����Ƿ���instructor
	public boolean checkIns(String iid);
	//��ʦ�ڿ�
	public void insertTeaches(AddCourseIns courseIns);
}
