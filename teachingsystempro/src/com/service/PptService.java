package com.service;

import java.util.List;

import com.bean.Ppt;

public interface PptService {
	//��ȡPPT��Դ
	public List<Ppt> getPpts(Integer cid);
	//ɾ��ppt
	public void deletePpt(Ppt ppt);
	//�ı�ppt״̬
	public void updatePpt(Ppt ppt);
	//��ȡĳ��ĳ��ppt��
	public Integer getPptCount(Integer cid ,Short chapter);
	//�洢ppt
	public void savePpt(Ppt ppt);
}
