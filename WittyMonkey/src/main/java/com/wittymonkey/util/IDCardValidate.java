package com.wittymonkey.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wittymonkey.entity.Area;
import com.wittymonkey.service.IAreaService;
import com.wittymonkey.vo.IDCardInfo;

/**
 * 身份证校验和信息提取
 * 
 * @author neilw
 * 
 */
public class IDCardValidate {

	@Autowired
	private IAreaService areaService;
	//  静态初始化一个工具类，为了在Spring初始化以前
	private static IDCardValidate idCardValidate;
	private static final List<Integer> PARAM = new ArrayList<Integer>();
	private static final List<Integer> LAST_NUM = new ArrayList<Integer>();
	static {
		PARAM.add(7);
		PARAM.add(9);
		PARAM.add(10);
		PARAM.add(5);
		PARAM.add(8);
		PARAM.add(4);
		PARAM.add(2);
		PARAM.add(1);
		PARAM.add(6);
		PARAM.add(3);
		PARAM.add(7);
		PARAM.add(9);
		PARAM.add(10);
		PARAM.add(5);
		PARAM.add(8);
		PARAM.add(4);
		PARAM.add(2);
		LAST_NUM.add(1);
		LAST_NUM.add(0);
		// -1代表X
		LAST_NUM.add(-1);
		for (int i = 9; i >= 2; i--) {
			LAST_NUM.add(i);
		}
	}
	
	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
	/**
	 * 在初始化之前
	 */
	@PostConstruct
	public void init(){
		System.out.println("注入");
		idCardValidate = this;
		idCardValidate.areaService = this.areaService;
	}
	/**
	 * <h4>身份证正确性校验</h4> 校验规则：<br>
	 * 1. 将身份证号前17分别依次乘以下系数：<br>
	 * &nbsp;&nbsp;7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2<br>
	 * 2. 将17个结果相加<br>
	 * 3. 将相加结果除以11得余数<br>
	 * 4. 余数0 - 11分别应该对应以下最后一位号码<br>
	 * &nbsp;&nbsp;1,0,X,9,8,7,6,5,4,3,2
	 * 
	 * @PARAM id
	 * @return
	 */
	public static boolean validate(String id) {
		boolean result = false;
		int sum = 0;
		try {
			for (int i = 0; i < 17; i++) {
				int now = Integer.parseInt(id.substring(i, i + 1));
				sum += now * PARAM.get(i);
			}
			int mod = sum % 11;
			String last = id.substring(17,18);
			int lastNum;
			if (last.equals("X")||last.equals("x")){
				lastNum=-1;
			}else{
				lastNum=Integer.parseInt(last);
			}
			switch (mod){
			case 0:result=(lastNum == 1)?true:false;break;
			case 1:result=(lastNum == 0)?true:false;break;
			case 2:result=(lastNum == -1)?true:false;break;
			case 3:result=(lastNum == 9)?true:false;break;
			case 4:result=(lastNum == 8)?true:false;break;
			case 5:result=(lastNum == 7)?true:false;break;
			case 6:result=(lastNum == 6)?true:false;break;
			case 7:result=(lastNum == 5)?true:false;break;
			case 8:result=(lastNum == 4)?true:false;break;
			case 9:result=(lastNum == 3)?true:false;break;
			case 10:result=(lastNum == 2)?true:false;break;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return result;
	}
	
	public static IDCardInfo getIDCardInfo(String id){
		if (!validate(id))
			return null;
		IDCardInfo idCardInfo = new IDCardInfo();
		String code = id.substring(0,6);
		System.out.println(idCardValidate == null);
		Area area = idCardValidate.areaService.getAreaByCode(code);
		idCardInfo.setArea(area.getName());
		idCardInfo.setCity(area.getCity().getName());
		idCardInfo.setProvince(area.getCity().getProvince().getName());
		String date = id.substring(6,14);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			idCardInfo.setBirthday(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		int sex = Integer.parseInt(id.substring(17,18));
		idCardInfo.setSex((sex % 2 == 0)?"F":"M");
		return idCardInfo;
	}
}
