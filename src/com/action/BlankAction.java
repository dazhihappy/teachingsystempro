package com.action;

import java.io.File;
import java.util.List;

import com.bean.Blank;
import com.bean.Course;
import com.bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.BlankService;
import com.util.FileUploadYun;

public class BlankAction extends ActionSupport implements ModelDriven<Blank>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5767591966886731672L;
	private Blank blank = new Blank();
	private Course course;
	private BlankService blankService;
	private short bchapter;

	//ý����Ϣ����
	private String mediaType;
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public void setBchapter(short bchapter) {
		this.bchapter = bchapter;
	}

	public void setBlankService(BlankService blankService) {
		this.blankService = blankService;
	}

	@Override
	public Blank getModel() {
		return blank;
	}

	//�����Ŀ
	public String add(){
		try {
			Course course = (Course)ActionContext.getContext().getSession().get("course");
			String str = FileUploadYun.dealUpload( mediaType, upload, uploadFileName);
			blank.setBmediaurl(str);			
			blankService.addBlank(blank,course.getCid(),bchapter);
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
		List list = blankService.findAllBlank(course.getCid(),chapter,pageBean); 
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getSession().put("selectedChapter", chapter);
		return "findAll_success";
	}
	
	//ɾ����Ŀ
	private Integer cid;     
	private Integer bid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	public String delete(){
		blankService.deleteById(cid,bchapter,bid);
		return this.NONE;
	}
	
	//��id����
	public String findById(){
		Object[] blank2 = blankService.findById(cid,bchapter,bid);
		ActionContext.getContext().getValueStack().set("blank", blank2);
		return "findById_success";
	}
	
	//�޸�
	public String update(){
		try {
			String str = FileUploadYun.dealUpload(mediaType, upload,uploadFileName);
			if(str == null || "".equals(str)){
				Object[] blank2 = blankService.findById(cid, bchapter, bid);
				blank.setBmediaurl((String) blank2[7]);
			}else{
				blank.setBmediaurl(str);
			}			
			blankService.updateById(cid, bchapter, bid, blank);
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