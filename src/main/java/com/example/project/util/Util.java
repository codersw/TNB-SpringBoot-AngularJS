package com.example.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 常用工具类
 * Created by sw on 2017/10/26.
 */
public class Util {

    /**
     * 生成uuid
     * @return
     */
    public static String UUID(){
       return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 判断字符串是不是NULL或是空字符串,如果是，返回true，不是false
     * @param str 待判断字符串
     * @return true/false
     */
    public static boolean isNullOrEmpty(final String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断字符串对象是不是NULL或空，如果是，返回true，不是false
     * @param str 字符串对象
     * @return true/false
     */
    public static boolean isNullOrEmpty(final Object str){
        return str==null||"".equals(str.toString());
    }

    /**
     * 数组转字符串
     * @param array 数组
     * @param surffix 分割符号
     * @return
     */
    public static String array2String(final String[] array,final String surffix){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<array.length;i++){
            if(i==array.length-1){
                builder.append(array[i]);
            }
            else {
                builder.append(array[i]).append(surffix);
            }
        }
        return builder.toString();
    }

    /**
     * 把map 转换成对象 (调用set方法，方便加密)
     * @param map 需要转换的map
     * @param c 要转换成对象的class
     * @return
     */
    public static Object mapToObj(Map<?, ?> map, Class<?> c) throws Exception {
        //用json 转型
        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.convertValue(map, c);
        //下面是反射转型
       /* Object o=null;
        o=c.newInstance();
        Method[] methods=c.getMethods();
        for(Method m:methods){
            m.setAccessible(true);
            String methodName=m.getName();
            if(!methodName.contains("set")) continue;   //如果不是set方法则跳过
            Class<?> paraType=m.getParameterTypes()[0];
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String value=(String)map.get(methodName.substring(3,4).toLowerCase()+methodName.substring(4));      //把方法名中的set字符去掉，然后第一个字母转小写，就是bean属性，以此为key从map 中取值
            if(value==null || value.equals(""))continue;    //根据bean属性在map中获取不到值则跳过
            if( paraType== java.sql.Date.class){       //如果该set方法的参数类型是date 或者 timestamp
                m.invoke(o,new java.sql.Date(sdf.parse(value).getTime()));
            }else if(paraType==Date.class ){
                m.invoke(o,new Date(sdf.parse(value).getTime()));
            }else if( paraType== Timestamp.class){
                m.invoke(o,new Timestamp(sdf.parse(value).getTime()));
            }else if(paraType== int.class){                   //如果该set方法的参数类型是int
                m.invoke(o,Integer.parseInt(value));
            }else if(paraType== BigDecimal.class){
                m.invoke(o,new BigDecimal(value));
            }else if(paraType==String.class) {                //如果该set方法的参数类型是 string
                m.invoke(o,value);
            }
        }*/
        return o;
    }

    /**
     * 对象转换成map
     * @param obj
     * @return
     */
    public static Map<String ,Object> ObjToMap(Object obj){
        Map<String ,Object> map=new HashMap<String ,Object>();
        Field[] fields=obj.getClass().getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
            String name=f.getName();
            try {
                map.put(name,f.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
