package com.service;

import java.util.List;

import com.bean.Course;

public interface CourseSevice {
	//��ȡ�γ��б�
	public List<Course> getCourses(String iid);
	//��ȡ�γ�����
	public List getCourseDetail(Integer cid);
	//��ӿγ�
	public Integer addCourse(Course course);
}
