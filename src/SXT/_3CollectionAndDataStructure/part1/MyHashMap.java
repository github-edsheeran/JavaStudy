package SXT._3CollectionAndDataStructure.part1;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 实现一个简单的HashMap
 * @author: LiuDongMan
 * @createdDate: 2019-08-14 09:53
 **/
public class MyHashMap<K, V> {
    private Node<K, V>[] table; // 位桶数组
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 相当于16
    private int size;

    public MyHashMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    public MyHashMap(int capactiy) {
        table = new Node[capactiy];
    }

    public void put(K key, V value) {
        int keyHashCode = key.hashCode();
        int hash = hash(keyHashCode, table.length);

        if (table[hash] == null) {
            Node<K, V> newNode = new Node<>(hash, key, value, null);
        } else {

        }
    }

    private int hash(int hashCode, int length) {
        System.out.println(hashCode & (length - 1));
        return hashCode & (length - 1);
    }

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
    }
}
