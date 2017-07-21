package com.example;



/**
 * Created by peiboning on 2017/7/21.
 */

public class Link {

    public static  void sort(){
        new Link().run();
    }

    private void run(){
        createLink();
        print();
        revser();
        print();
    }

    private void revser() {
        if(null == head){
            return;
        }

        if(head.next == null){
            return;
        }

        Node temp = null;
        Node current = head.next;
        Node next = current.next;
        Node FTemp = current;

        while (next != null){
            temp = next.next;
            next.next = current;
            current = next;
            next = temp;
        }

        head.next = current;
        FTemp.next = null;
    }

    private Node head;
    class Node{
        int data;
        Node next;
    }

    private void createLink(){
        head = new Node();
        for(int i = 1;i<=30;i++){
            Node node = new Node();
            node.data = i;
            add(node);
        }


    }

    private void print(){
        Node next = head.next;
        while (next != null){
            System.out.print(next.data+",");
            next = next.next;
        }
        System.out.print("\n");
    }

    private void add(Node node){
        Node next = head.next;
        if(next == null){
            head.next = node;
            return;
        }
        while (next.next != null){
            next = next.next;
        }

        next.next = node;
    }
}
