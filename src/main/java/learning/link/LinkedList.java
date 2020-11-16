package learning.link;

/**
 * @author Lichenyi
 * @date 2017-9-18 0018
 */
public class LinkedList {

    int lenght;// 链表的长度
    Node head;// 定义头结点

    public LinkedList() {
        head = null;// 链表中的头结点
        lenght = 0;
    }

    /** 在链表末尾添加一个元素 */
    public void addElement(Node n) {
        if (lenght == 0) {// 如果链表为空，则直接添加。
            head = n;
        } else {
            Node t = head;
            while (t.next != null) {// 沿链表第一个元素向后寻找，直到找到末尾
                t = t.next;
            }
            t.next = n;// 在末尾添加元素，也就是给最后一个next赋值~让链域不为空，这样链域为空的就成了链表中最后一个元素
        }
        // 在主函数中调用这个的方法为addElement(new Node("张三"))，
        // 其中new一个Node 就确定了这个Node的数据域（data）为"张三"，链域（next）为null，
        // 而这个函数的作用就是确定把这个新new出来Node放在谁的next中
        lenght++;// 修改长度
    }

    /** 在链表中特定位置添加一个元素 */
    public boolean insert(Node n, int index) {
        if (index > lenght && index < 0) {// 如果插入位置超出链表长度
            System.out.println("超出范围！！");
            return false;
        } else {
            Node t = head;
            if (index == 0) {
                n.next = head;// 让传入的节点指向头节点
                head = n;// n作为新的头节点
            } else {
                for (int i = 0; i < index - 1; i++) {// 找到要插入的位置的前一个位置
                    t = t.next;
                }
                n.next = t.next;// 新插入的节点指向当前位置节点
                t.next = n;
                // 这里需要用图解释的更为清晰
                // 可见本文下方图—1；
            }
            lenght++;
            return true;
        }
    }

    /** 删除链表最后一个节点 */
    public void removeLastElement() {
        if (lenght == 0) {
            System.out.println("链表中没有元素");
            return;
        }
        Node t = head;
        if (lenght == 1) {
            head = null;
        } else {
            for (int i = 0; i < lenght - 1; i++) {// 找到末尾节点的前一个节点
                t = t.next;
            }
            t.next = null;
        }
        lenght--;
    }

    /** 删除指定位置的节点 */
    public void removeElement(int index) {
        if (lenght == 0) {
            System.out.println("链表没有元素！");
            return;
        }
        if (index > lenght && index < 0) {
            System.out.println("超出范围！！");
            return;
        }
        Node t = head;
        if (index == 0) {
            head = head.next;
            t.next = null;
            lenght--;
            return;
        }

        for (int i = 0; i < index - 1; i++) {// 找到要删除位置的前一位置
            t = t.next;
        }
        Node temp = t;
        t.next = t.next.next;
        temp.next = null;// 让该节点的next指向空
        lenght--;

    }

    /** 清空链表 */
    public void clearList() {
        head = null;
        lenght = 0;
    }

    /** 打印链表中的所有元素 */
    public void printList() {
        if (lenght == 0) {
            System.out.println("链表为空！");
            return;
        }
        Node t = head;
        while (t!= null) {
            System.out.print(t.data + " ");
            t = t.next;
        }
    }

}
