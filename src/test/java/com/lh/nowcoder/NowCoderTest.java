package com.lh.nowcoder;

import org.junit.Test;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-14 20:38
 **/
public class NowCoderTest {


    @Test
    public void testReverseList(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(node1);
        System.out.println(reverseList(node1));
    }

    private ListNode reverseList(ListNode head) {
        if(head==null){
            return null;
        }

        ListNode preNode = null;
        ListNode nextNode = null;
        while (head != null) {
            nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }
}
