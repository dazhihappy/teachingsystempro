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
	//��ȡ�γ���ϵ
	public List getCourseSystem(Integer cid);
	//��ӿγ���ϵ
	public void editCourseSystem(List preCourse);
	//��ȡǰ���γ�
	public List getPreCourse(Integer cid);
	//��ȡ���пγ�
	public List getAllCourse();
}
