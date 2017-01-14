package com.wittymonkey.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

/**
 * 关于反射的工具类
 * @author 莉
 * @date 2017年1月1日下午7:33:51
 */
public class Reflections {

	private static Logger logger = LoggerFactory.getLogger(Reflections.class);
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	public static Class getClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}
	
	
	/*public static Map<String,Object> convertFieldKeyValue(Class<?> clazz){
		Map<String,Object> result=new HashMap<String,Object>();
		Field[] fields = clazz.getDeclaredFields();
		int size=fields.length;
		for(int i=0;i<size;i++){
			Field field=fields[i];
			field.setAccessible(true);
			 
			String mod = Modifier.toString(fields[i].getModifiers());
			 //静态方法不获取值
			  if (mod.indexOf("static") == -1){
				  
				  String className = fields[i].getType().getSimpleName();
				  
				  if(className.equalsIgnoreCase("get")){
					  
				  }
				  
			  }
			  
		}
		 return null;
	}*/
}
