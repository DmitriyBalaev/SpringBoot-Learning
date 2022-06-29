package com.balaevdm.spring;

import java.util.*;

public class Еуые {

    public static void main(String[] args) {
        System.out.println();
        mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4))));
    }



     static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> arr = new ArrayList<>();

        boolean check = true;

        while (check){
           int val = list1.val;
           ListNode node = list1.next;
           arr.add(val);
           if (node == null){
               check = false;
           }
        }

        System.out.println(arr);
        return new ListNode();
    }
}
