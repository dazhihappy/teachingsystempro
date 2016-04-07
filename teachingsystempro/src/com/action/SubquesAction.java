package com.action;

import java.io.File;
import java.util.List;

import com.bean.Course;
import com.bean.PageBean;
import com.bean.Subques;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.SubquesService;
import com.util.FileUploadYun;

public class SubquesAction extends ActionSupport implements ModelDriven<Subques>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7423731253269980441L;
	private Subques subques = new Subques();
	private Course course;
	private SubquesService subquesService;
	private short suchapter;
	
	//ý����Ϣ����
	private String mediaType;
	@Override
	public Subques getModel() {
		return subques;
	}
	public void setSubques(Subques subques) {
		this.subques = subques;
	}
	public void setSubquesService(SubquesService subquesService) {
		this.subquesService = subquesService;
	}
	public void setSuchapter(short suchapter) {
		this.suchapter = suchapter;
	}
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String add(){
		try {
			Course course = (Course)ActionContext.getContext().getSession().get("course");			
			String str = FileUploadYun.dealUpload( mediaType, upload, uploadFileName);
			subques.setSumediaurl(str);			
			subquesService.addSubques(subques,course.getCid(),suchapter);
			this.addActionMessage("��ӳɹ�");
			return "add_success";			
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("���ʧ��");
			return "add_input";
		}
	}
				
	//�г�ȫ����Ŀ
	private Short chapter;
	public void setChapter(Short chapter) {
		this.chapter = chapter;
	}
	//���ڷ�ҳ�ĵ�ǰҳ��
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String list(){
		PageBean pageBean = new PageBean();
		pageBean.setPagesize(3);
		pageBean.setCurrentPage(currentPage);
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		List list = subquesService.findAllChoice(course.getCid(),chapter,pageBean); 
		ActionContext.getContext().getValueStack().set("list", list);
		return "findAll_success";
	}
		
		
	//ɾ����Ŀ
	private Integer cid;     
	private Integer suid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setSuid(Integer suid) {
		this.suid = suid;
	}
	
	public String delete(){
		subquesService.deleteById(cid,suchapter,suid);
		return this.NONE;
	}
	
	//��id����
	public String findById(){
		Object[] subques2 = subquesService.findById(cid,suchapter,suid);
		ActionContext.getContext().getValueStack().set("subques", subques2);
		return "findById_success";
	}
	
	//�޸�
	public String update(){
		try {
			String str = FileUploadYun.dealUpload(mediaType, upload,uploadFileName);			
			if(str == null || "".equals(str)){
				Object[] choice2 = subquesService.findById(cid, suchapter, suid);
				subques.setSumediaurl((String) choice2[7]);
			}else{
				subques.setSumediaurl(str);
			}			
			subquesService.updateById(cid, suchapter, suid, subques);
			this.addActionMessage("�޸ĳɹ�");
			return "update_success";
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("�޸�ʧ��");
			return "update_input";
		}
	}
	
	// �ϴ�����
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
