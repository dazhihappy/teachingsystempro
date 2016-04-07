package com.action;

import java.io.File;
import java.util.List;

import com.bean.Choice;
import com.bean.Course;
import com.bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ChoiceService;
import com.util.FileUploadYun;

public class ChoiceAction extends ActionSupport implements ModelDriven<Choice>{

	private Choice choice = new Choice();
	private ChoiceService choiceService;
	private Course course;
	private short chchapter;
	
	//ý����Ϣ����
	private String mediaType;
	
	@Override
	public Choice getModel() {
		return choice;
	}
	public void setChoiceService(ChoiceService choiceService) {
		this.choiceService = choiceService;
	}
	public void setChchapter(short chchapter) {
		this.chchapter = chchapter;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String add(){
		try {
			//���ڲ�����session���ֶ�����course
			ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "chchapter = " + chchapter);
			
			String str = FileUploadYun.dealUpload(mediaType, upload, uploadFileName);
			choice.setChmediaurl(str);
			
			choiceService.addChoice(choice,cid,chchapter);
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
	//		chapter = 2;
			/*if(chapter != null){
				ActionContext.getContext().getSession().put("chapter", chapter);
			}else{
				chapter = (Short) ActionContext.getContext().getSession().get("chapter");
			}*/
			
			//���γ̺Ŵ���session
	//		ActionContext.getContext().getSession().put("chapter", chapter);
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "chapter = " + chapter);
			System.out.println("currentPage" + currentPage);
			
			List list = choiceService.findAllChoice(cid,chapter,pageBean); 
			ActionContext.getContext().getValueStack().set("list", list);
			return "findAll_success";
		}
		
		//ɾ����Ŀ
		private Integer cid;
//		private short chchapter;     
		private Integer chid;
		
		public void setCid(Integer cid) {
			this.cid = cid;
		}

		public void setChid(Integer chid) {
			this.chid = chid;
		}
		
		public String delete(){
			System.out.println("cid = "+ cid + "chchapter = "+ chchapter + "chid = " + chid);
	//		ActionContext.getContext().getSession().put("chapter", );
			choiceService.deleteById(cid,chchapter,chid);
	//		return "delete_success";
			return this.NONE;
		}

		//��id����
		public String findById(){
			System.out.println("cid = "+ cid + "chchapter = "+ chchapter + "chid = " + chid);
			Object[] choice2 = choiceService.findById(cid,chchapter,chid);
			ActionContext.getContext().getValueStack().set("choice", choice2);
			return "findById_success";
		}
		
		//�޸�
		public String update(){
			try {
				System.out.println("cid = " + cid + "chchapter = " + chchapter
						+ "chid = " + chid);
				System.out.println(choice);
				String str = FileUploadYun.dealUpload( mediaType, upload,
						uploadFileName);
				
				if(str == null || "".equals(str)){
					System.out.println("û�ϴ�ý�壬��ԭ������");
					Object[] choice2 = choiceService.findById(cid, chchapter, cid);
					choice.setChmediaurl((String) choice2[8]);
				}else{
					System.out.println("�ϴ�ý���ˣ��滻ԭ����");
					choice.setChmediaurl(str);
				}
				
				choiceService.updateById(cid, chchapter, chid, choice);
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
