package SXT._3CollectionAndDataStructure.part1;

import java.util.Arrays;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现一个ArrayList
 * @author: LiuDongMan
 * @createdDate: 2019-08-12 20:47
 */
public class MyArrayList<E> {
    /**
     * 元素个数
     */
    private int size;
    /**
     * 默认容量
     */
    private static int DEFAULT_CAPACITY = 10;
    /**
     * 元素数组
     */
    private Object[] elementData;

    /**
     * 构造一个默认容量的MyArrayList
     */
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * 构造一个指定容量的MyArrayList
     * @param capacity
     */
    public MyArrayList(int capacity) {
        if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else if (capacity < 0) {
            throw new RuntimeException("容易容量不能小于0!");
        } else {
            elementData = new Object[capacity];
        }
    }

    /**
     * 检查索引是否合法
     * @param index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("索引越界!");
        }
    }

    /**
     * 添加元素
     * @param element
     */
    public void add(E element) {
        if (size == elementData.length) {
            Object[] newElementData = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }

        elementData[size++] = element;
    }

    /**
     * 获取指定索引位置上的元素
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);

        return (E) elementData[index];
    }

    /**
     * 移除MyArrayList中和传入参数值相同的元素
     * @param element
     */
    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(get(i))) {
                remove(i);
            }
        }
    }

    /**
     * 移除指定索引位置上的元素
     * @param index
     */
    public void remove(int index) {
        rangeCheck(index);

        int numMoved = elementData.length - index - 1;

        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        elementData[--size] = null;
    }

    /**
     * 返回元素个数
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    /**
     * 替换指定索引位置上的元素
     * @param element
     * @param index
     */
    public void set(E element, int index) {
        rangeCheck(index);

        elementData[index] = element;
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }

        System.out.println(list.toString());
    }
}
