package sandbox;

import java.util.ArrayList;
import java.util.List;


/*
 * To execute Java, please define "static void main" on a class named Solution.
 * 
 * If you need more classes, simply define them inline.
 */

class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;
    
    List<Node<T>> nodes = new ArrayList<>();

    Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}


class MyTree<T extends Comparable<T>> {
    Node<T> root;

    void insert(T item) {
        if (root == null) {
            root = new Node<T>(item, null, null);
        }
        else
            insert(root, item);
    }

    void insert(Node<T> node, T item) {
        if (node == null || item == null) {
            return;
        }

        if (item.compareTo(node.data) < 0) {
            if (node.left != null) {
                insert(node.left, item);
            } else {
                // left side is null
                node.left = new Node<T>(item, null, null);
            }
        } else {
            if (node.right != null) {
                insert(node.right, item);
            } else {
                node.right = new Node<T>(item, null, null);
            }
        }
    }

    void print() {
        printNode(root);
    }

    void printNode(Node<T> node) {
        if (node != null) {
            System.out.println(node.data);
            
            printNode(node.left);
            printNode(node.right);
        }
    }
    
    List<Node<T>> findMinPath() {
        
        return null;
    }
}


class Solution {

    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();
        tree.insert(10);
        tree.insert(2);
        tree.insert(11);
        
        tree.print();
    }
}
