//Program to merge n sorted sequences of integers into one sorted sequence using a min-heap in O(m * n log2 n).

import java.util.Scanner;
public class Source {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        int numOfsets = in.nextInt();
        
        for (int z = 0; z < numOfsets; z++){
            int numOfArrays = in.nextInt(); 
            int [] lengths = new int[numOfArrays]; 
            for (int i = 0; i < numOfArrays; i++){
                lengths[i] = in.nextInt(); 
            }

            int [][] array = new int[numOfArrays][]; 
            for (int j = 0; j < numOfArrays; j++){ 
                array[j] = new int[lengths[j]]; 
                for (int k = 0; k < lengths[j]; k++){
                    array[j][k] = in.nextInt(); 
                }
            }
            merge(lengths, array); 
        }   
    }
    
    static class Element { 
        int value; 
        int arrayIndex; 
        int elementIndex; 

        public Element(int v, int a, int e) { 
            value = v;
            arrayIndex = a;
            elementIndex = e;
        }
    }

    static class Heap { 
        public Element[] heap;
        public int size;

        public Heap (int cap) { 
            heap = new Element[cap];
            size = 0;
        }

        public boolean isEmpty (){ 
            if (size == 0){
                return true;
            }
            else{
                return false;
            }
        }

        public Element pop() { 
            if (isEmpty()) {
                return null;
            }
            Element min = heap[0]; 
            Element temp = heap[size - 1]; 
            heap[0] = temp; 
            size--; 
            heapify(0); 
            return min;
        }

        public void insert(Element element) { 
            heap[size] = element; 
            int current = size; 
            int parent = ((current - 1) / 2); 
            while (current > 0 && heap[current].value < heap[parent].value) { 
                Element temp = heap[current];
                heap[current] = heap[parent];
                heap[parent] = temp;

                current = parent; 
                parent = ((current - 1) / 2);
            }
            size++;
        }

        private void heapify(int index) { 
            int min = index; 
            int left = ((2 * index) + 1); 
            int right = ((2 * index) + 2); 
            boolean swapped = false; 

            if (left < size && heap[left].value < heap[min].value) { 
                min = left;
                swapped = true;

            }
            if (right < size && heap[right].value < heap[min].value) { 
                min = right;
                swapped = true;
            }
            if (swapped) { 
                Element temp = heap[index]; 
                heap[index] = heap[min];
                heap[min] = temp;

                heapify(min);
            }
        }
    }

    static void merge (int[] lengths, int[][] array) { 
        int size = 0;
        for (int i = 0; i < lengths.length; i++){
            size += lengths[i]; 
        }
        int[] output = new int[size];

        Heap heap = new Heap(lengths.length); 

        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] > 0) {
                Element element = new Element (array[i][0], i, 0);
                heap.insert(element); 
            }
        }

        int i = 0;
        while (!heap.isEmpty()) { 
            Element min = heap.pop(); 
            output[i] = min.value; 
            i++;
            if (min.elementIndex + 1 < lengths[min.arrayIndex]) 
            {
                Element element = new Element( array[min.arrayIndex][min.elementIndex + 1], min.arrayIndex, min.elementIndex + 1);
                heap.insert(element); 
            }
        }

        for (int i2 = 0; i2 < size; i2++){ 
            System.out.print(output[i2] + " ");
        }
        System.out.println();
    }
}