package com.duan.sysmodule.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;
/*import com.dbn.result.DataResultMsg;
import com.dbn.user.bean.LoginUserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;*/

/**
 * Created by long on 2015/6/6.
 */
public class JSONUtils {

    //Google JSON
    //private static Gson gson = new Gson();

    /**
     * javaBean转换为JSON字符串
     * 支持List<T>或T
     * @param resultBean
     * @return
     */
    public static <T> String beanToJSONStr(T bean){
        if(null == bean){
            return null;
        }else{
            return JSONObject.toJSONString(bean);
        }
    }

    /**
     * JSONStr转换为JavaBean
     * @param jsonStr    需要转换的JSON字符串
     * @param beanClass  需要转换的JavaBean类型
     * @param <T>        转换结果
     * @return
     */
    public static <T> T jsonStrToBean(String jsonStr,Class<T> beanClass){
        if(null == jsonStr || "".equals(jsonStr.trim()) || null == beanClass){
            return null;
        }else{
            return JSON.parseObject(jsonStr, beanClass);
        }
    }

    /**
     * jsonStr转换为JavaBean
     * @param json   需要转换的JSON字符串
     * @param type   需要转换的JavaBean类型
     * @param <T>    转换结果
     * @return
     */
    public static <T> T jsonStrToBean(String json, TypeReference<T> type) {
        if(null == json || "".equals(json.trim()) || null == type){
            return null;
        }else{
            return JSON.parseObject(json, type);
        }
    }

    /**
     * JSONStr转换为ListBean
     * @param jsonStr
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonStrToListBean(String jsonStr,Class<T> beanClass){
        if(null == jsonStr || "".equals(jsonStr.trim()) || null == beanClass){
            return null;
        }else{
            return JSON.parseArray(jsonStr,beanClass);
        }
    }

    /**
     * Map<K,V>转换为JSON字符串
     * <p>理论上讲只要是Map集合都可以转换为JSONStr</p>
     * @param map  待转换的Map
     * @return     JSON字符串
     */
    public static <K,V> String mapToJSONStr(Map<K,V> map){
    	if(null == map || map.isEmpty()){
    		return null;
    	}else{
    		return JSONObject.toJSONString(map);
    	}
    }
    
    /**
     * jsonStr转换为Map
     * <p>只能转换普通的数据，如Map<String,Sring>，不能转换Map<String,Bean></p>
     * @param jsonStr
     * @param keyC
     * @param valC
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> jsonStrToMap(String jsonStr,Class<K> keyC,Class<V> valC){
    	if(null == jsonStr || "".equals(jsonStr)){
    		return null;
    	}else{
    		return (Map<K, V>) JSONObject.parse(jsonStr);
    	}
    }
    
    /**
     * bean转换为Map<String,Bean>
     * @param bean
     * @return
     */
    public static <T> Map<String,String> beanToMap(T bean){
    	if(null == bean){
    		return null;
    	}else{
    		String jsonStr = beanToJSONStr(bean);
    		return jsonStrToMap(jsonStr, String.class, String.class);
    	}
    }
    
    /**
     * google JSON
     * @param jsonStr
     * @param beanClass
     * @return
     */
    /*public static <T> T jsonStrToBeanByGoogle(String jsonStr,Class<T> beanClass){
        if(null == jsonStr || "".equals(jsonStr.trim()) || null == beanClass){
            return null;
        }else{
            return gson.fromJson(jsonStr, beanClass);
        }
    }*/

    /**
     * googleJSON
     * @param jsonStr
     * @param type  需要转换的类型
     * @return
     */
    /*public static <T> T jsonStrToBeanByGoogle(String jsonStr,TypeToken<T> type){
        if(null == jsonStr || "".equals(jsonStr.trim()) || null == type){
            return null;
        }else{
            return gson.fromJson(jsonStr, type.getType());
        }
    }*/

    /*public static void main(String[] args){
        LoginUserInfo userInfo = new LoginUserInfo();
        Long userId = 30618L;
        userInfo.setUserId(userId);
        userInfo.setUname("方海龙");
        userInfo.setActivated(1);
        userInfo.setCertStatus(1);
        userInfo.setCustName("方海龙");
        userInfo.setMobile("15210283563");

        DataResultMsg<LoginUserInfo> dataResult = new DataResultMsg<LoginUserInfo>();
        dataResult.setRestCode(0);
        dataResult.setRestMsg("");
        dataResult.setData(userInfo);

        //BeanToJSONStr
        String jsonStr = beanToJSONStr(dataResult);
        System.out.println("JSONStr：" + jsonStr);
        System.out.println("----------googleJson---------------");
        DataResultMsg<LoginUserInfo> dataResltMsg = jsonStrToBeanByGoogle(jsonStr,new
TypeToken<DataResultMsg<LoginUserInfo>>(){});
        System.out.println("dataResultMsg：" + dataResltMsg.toString());
        System.out.println("dataMsg：" + dataResltMsg.getData().toString());

        System.out.println("----------fastJson---------------");
        DataResultMsg<LoginUserInfo> resltMsg = jsonStrToBean(jsonStr,new
TypeReference<DataResultMsg<LoginUserInfo>>(){});
        System.out.println("resltMsgStr：" + resltMsg.toString());
        System.out.println("dataMsgStr：" + resltMsg.getData().toString());
    }*/
}