package com.test.client;

/**
 * 自定义的一个模仿ArrayList的类， 你需要实现其中的add, get, remove , 等方法
 * 
 * @author 刘欣
 */
public class SimpleList<T> {
	private Object[] elementData;
	private int size = 0;

	public int size() {
		return elementData == null ? -1 : this.size;
	}

	public SimpleList() {
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(T e) {
		elementData[size++] = e;
		return true;
	}

	public boolean remove(Object o) {
		return false;
	}

	public T get(int index) {
		if (index <= size) {
			return (T) SimpleList.this.elementData[index];
		}
		return null;
	}

	public static void main(String[] args) {

		SimpleList<Object> simpleList = new SimpleList<>();

		System.out.println(simpleList.add("123"));
		System.out.println(simpleList.add("456"));
		System.out.println(simpleList.add("789"));
		System.out.println(simpleList.add("0"));
		System.out.println(simpleList.get(0));
		System.out.println(simpleList.get(3));
	}
}