package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.Section;
import com.bean.SectionId;
import com.bean.StudentBasic;
import com.bean.Takes;
import com.bean.TakesId;
import com.dao.SectionDAO;
import com.dao.StudentBasicDAO;
import com.dao.TakesDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.StudentManageService;

public class StudentManageServiceImpl implements StudentManageService {
	private TakesDAO takesDAO;
	private StudentBasicDAO studentBasicDAO;
	private SectionDAO sectionDAO;
	
	@Override
	public List<String> daoStudent(Short seid , List<String> list) {
		List<String> fails = new ArrayList<String>();
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		Section section = sectionDAO.findById2(new SectionId(course.getCid(), seid));
		System.out.println(section.getSeendTime());
		for(String str : list){
			StudentBasic studentBasic = studentBasicDAO.findById(str);
			if(studentBasic != null){
				if(takesDAO.findById2(new TakesId(str, course.getCid(), seid))==null)
					takesDAO.save(new Takes(new TakesId(str, course.getCid(), seid), section, studentBasic));
			}else{
				fails.add(str);
			}
		}
		return fails;
	}
	
	//����ɾ��ѧ��
	@Override
	public void deleteTakes(TakesId takesIds) {
		takesDAO.delete(takesDAO.findById(takesIds));
	}
	
	//��ȡѧ���б�
	@Override
	public List getStudentMesssage(Integer cid, List<Short> seids,Integer page,Integer pageSize) {
		String seid ="";
		for (int i = 0; i < seids.size(); i++) {
			if(i!=(seids.size()-1)){
				seid += seids.get(i)+",";
			}else{
				seid += seids.get(i);
			}
		}
		return takesDAO.findStuList(cid, seid, page, pageSize);
	}

	//��ȡ��ҳ��
	@Override
	public Integer getStudentTotal(Integer cid, List<Short> seids) {
		String seid ="";
		for (int i = 0; i < seids.size(); i++) {
			if(i!=(seids.size()-1)){
				seid += seids.get(i)+",";
			}else{
				seid += seids.get(i);
			}
		}
		return takesDAO.getStudentTotal(cid, seid);
	}
	
	public TakesDAO getTakesDAO() {
		return takesDAO;
	}

	public void setTakesDAO(TakesDAO takesDAO) {
		this.takesDAO = takesDAO;
	}

	public StudentBasicDAO getStudentBasicDAO() {
		return studentBasicDAO;
	}

	public void setStudentBasicDAO(StudentBasicDAO studentBasicDAO) {
		this.studentBasicDAO = studentBasicDAO;
	}

	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}

	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
}
