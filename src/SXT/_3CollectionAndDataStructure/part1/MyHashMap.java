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
        Node<K, V> newNode = new Node<>(hash, key, value, null);

        if (size == 0) {
            table[hash] = newNode;
        } else {
            Node<K, V> temp = table[hash];
            Node<K, V> next = null;
            Node<K, V> prev = null;

            for (int i = 0; i < size; i++) {
                if (key.equals(temp.key)) {
                    next = temp.next;
                    break;
                }

                if (temp.next == null) {
                    break;
                }

                temp = temp.next;
                prev = temp;
            }

            temp.next = newNode;
        }

        size++;
    }

    public V get(V key) {
        int keyHashCode = key.hashCode();
        int hash = hash(keyHashCode, table.length);
        Node<K, V> temp = table[hash];
        V value = null;

        for (int i = 0; i < size; i++) {
            if (key.equals(temp.key)) {
                value = temp.value;
                return value;
            }

            temp = temp.next;
        }

        return null;
    }

    private int hash(int hashCode, int length) {
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
        map.put("2", "b");
        map.put("3", "c");

        System.out.println(map.get(""));
    }
}
