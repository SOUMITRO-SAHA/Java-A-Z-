/*
Flattening a Linked List
https://course.acciojob.com/idle?question=190b12c6-5a31-436d-8821-bd29626d80ad
 */
import java.util.*;

// Java program for flattening a Linked List
public class Main {

    /* Linked list Node*/
    static class Node {

        int data;
        Node right, down;

        Node(int data) {
            this.data = data;
            right = null;
            down = null;
        }
    }

    static Node flatten(Node root) {
        // Hint: Merging two different LL:
        // Base Case:
        if (root == null || root.right == null) return root;
        Node nextNodeRight = root.right;
        // call merge function:
        Node t = flatten(nextNodeRight);
        return mergeLL(root,t);
    }

    static Node mergeLL(Node h1, Node h2) {
        Node d = new Node(-1);
        Node last = d;
        Node l1 = h1;
        Node l2 = h2;
        // Breaking the right Reference of the provided LL:
        l1.right = null;

        // Initial starting Node:
        if (l1.data <= l2.data) {
            last.down = l1;
            last = l1;
            l1 = l1.down;
        }
        else {
            last.down = l2;
            last = l2;
            l2 = l2.down;
        }

        // Merging the Nodes of the LL1 and LL2:
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                last.down = l1;
                last = l1;
                l1 = l1.down;
            } else {
                last.down = l2;
                last = l2;
                l2 = l2.down;
            }
        }

        // Condition:
        // if there is still any ll left
        if (l1 != null) {
            last.down = l1;
        } else {
            last.down = l2;
        }

        // Returning the LL
        return d.down;
    }

    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.down;
        }
        System.out.println();
    }

    public static boolean isCorrect(Node claim) {
        if (claim.right != null) return false;
        Node copy = claim;
        while (copy.down != null) {
            if (copy.down.data < copy.data) return false;
            copy = copy.down;
        }
        return true;
    }

    // Driver's code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node ll = new Node(-1), prev1 = null, head = ll;
        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            Node prev2 = null, copy = ll;
            while (k-- != 0) {
                copy.data = sc.nextInt();
                copy.down = new Node(-1);
                prev2 = copy;
                copy = copy.down;
            }
            assert prev2 != null;
            prev2.down = null;
            ll.right = new Node(-1);
            prev1 = ll;
            ll = ll.right;
        }
        sc.close();
        assert prev1 != null;
        prev1.right = null;
        Node flattenedNode = flatten(head);
        printList(flattenedNode);
        if (isCorrect(flattenedNode)) System.out.println(
                "yes"
        ); else System.out.println("no");
    }
}