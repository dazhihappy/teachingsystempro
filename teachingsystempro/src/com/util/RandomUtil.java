package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
	
	//��ȡ�����,��Χ:0~maxNum ,����:count
	public static List<Integer> getRandom(int count , int maxNum){
		
		List<Integer> list = new ArrayList<Integer>();
		if(count <= maxNum){
			Random random = new Random();
			while(list.size() < count){
				int i = random.nextInt(maxNum)+1;
				if(!list.contains(i)){
					list.add(i);
				}
			}
		}else{
			for(int i = 1 ; i <= maxNum ; i++){
				list.add(i);
			}
		}
		return list;
	}
}
