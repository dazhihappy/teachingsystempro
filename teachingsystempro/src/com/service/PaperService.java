package com.service;

import java.util.List;

import com.bean.BlankId;
import com.bean.ChoiceId;
import com.bean.Paperform;
import com.bean.SubquesId;

public interface PaperService {
	//��ȡpaper
	public List getPapers(Integer cid ,Integer page ,Integer pageSize);
	//��ȡ�Ծ�����
	public Long getPapersCount(Integer cid);
	//�����Ծ�
	public void issuePaper(Integer pfid, Short pfstatus);
	//�鿴�Ծ�
	public List showPaperDetail(Integer pfid);
	//��ȡ���������������б�
	public List getStuAns(String stid , Integer pfid);
	//�з�
	public void changeScore(Integer pid , Short score);
	//�����Ծ�
	public List createPaper(Integer cid, Short pftype, Short startChapter ,Short endChapter,
			Integer choiceNum , Integer blankNum ,Integer subquesNum);
	//�洢paperform
	public Paperform savePaperForm(String name , Short pftype);
	//�洢paperdetail
	public void savePaperDetail(List<ChoiceId> choices , List<BlankId> blanks ,List<SubquesId> subques ,Paperform pf);
}
