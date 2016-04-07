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
			//���ڲ�����session���ֶ�����course
			ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "bchapter = " + bchapter);
			System.out.println(blank.getBanswer());
			System.out.println(blank.getBexplain());
			System.out.println(blank.getBquestion());
			System.out.println(blank.getBscore());
			String str = FileUploadYun.dealUpload( mediaType, upload, uploadFileName);
			blank.setBmediaurl(str);
			
			blankService.addBlank(blank,cid,bchapter);
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
		//���ڲ�����session���ֶ�����course
		ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
	//	chapter = 1;
		Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
		System.out.println("cid = " + cid + "chapter = " + chapter);
		System.out.println("currentPage" + currentPage);
		
		List list = blankService.findAllBlank(cid,chapter,pageBean); 
		ActionContext.getContext().getValueStack().set("list", list);
		return "findAll_success";
	}
	
	//ɾ����Ŀ
	private Integer cid;
//	private short bchapter;     
	private Integer bid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	public String delete(){
		System.out.println("cid = "+ cid + "bchapter = "+ bchapter + "bid = " + bid);
		blankService.deleteById(cid,bchapter,bid);
		return this.NONE;
	}
	
	//��id����
	public String findById(){
		System.out.println("cid = "+ cid + "bchapter = "+ bchapter + "bid = " + bid);
		Object[] blank2 = blankService.findById(cid,bchapter,bid);
		ActionContext.getContext().getValueStack().set("blank", blank2);
		return "findById_success";
	}
	
	//�޸�
	public String update(){
		try {
			System.out.println("cid = " + cid + "bchapter = " + bchapter
					+ "bid = " + bid);
			System.out.println(blank);
			String str = FileUploadYun.dealUpload(mediaType, upload,uploadFileName);
			if(str == null || "".equals(str)){
				System.out.println("û�ϴ�ý�壬��ԭ������");
				Object[] blank2 = blankService.findById(cid, bchapter, bid);
				blank.setBmediaurl((String) blank2[7]);
			}else{
				System.out.println("�ϴ�ý���ˣ��滻ԭ����");
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

}