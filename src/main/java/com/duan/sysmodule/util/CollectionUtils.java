package com.duan.sysmodule.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
//import java.util.Map.Entry;
import java.util.Set;
import java.beans.BeanInfo;
//import java.beans.IntrospectionException;
//import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 处理集合的工具类
 * @author Savvy
 */
public class CollectionUtils{
	
    /**
     * 将一个BO的List 转换成一个Map<Object,BO>返回，其中Map的key是BO的某个属性的值，Map的value是BO
     * @param beanList      List<BO>，其中BO是个bean
     * @param propertyName  propertyName 指定需要将BO的哪个属性做为Map的key
     * @param keyC          bean中propertyName属性值的类型
     * @param valC          bean的具体类型
     * @return
     */
    public static <K,V> Map<K,V> buildBeanListToMap(List<V> beanList,String propertyName,Class<K> keyC){
    	if(null == beanList || beanList.isEmpty() || propertyName == null || "".equals(propertyName.trim())){
    		return new HashMap<K,V>();
    	}else{
    		try {
                Map<K,V> resultMap = new HashMap<K,V>();
                V bean = null;
                PropertyDescriptor pd = null;
                Iterator<V> it = beanList.iterator();
                while (it.hasNext()) {
                    bean = it.next();
                    if (bean != null) {
                        if (pd == null){
                            pd = new PropertyDescriptor(propertyName, bean.getClass());
                        }
                        resultMap.put((K)pd.getReadMethod().invoke(bean), bean);
                    }
                }
                return resultMap;
            } catch (Exception e) {
            	System.err.println("buildBeanListToMap error,error info：" + e.getMessage());
                return new HashMap<K,V>();
            }
    	}
    }
    
    /**
     * 将一个List<Map>转换为一个新的Map，新Map的key和value分别由keyColum和valueColum指定。
     * forexample: maplist= map1:key=id,value=1;key=name,value=张三
     * map2:key=id,value=2;key=name,value=李四 keyColum=id valueColum=name
     * 返回：Map=key=1,value=张三;key=2,value=李四
     */
    public static Map buildMapListToMap(List<Map> maplist, String keyColumn,String valueColumn) {
        if (maplist == null || maplist.size() == 0 || keyColumn == null
                || keyColumn.equals("") || valueColumn == null
                || valueColumn.equals(""))
            return new HashMap();
        else {
            Map result = new HashMap();
            for (Map temp : maplist) {
                if (temp != null) {
                    String key = String.valueOf(temp.get(keyColumn));
                    if (key != null) {
                        String value = String.valueOf(temp.get(valueColumn));
                        result.put(key, value);
                    }
                }
            }
            return result;
        }
    }
    
    /**
     * 将一个List<Map>转换为一个新的Map<Object, Map>，新Map的key是原List<Map>中每个Map的keyColum字段值
     * forexample: maplist= map1:key=id,value=1;key=name,value=张三
     * map2:key=id,value=2;key=name,value=李四 keyColum=id 返回：Map:{ key=1
     * value=map{ key=id,value=1; key=name,value=张三 } key=2 value=map{
     * key=id,value=2; key=name,value=李四 }
     */
    public static Map buildMapListToMap(List maplist, String keyColumn) {
        if (maplist == null || maplist.size() == 0 || keyColumn == null || keyColumn.equals(""))
            return new HashMap();
        else {
            try {
                Map resultMap = new HashMap();
                for (Iterator it = maplist.iterator(); it.hasNext();) {
                    Map map = (Map) it.next();
                    if (map != null) {
                        Object key = map.get(keyColumn);
                        if (key != null)
                            resultMap.put(key, map);
                    }
                }
                return resultMap;
            } catch (Exception e) {
                return new HashMap();
            }
        }
    }
    
   /**
    * List转换为Set
    * @param list
    * @return
    */
   public static <T> Set<T> buildListToSet(List<T> list) {
	   if(null == list || list.isEmpty()){
		   return new HashSet<T>();
	   }else{
		   Set<T> resultSet = new HashSet<T>();
		   if (list != null && list.size() > 0) {
			   for (Iterator<T> it = list.iterator(); it.hasNext();) {
				   T val = it.next();
				   if (val != null){
					   resultSet.add(val);
				   }
			   }
		   }
		   return resultSet;
	   }
    }
    
   /**
    * Set转换为List
    * @param set
    * @return
    */
   public static <T> List<T> buildSetToList(Set<T> set) {
       if (set == null)
           return new ArrayList<T>();
       else {
           List<T> resultList = new ArrayList<T>();
           for (Iterator<T> it = set.iterator(); it.hasNext();) {
               resultList.add(it.next());
           }
           return resultList;
       }
   }
   
	/**
	 * 数据转换为Set
	 * @param array
	 * @return
	 */
	public static <T> Set<T> buildArrayToSet(T[] array) {
		if (null == array || array.length == 0) {
			return new HashSet<T>();
		} else {
			Set<T> set = new HashSet<T>();
			for (T data : array) {
				set.add(data);
			}
			return set;
		}
	}
   
    /**
     * 将List按separator分隔组合成字符串
     * <p>list只适用于基本类型</p>
     * <p>替换方法buildListToString(List,String)</p>
     * @param list
     * @param sep
     * @return
     */
    public static <T> String buildListToStr(List<T> list,String sep){
    	if(null == list || list.isEmpty() || null == sep || sep.equals("")){
    		return null;
    	}else{
    		Iterator<T> it = list.iterator();
        	StringBuffer result = new StringBuffer();
        	T item = null;
        	while(it.hasNext()){
        		item = it.next();
        		if(null == item)
        			continue;
        		result.append(item).append(sep);
        	}
        	return StringUtils.replaceLast(result.toString(), sep, "");
    	}
    }
    
    /**
     * <p>将一个字符串按照sparator分隔后，将分隔后的各部分变成List返回</p>
     * <p>str="a,b,c,d,e,f,g"</p>
     * <p>separator=","</p>
     * <p>List={a;b;c;d;e;f;g}</p>
     * @param str
     * @param separator
     * @return
     */
    public static List<String> buildStrToStrList(String str, String separator){
    	if(null == str || str.equals("") || null == separator || separator.equals("")){
    		return new ArrayList<String>();
    	}
    	String[] strs = str.split(separator);
    	List<String> resultList = new ArrayList<String>(strs.length);
    	for(String item : strs){
    		resultList.add(item);
    	}
    	return resultList;
    }
    
    /**
     * 将一个字符串按照sparator分隔后，将分隔后的各部分变成List返回 forexample: string="a,b,c,d,e,f,g";
     * separator=","; 返回：List={a;b;c;d;e;f;g};
     */
    public static List<Long> buildStrToLongList(String string, String separator) {
        List<Long> resultList = new ArrayList<Long>();
        if (string == null || string.equals("") || separator == null || separator.equals(""))
            return resultList;
        else {
            try {
                String[] strings = string.split(separator);
                for (int i = 0; i < strings.length; i++) {
                    if (!strings[i].equals("")) {
                        Long num = Long.valueOf(strings[i].trim());
                        resultList.add(num);
                    }
                }
                return resultList;
            } catch (Exception e) {
                return new ArrayList<Long>();
            }
        }
    }
    
    public static List<Integer> buildStrToIntList(String str,String sep){
    	if (str == null || str.equals("") || sep == null || sep.equals("")){
    		return new ArrayList<Integer>(0);
    	}
    	String[] strs = str.split(sep);
    	List<Integer> resultList = new ArrayList<Integer>(strs.length);
    	try{
        	for(String item : strs){
        		resultList.add(Integer.parseInt(item));
        	}
    	}catch(Exception e){
    		return new ArrayList<Integer>(0);
    	}
    	return resultList;
    }
    
    /**
     * <p>Map<K,V>中V按separator拼接成字符串</p>
     * <p>Map[{'a','a'},{'b','b'},{'c','c'}],separator = '|'返回结果a|b|c</p>
     * @param map
     * @param separator
     * @return
     */
    public static <K,V> String buildMapValueToStr(Map<K,V> map,String separator){
    	if(null == map || map.isEmpty() || null == separator || "".equals(separator)){
    		return null;
    	}
    	String str = null;
    	String val = null;
    	for(Map.Entry<K, V> item : map.entrySet()){
    		val = (null == item.getValue() ? null : item.getValue().toString());
    		if(null == str){
    			str = ((null == val || "".equals(val.trim())) ? null : val);
    		}else{
    			str += ((null == val || "".equals(val.trim()))? "" : (separator + val));
    		}
    	}
    	return str;
    }
    
    public static <K,V> String buildMapToStr(Map<K,V> map,String separator){
    	if(null == map || map.isEmpty() || null == separator || "".equals(separator)){
    		return null;
    	}
    	StringBuffer result = new StringBuffer();
    	for(Map.Entry<K, V> data : map.entrySet()){
    		result.append(data.getKey()).append(":").append(data.getValue()).append(separator);
    	}
    	return result.toString().substring(0, result.toString().length() - separator.length());
    }
    
    /**
     * 
     * 功能：将一个List<Map>的集合，按照某个columnName做为Key，将其值存成一个List，将list返回 forexample:
     * list={ Map1[key=id,value=1;key=name,value=张三]
     * Map2[key=id,value=2;key=name,value=李四] }; columnName=name;
     * 返回：List={张三;李四};
     * 
     * @param list
     * @param columnName
     * @return
     */
    public static <K,V> List<V> buildListMapToList(List<Map<K,V>> list,String columnName){
    	if(null == list || list.isEmpty() || null == columnName){
    		return new ArrayList<V>();
    	}else{
    		List<V> resultList = new ArrayList<V>();
    		for(Iterator<Map<K, V>> it = list.iterator(); it.hasNext();){
    			Map<K,V> row = (Map<K,V>)it.next();
    			if(null != row){
    				V value = row.get(columnName);
    				if(null != value && !resultList.contains(value)){
    					resultList.add(value);
    				}
    			}
    		}
    		return resultList;
    	}
    }
    
    /**
     * 将一个List中包含的各个Map分组，每个分组存成一个List;最终结果以Map<Object,
     * List>的形式返回，从而可以快速找到某个分组下的List。其中分组的字段名以groupColumnName指出 forexample:
     * list={ Map= key=id key=name 1 张三 1 李四 2 王五 groupColumnName=id 返回：Map{
     * --------------------------------- | key=1 | key=2 | | List{张三 李四} |
     * List{王五} | ----------------------------------
     * 
     * }
     * 
     */
    public static Map buildMapListToGroupMap(List list, String groupColumnName) {
        if (list == null || groupColumnName == null || groupColumnName.equals("")) {
            return new HashMap();
        } else {
            Map resultMap = new HashMap();
            for (Iterator it = list.iterator(); it.hasNext();) {
                Map row = (Map) it.next();
                if (row != null) {
                    Object key = row.get(groupColumnName);
                    if (key != null) {
                        List groupList = (List) resultMap.get(key);
                        if (groupList == null) {
                            groupList = new ArrayList();
                            resultMap.put(key, groupList);
                        }
                        // 把新找到的这个处理记录增加到处理记录列表中
                        groupList.add(row);
                    }
                }
            }
            return resultMap;
        }
    }
    
    /**
     * 
     * 功能：将一个Map<String,Object>，转码为指定编码的新Map<String,Object>
     * @param map
     * @param encode 编码字符集
     * @param decode 解码字符集
     * @return Map
     */
    public static Map<String,Object> encodeMapString(Map<String,Object> map,String encode,String decode) {
        Map<String,Object> newMap = new HashMap<String,Object>();
        if (map != null && map.size() > 0 && !StringUtils.isNull(encode) && !StringUtils.isNull(decode)) {
            for (Object obj : map.keySet()) {
                String key = (String) obj;
                String val = (String) map.get(key);
                String newVal = val;
                try {
                    newVal = new String(val.getBytes(encode), decode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                newMap.put(key, newVal);
            }
        }
        return newMap;
    }
    
    /**
     * 将List数组转换为一个Map集合.如:{[1,上海,4],[2,北京,5]}转换为Map{(key=1,value=指定的Object对象),(key=2,value=指定的Object对象)}
     * @param beanlist
     * @param propertyName
     * @param object
     * @return
     */
	public static Map buildArrayListToMap(List beanlist, String propertyName,Object object) {
		System.out.println("--------------开始计算" + new Date());
		try {
			Class targetCalss = object.getClass();
			Field[] fields = targetCalss.getDeclaredFields();
			if (beanlist == null || beanlist.size() == 0 || propertyName == null || propertyName.equals(""))
				return new HashMap();
			else {
				Map resultMap = new HashMap();
				PropertyDescriptor pd = null;
				Iterator it = beanlist.iterator();
				Long id = 0L;
				int t = 1;

				while (it.hasNext()) {
					if (t / 1000 == 0) {
						System.out.println("当前-----------------------" + t);
					}
					t++;
					Object newObject = targetCalss.newInstance();
					Object[] ob = (Object[]) it.next();
					for (int i = 0; i < ob.length; i++) {
						Object value = ob[i];
						if (value == null) {
							value = null;
						} else if (ob[i].getClass().equals(BigInteger.class)) {
							value = new Long(ob[i].toString());
							if (i == 0) {
								id = new Long(ob[i].toString());
							}
						} else if (ob[i].getClass().equals(String.class)) {
							value = ob[i].toString();
						} else if (ob[i].getClass().equals(BigDecimal.class)) {
							value = ((BigDecimal) value).doubleValue();
						}

						pd = new PropertyDescriptor(fields[i].getName().toString(), newObject.getClass());
						Class d = pd.getPropertyType();
						pd.getWriteMethod().invoke(newObject, value);
					}
					if (newObject != null) {
						if (pd == null)
							pd = new PropertyDescriptor(propertyName,newObject.getClass());
						resultMap.put(id, newObject);
					}
				}
				System.out.println("------------------结束计算" + new Date());
				return resultMap;
			}
		} catch (Exception e) {
			return new HashMap();
		}
	}
    
    /**
     * 将一个BO的List 转换成一个值的List返回，其中值为某个属性的值。
     * @param beanlist List<BO>，其中BO是个bean
     * @param propertyName 指定需要将BO的哪个属性做为取值域
     * @forexample: beanlist是Account对象List， propertyName是Account的属性id，返回以Account的id所有值的列表
     */
    public static List buildBeanListToPropertyList(List beanlist,String propertyName) {
        if (beanlist == null || beanlist.size() == 0 || propertyName == null
                || propertyName.equals(""))
            return new ArrayList();
        else {
            try {
                List resultList = new ArrayList();
                Object bean = null;
                PropertyDescriptor pd = null;
                Iterator it = beanlist.iterator();
                while (it.hasNext()) {
                    bean = it.next();
                    if (bean != null) {
                        if (pd == null)
                            pd = new PropertyDescriptor(propertyName, bean
                                    .getClass());
                        resultList.add(pd.getReadMethod().invoke(bean));
                    }
                }
                return resultList;
            } catch (Exception e) {
                return new ArrayList();
            }
        }
    }
    
    /**
     * 功能：将一个BO的List
     * 分组，先按propertyName1参数分组，分成Map<key,list>,形式，将LIST再按第二个参数分组，最终结果Map
     * (propertyName1.value,Map(propertyName2.value,bean))。
     * 
     * @param eventTimeoutInfos
     * @param string
     * @param string2
     * @return
     */
    public static Map buildBeanListToListMap(List beanlist,String propertyName1, String propertyName2) {
        if (beanlist == null || propertyName1 == null
                || propertyName1.equals("") || propertyName2 == null
                || propertyName2.equals("")) {
            return new HashMap();
        } else {
            try {
                Map resultMap = new HashMap();
                Object bean = null;
                PropertyDescriptor pd = null;
                PropertyDescriptor pd2 = null;
                Iterator it = beanlist.iterator();
                while (it.hasNext()) {
                    bean = it.next();
                    if (bean != null) {
                        if (pd == null) {
                            pd = new PropertyDescriptor(propertyName1, bean.getClass());
                        }
                        
                        Object key = resultMap.get(pd.getReadMethod().invoke(bean));
                        Map p = new HashMap();
                        if (pd2 == null) {
                            pd2 = new PropertyDescriptor(propertyName2, bean.getClass());
                        }
                        if (key != null) {
                            p = (Map) resultMap.get(pd.getReadMethod().invoke(bean));
                            p.put(pd2.getReadMethod().invoke(bean), bean);
                        }else{
                            p.put(pd2.getReadMethod().invoke(bean), bean);
                        }
                        resultMap.put(pd.getReadMethod().invoke(bean), p);
                    }
                }
                return resultMap;
                
            } catch (Exception e) {
                e.printStackTrace();
                return new HashMap();
            }
        }
    }
	
	/**
	 * javaBean转换为Map<String,String>
	 * <p>Object为简单的javaBean</p>
	 * @param bean
	 * @return
	 */
	public static Map<String, String> beanToMap(Object bean){
		if(null == bean){
			return new HashMap<String, String>();
		}else{
			Map<String, String> data = new HashMap<String, String>();
			JSONObject jsonObject = (JSONObject)JSON.toJSON(bean);
			for(String key : jsonObject.keySet()){
				Object val = jsonObject.get(key);
				if(null != val){
					data.put(key, val.toString());
				}else{
					data.put(key, "");
				}
			}
			return data;
		}
	}
	
	/**
	 * 将实体转换为hashMap
	 * <p>Object为简单的javaBean</p>
	 * @param object
	 * @return
	 */
	public static HashMap<String, Object> beanToHashMap(Object object) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		if(null == object){
			return data;
		}else{
			JSONObject jsonObject = (JSONObject)JSON.toJSON(object);
			for(String key : jsonObject.keySet()){
				data.put(key, jsonObject.get(key));
			}
			return data;
		}
	}
	
	/**
	 * Bean转换为Key:value字符串并把key按升序排序
	 * @param bean
	 * @return
	 */
	public static <T> String beanToBeanStr(T bean){
		if(null == bean){
			return null;
		}else{
			return sortMap(beanToMap(bean));
		}
	}
	
	/**
	 * Bean转换为Key:value字符串并把key按升序排序
	 * @param itemList
	 * @return
	 */
	public static <T> String beanToBeanStr(List<T> beanList){
		if(null == beanList || beanList.isEmpty()){
			return null;
		}else{
			try{
				StringBuffer str = new StringBuffer();
				Map<String,String> itemMap = null;
				for(T t : beanList){
					itemMap = beanToMap(t);
					str.append(sortMap(itemMap));
				}
				return str.toString();
			}catch(Exception e){
				return null;
			}
		}
	}
	
	/**
	 * <p>对参数Map按key倒序并拼接成字符串返回</p>
	 * <p>Value为null或trim()后为''，也参与拼接</p>
	 * @param param
	 * @return
	 */
	public static String sortMap(Map<String,String> param){
    	if(null == param || param.isEmpty()){
			return null;
		}
    	List<Map.Entry<String,String>> sortList = new ArrayList<Map.Entry<String, String>>(param.entrySet());
    	Collections.sort(sortList, new Comparator<Map.Entry<String, String>>() {   
    		public int compare(Map.Entry<String, String> paramOne, Map.Entry<String, String> paramTwo) {
    			return paramOne.getKey().compareTo(paramTwo.getKey());
    		}
    	});
    	StringBuffer str = new StringBuffer();
    	for(Map.Entry<String, String> map : sortList){
    		str.append(map.getKey().trim());
    		if(null == map.getValue() ||  "".equals(map.getValue())){
    			continue;
    		}else{
    			str.append(map.getValue().trim());
    		}
		}
    	return str.toString();
    }
	
	/**
	 * Map转换为Bean
	 * <p>抽象类不能使用此方法，必须是抽象类的实现类</p>
	 * @param ct   转换为Bean的类型
	 * @param map  需要转换的Map
	 * @return
	 */
	public static <T,K,V> T mapToBean(Class<T> ct,Map<K,V> map){
		if(null == ct || null == map || map.isEmpty()){
			return null;
		}else{
			 try {
				BeanInfo beanInfo = Introspector.getBeanInfo(ct);//获取类属性
				T bean = ct.newInstance();//创建 JavaBean 对象
				
				//给JavaBean 对象的属性赋值
		        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
		        for (int i = 0; i< propertyDescriptors.length; i++) {
		            PropertyDescriptor descriptor = propertyDescriptors[i];  
		            String propertyName = descriptor.getName();
		  
		            if (map.containsKey(propertyName)) {
		                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
		                Object value = map.get(propertyName);  
		                if(value == null || value.equals("")){
		                	continue;
		                }else{
		                	Object[] args = new Object[1];
			                args[0] = value;
			                descriptor.getWriteMethod().invoke(bean, args);
		                }
		            }
		        }
		        return bean;
			} catch (Exception e) {
				System.err.println("map to Bean error,error info：" + e.getMessage());
				return null;
			}
		}
	}
}