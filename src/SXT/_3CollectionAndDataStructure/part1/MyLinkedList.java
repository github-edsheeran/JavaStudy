package SXT._3CollectionAndDataStructure.part1;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现一个LinkedList
 * @author: LiuDongMan
 * @createdDate: 2019-08-13 08:41
 **/
public class MyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    /**
     * 添加元素
     * @param element
     */
    public void add(E element) {
        // 这个地方不能用first，因为在加入第三个元素的时候，会把first的next设置为包含第三个元素的节点
        Node<E> temp = last;
        Node<E> newNode = new Node<>(temp, element, null);
        last = newNode;

        // 如果last为空，证明first也为空
        if (temp == null) {
            first = newNode;
        } else {
            temp.next = newNode;
        }

        // 额外注意
        size++;
    }

    /**
     * 检查索引合法性
     * @param index
     */
    private void rangCheck(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("索引越界!");
        }
    }

    /**
     * 获取指定索引位置上的元素
     * @param index
     * @return
     */
    public E get(int index) {
        return getNode(index).item;
    }

    /**
     * 私有获取节点对象的方法
     * @param index
     * @return
     */
    private Node<E> getNode(int index) {
        rangCheck(index);

        Node<E> temp = null;

        // 如果查询的索引在list的前半部分，则从list的first开始从前往后查找；如果在list的后半部分，则从list的last开始从后往前查找
        if (index <= (size >> 1)) {
            temp = first;

            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = last;

            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
        }

        return temp;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<E> temp = first;

        // 如果temp变量为空，证明list中的元素已经全部遍历，退出循环
        while (temp != null) {
            sb.append(temp.item).append(",");
            temp = temp.next;
        }

        sb.setCharAt(sb.length() - 1, ']');

        return sb.toString();
    }

    /**
     * 移除指定索引位置上的元素
     * @param index
     */
    public void remove(int index) {
        Node<E> temp = getNode(index);
        Node<E> prev = temp.prev;
        Node<E> next = temp.next;

        // 如果删除的是第一个元素，则需重置first的指向
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        // 如果删除的是最后一个元素，则需重置last的指向
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }

        // 额外注意
        size--;

        temp = null;
    }

    /**
     * 返回元素个数
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 在指定索引位置插入元素
     * @param element
     * @param index
     */
    public void add(E element, int index) {
        Node<E> temp = getNode(index);
        Node<E> prev = temp.prev;
        Node<E> next = temp.next;
        Node<E> newNode = new Node<>(prev, element, temp);

        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }

        temp.prev = newNode;

        // 额外注意
        size++;
    }

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.add(6, 0);
        list.add(8, 4);

        System.out.println(list.toString());
    }
}
