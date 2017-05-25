package com.yd.gcj.tool;

import java.util.HashMap;

public class MapInitFactory {
	HashMap<String, Object> map = new HashMap<>();
	
	public MapInitFactory() {
		map = new HashMap<String,Object>();
	}
	
	public MapInitFactory(String code,String msg){
		map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("msg", msg);
	}
	
	/***
	 * 初始化状态
	 * @return
	 */
	public MapInitFactory init(){
		if(map!=null){
			map.put(Values.NAME_CODE,Values.INITSUCCESSCODE);
			map.put(Values.NAME_MSG,Values.INITSUCCESSDESC);
		}
		return this;
	}
	
	/***
	 * 系统异常设置
	 * @return
	 */
	public MapInitFactory setSystemError(){
		if(map!=null){
			map.put(Values.NAME_CODE, Values.ERRORCODE);
			map.put(Values.NAME_MSG, Values.ERRORCODEDESC);
		}
		return this;
	}
	
	/***
	 * 设置状态码
	 * @param code
	 * @return
	 */
	public MapInitFactory setCode(String code){
		map.put(Values.NAME_CODE, code);
		return this;
	}
	
	/***
	 * 设置状态描述信息
	 * @param state
	 * @return
	 */
	public MapInitFactory setState(String state){
		map.put(Values.NAME_MSG, state);
		return this;
	}
	
	/***
	 * 添加数据到data里面
	 * @param obj
	 * @return
	 */
	public MapInitFactory setData(Object obj){
		map.put(Values.NAME_DATA, obj);
		return this;
	}
	
	public Object getData(){
		return map.get("data");
	}
	
	public MapInitFactory setDatas(String key,Object obj){
		map.put(key, obj);
		return this;
	}
	
	/***
	 * 更新map
	 * @param map
	 * @return
	 */
	public MapInitFactory setMap(HashMap<String,Object> map){
		this.map = map;
		return this;
	}
	
	public MapInitFactory put(String k,Object v){
		if(map!=null){
			map.put(k, v);
		}
		return this;
	}
	
	/***
	 * 
	 * @return
	 */
	public MapInitFactory setMsg(String code,String msg){
		map.put(Values.NAME_CODE, code);
		map.put(Values.NAME_MSG, msg);
		return this;
	}
	
	/***
	 * @return
	 */
	public HashMap<String, Object> getMap(){
		return map;
	}
	
	
	
}
