package SXT._3CollectionAndDataStructure.part1;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 实现一个简单的HashMap
 * @author: LiuDongMan
 * @createdDate: 2019-08-14 09:53
 **/
public class MyHashMap<K, V> {
    private Node<K, V>[] table; // 位桶数组，存储Node节点
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 相当于16
    private int size;   // 元素个数

    public MyHashMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    public MyHashMap(int capactiy) {
        table = new Node[capactiy];
    }

    /**
     * 添加键值对
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        // 如果要继续完善，需要考虑数组的扩容问题

        // 计算出key的hash值，以此来判断将键值对存在位桶数组的哪个位置
        int keyHashCode = key.hashCode();
        int hash = hash(keyHashCode, table.length);
        Node<K, V> newNode = new Node<>(hash, key, value, null);

        // 如果第一次添加
        if (size == 0) {
            table[hash] = newNode;
            size++;
        } else {
            Node<K, V> temp = table[hash];

            for (int i = 0; i < size; i++) {
                if (key.equals(temp.key)) {
                    temp.value = value; // 当key相同时，只需要将值进行替换即可
                    break;
                }

                if (temp.next == null) {
                    temp.next = newNode;
                    size++;

                    break;
                }

                temp = temp.next;
            }
        }
    }

    /**
     * 根据hash值和key获取节点对象
     * @param hash
     * @param key
     * @return
     */
    private Node<K, V> getNode(int hash, K key) {
        Node<K, V> temp = table[hash];

        for (int i = 0; i < size; i++) {
            if (key.equals(temp.key)) {
                return temp;
            }

            temp = temp.next;
        }

        return null;
    }

    /**
     * 根据键获取对应的值
     * @param key
     * @return
     */
    public V get(K key) {
        Node<K, V> e;
        return (e = getNode(hash(key.hashCode(), table.length), key)) == null ? null : e.value;
    }

    /**
     * 根据hashCode和位桶数组的大小计算出对应的hash值，如果采用方法中的位算法，则位桶数组的大小必须是2的指数幂
     * @param hashCode
     * @param length
     * @return
     */
    private int hash(int hashCode, int length) {
        return hashCode & (length - 1);
    }

    /**
     * 获取元素个数
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 获取节点对象所在单向链表的键值对的值组成的字符串
     * @param node
     * @return
     */
    private String getNodeString(Node<K, V> node) {
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(node.key).append("=").append(node.value).append(", ");
            node = node.next;
        }

        return sb.substring(0, sb.lastIndexOf(","));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                sb.append(getNodeString(table[i]));
            }
        }

        sb.append("}");

        return sb.toString();
    }

    /**
     * 单向链表中的节点类
     * @param <K>
     * @param <V>
     */
    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node() {
        }

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>(17);

        map.put("1", "a");

        System.out.println(map.toString());

//        Map<String, String> map = new HashMap<>();
//        map.put("1", "a");
//        map.put("2", "b");
//        map.put("3", "c");
//        System.out.println(map.toString());
    }
}
