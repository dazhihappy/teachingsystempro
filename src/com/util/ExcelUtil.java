package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	/**
	 * POI����excel����
	 */
	public static void main(String[] args) {
		String filepath = "H:/2014-12.xls";
		File file = new File(filepath);
		try {
			ArrayList<String> list = ExcelUtil.getDatas(file);
			for(String l :list){
				System.out.println(l);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//��ȡѧ��
	public static ArrayList<String> getDatas(File file) throws IOException{		
		HSSFWorkbook workbook =new HSSFWorkbook(new FileInputStream(file));//��ȡexcel	
		HSSFSheet sheet = workbook.getSheetAt(0);//��ȡ������sheet
		ArrayList<String> list = new ArrayList<String>(); //�洢����
		HSSFRow row = null;//��	
		HSSFCell cell = null;//��Ԫ��
		for(int i = 9; i < sheet.getLastRowNum(); i++){
			row = sheet.getRow(i);//��ȡ��i��
			cell = row.getCell(1);//��ȡ�ڶ���:ѧ��
			if(((int)cell.getNumericCellValue())!=0)
				list.add((int)cell.getNumericCellValue()+"");
		}		
		return list;
	}
}
