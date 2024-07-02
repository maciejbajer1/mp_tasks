//Basic operations on binary search tree, most of them are iterative.

import java.util.Scanner;
public class Source {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int numOfSets = in.nextInt(); 
        in.nextLine();
        for (int i = 1; i <= numOfSets; i++){
            Tree tree = new Tree(); 
            int numOfCommands = in.nextInt();
            System.out.println("ZESTAW " + i);

            for (int j = 0; j < numOfCommands; j++){ 
                String command = in.next(); 
                
                if (command.equals("CREATE")){ 
                    tree.clear(); 
                    String order = in.next(); 
                    
                    int numOfArguments = in.nextInt(); 
                    Person[] arr = new Person[numOfArguments]; 
                    for(int k = 0; k < numOfArguments; k++) {
                        arr[k] = new Person(in.nextInt(), in.next(), in.next()); 
                    }
                    if (order.equals("POSTORDER")) { 
                        tree.root = tree.createPostorder(arr, 0, arr.length - 1); 
                        
                    } else if (order.equals("PREORDER")) { 
                        tree.root = tree.createPreorder(arr, 0, arr.length - 1);

                    }
                }
                if (command .equals("DELETE")){ 
                    int prio = in.nextInt(); 
                    tree.delete(prio);  
                }
                if (command.equals("ENQUE")){ 
                    int prio = in.nextInt(); 
                    String n = in.next();
                    String surn = in.next();
                    Person newPerson = new Person(prio, n, surn); 
                    tree.enque(newPerson);
                }
                if (command.equals("DEQUEMAX")){ 
                    Node max = tree.dequeMax();
                    System.out.println("DEQUEMAX: " + max.info.priority + " - " + max.info.name + " " + max.info.surname); 
                }
                if (command.equals("DEQUEMIN")){ 
                    Node min = tree.dequeMin();
                    System.out.println("DEQUEMIN: " + min.info.priority + " - " + min.info.name + " " + min.info.surname); 
                }
                if (command.equals("NEXT")){ 
                    int x = in.nextInt(); 
                    Node temp = tree.next(x);
                    if (temp != null){ 
                        System.out.println("NEXT " + x + ": " + temp.info.priority + " - " + temp.info.name + " " + temp.info.surname);
                    }
                    else{
                        System.out.println("NEXT " + x + ": BRAK");
                    }
                }
                if (command.equals("PREV")){ 
                    int x = in.nextInt(); 
                    Node temp = tree.prev(x);
                    if (temp != null){ 
                        System.out.println("PREV " + x + ": " + temp.info.priority + " - " + temp.info.name + " " + temp.info.surname);
                    }
                    else{
                        System.out.println("PREV " + x + ": BRAK");
                    }
                }
                if (command.equals("PREORDER")){ 
                    String output = "";
                    output = tree.preorder(tree.root);
                    System.out.println(output);
                }
                if (command.equals("INORDER")){ 
                    String output = "";
                    output = tree.inorder(tree.root);
                    System.out.println(output);
                }
                if (command.equals("POSTORDER")){ 
                    String output = "";
                    output = tree.postorder(tree.root);
                    System.out.println(output);
                }
                if (command.equals("HEIGHT")){ 
                    int h = tree.height(tree.root);
                    System.out.println("HEIGHT: " + h);
                }
            }
        }
    }
}

class Person { 
    public int priority;
    public String name;
    public String surname;

    Person (int priority_, String name_, String surname_) { 
        priority = priority_;
        name = name_;
        surname = surname_;
    }

    public String toString() { 
        return "" + priority + " - " + name + " " + surname;
    }
}

class Node { 
    public Person info; 
    public Node left;
    public Node right;

    Node (Person newPerson) { 
        info = newPerson;
        left = null;
        right = null;
    }
}

class Tree { 
    Node root;

    public Tree (){ 
        root = null;
    }

    public Node createPostorder (Person[] arr, int start, int end){ 
        
        if (end < start) return null; 
        if (end == start) return new Node (arr[start]); 
        
        Person rootPerson = arr[end]; 
        Node root = new Node(rootPerson);

        int i;
        for (i = end - 1; i >= start; i--) { 
            if (arr[i].priority <= rootPerson.priority) {
                break;
            }
        }
    
        root.left = createPostorder(arr, start, i); 
        root.right = createPostorder(arr, i + 1, end - 1); 

        return root; 
    }

    public Node createPreorder (Person[] arr, int start, int end) {
        if (end < start) return null; 
        if (end == start) return new Node(arr[start]); 
        
        Person rootPerson = arr[start]; 
        Node root = new Node(rootPerson); 
    
        int x = start + 1; 
        while (x <= end && arr[x].priority <= rootPerson.priority) { 
            x++;
        }
    
        root.left = createPreorder(arr, start + 1, x - 1); 
        root.right = createPreorder(arr, x, end); 
    
        return root; 
    }

    public void clear() { 
        root = null;
    }

    public String postorder(Node temp) { 
        Stack stack1 = new Stack(); 
        Stack stack2 = new Stack(); 
        stack1.push(root); 
        
        StringBuilder output = new StringBuilder();
        output.append("POSTORDER:");

        
        while (!stack1.isEmpty()) {
            Node current = stack1.pop(); 
            stack2.push(current); 
    
            if (current.left != null) { 
                stack1.push(current.left); 
            }
            if (current.right != null) { 
                stack1.push(current.right);
            }
        }
    
        while (!stack2.isEmpty()) { 
            output.append(" ").append(stack2.pop().info.toString()).append(",");

        }

        int n = output.length();
        return output.substring(0, n - 1); 
    }

    public String preorder(Node temp) {
        Stack stack = new Stack(); 
        stack.push(root); 

        StringBuilder output = new StringBuilder();
        output.append("PREORDER:");

        while (!stack.isEmpty()) {
            Node current = stack.pop(); 
            output.append(" ").append(current.info.toString()).append(","); 

            if (current.right != null) { 
                stack.push(current.right);
            }
            if (current.left != null) { 
                stack.push(current.left);
            }
        }
        
        int n = output.length();
        return output.substring(0, n - 1); 
    }

    public String inorder (Node temp) {

        Stack stack = new Stack(); 
        temp = root;
        StringBuilder output = new StringBuilder();
        output.append("INORDER:");
        while (temp != null || stack.isEmpty() != true) {
            if (temp != null) { 
                stack.push(temp); 
                temp = temp.left; 
            }
            else{ 
                temp = stack.pop(); 
                output.append(" ").append(temp.info.toString()).append(","); 
                temp = temp.right; 
            }
        }
        int n = output.length();
        return output.substring(0, n - 1); 

    }

    public int height (Node temp){
        if(temp == null){ 
            return -1;
        }
        int left = height(temp.left); 
        int right = height(temp.right); 

        if (left > right){ 
            return left + 1; 
        } else {
            return right + 1; 
        }
    }

    public void enque (Person p) {
        Node node = new Node(p); 

        if (root == null){ 
            root = node;
            return;
        }

        Node temp = root;
        Node prev = null;

        while (temp != null) { 
            if (p.priority < temp.info.priority){ 
                prev = temp; 
                temp = temp.left; 
            }
            else{ 
                prev = temp;
                temp = temp.right;
            }
        }
        
        if (p.priority < prev.info.priority) {
            prev.left = node;
        } else {
            prev.right = node;
        }
    }

    public Node dequeMax (){
        
        if (root == null){ 
            return null;
        }
        
        Node temp = root; 
        Node prev = null;

        
        while (temp.right != null) { 
            prev = temp;
            temp = temp.right;
        }

        if (prev == null){ 
            root = root.left; 
        }
        else if (prev != null){ 
            prev.right = temp.left; 
        }

        return temp; 
    }

    public Node dequeMin (){
        Node temp = root; 
        Node prev = null;

        if (root == null){ 
            return temp;
        }
        while (temp.left != null) { 
            prev = temp;
            temp = temp.left;
        }

        if (prev == null){ 
            root = root.right; 
        }
        else if (prev != null){ 
            prev.left = temp.right; 
        }
        
        return temp;
    }

    public Node next (int x){
        Node temp = root; 
        Node closest = null; 
        boolean found = false; 
        while (temp != null) {
            if (temp.info.priority > x) { 
                closest = temp; 
                temp = temp.left;
            }
            else if (temp.info.priority < x) { 
                temp = temp.right; 
            }
            else {
                found = true; 
                temp = temp.right;
            }
        }
        if (found) { 
            return closest;
        }
        else {
            return null; 
        }
    }

    public Node prev (int x){ 
        Node temp = root;
        Node closest = null;
        boolean found = false;
        while (temp != null) {
            if (temp.info.priority < x) {
                closest = temp;
                temp = temp.right;
            }
            else if (temp.info.priority > x) {
                temp = temp.left;
            }
            else{
                found = true;
                temp = temp.left;
            }
        }
        if (found){
            return closest;
        }
        else {
            return null;
        }
    }

    public void delete (int x) {
        Node temp = root; 
        Node parent = null; 

        while (temp != null && x != temp.info.priority) { 
            parent = temp; 
            if (x < temp.info.priority){ 
                temp = temp.left; 
            }
            else{
                temp = temp.right;
            }
        }
        if (temp == null){ 
            System.out.println("DELETE " + x + ": BRAK");
            return;
        }

        
        if (temp.left == null && temp.right == null) {
            if (temp != root) { 
                if (parent.left == temp) { 
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null; 
            }
        }
        else if (temp.left != null && temp.right != null) { 
            
            Node sParent = temp; 
            Node s = temp.right; 
    
            while (s.left != null) { 
                sParent = s; 
                s = s.left; 
            }
    
            temp.info = s.info; 
    
            if (sParent != temp) { 
                sParent.left = s.right;
            } else {
                sParent.right = s.right;
            }
        }
        else { 
            Node child; 
            if ((temp.left != null)){ 
                child = temp.left;
            }
            else{
                child = temp.right;
            }
            if (temp != root) { 
                if (temp == parent.left) {  
                    parent.left = child; 
                } else {
                    parent.right = child; 
                }
            } else {
                root = child;
            }
        }
    }
}

class StackNode {
    Node treeNode; 
    StackNode next; 

    StackNode(Node treeNode) { 
        this.treeNode = treeNode;
        this.next = null;
    }
}

class Stack { 

    public StackNode top; 

    public Stack () { 
        this.top = null;
    }

    public boolean isEmpty() { 
        return top == null;
    }

    public Node pop () { 
        if (top != null){
            StackNode temp = top;
            top = top.next;
            return temp.treeNode;
        }
        return null;
    }

    public void push (Node treeNode){ 
    StackNode newNode = new StackNode(treeNode);
    newNode.next = top;
    top = newNode;
    }
}
