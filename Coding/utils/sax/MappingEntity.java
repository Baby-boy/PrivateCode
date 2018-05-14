package com.glanway.util.sax;

/**
 * Excel2007单元格映射实体.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年5月14日
 */
public class MappingEntity {

	/** key */
	private String key;
	/** value */
	private String value;

	public MappingEntity(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * 去除空格判断.
	 * 
	 * @param entity
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public int getLevel(MappingEntity entity) {
		char[] other = entity.getKey().replaceAll("[0-9]", "").toCharArray();
		char[] self = getKey().replaceAll("[0-9]", "").toCharArray();
		if (other.length != self.length)
			return -1;
		for (int i = 0; i < other.length; i++) {
			if (i == other.length - 1) {
				return self[i] - other[i];
			} else {
				if (self[i] != other[i]) {
					return -1;
				}
			}

		}

		return -1;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
