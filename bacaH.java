//magic fives selection algorithm

import java.util.Scanner;
public class Source {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int numOfsets = in.nextInt(); 
        for (int i = 0; i < numOfsets; i++){
            int numOfelements = in.nextInt(); 
            int[] array = new int[numOfelements]; 
            for (int j = 0; j < numOfelements; j++){
                array[j] = in.nextInt(); 
            }
            int m = in.nextInt(); 
            for (int z = 0; z < m; z++){
                int k = in.nextInt(); 
                if (k > 0 && k <= numOfelements) {
                    System.out.println(k + " " + kth(array, 0, numOfelements, k - 1)); 
                } else {
                    System.out.println(k + " " + "brak");
                }
            }
        }
    }
    
    static int kth(int[] array, int low, int high, int k) {
        
        if (high - low - 1 <= 0) 
        {
            return array[low];
        }

        int i = 0;
        int count = 0; 
        int end = 0; 
        for (int j = low; j < high; j += 5) 
        { 
            if (j + 5 < high){
                end = j + 5; 
            } 
            else{
                end = high; 
            }
            insertionSort(array, j, end); 
            int temp = array[low + count]; 
            array[low + count] = array[(j + end) / 2];
            array[(j + end) / 2] = temp;
            i++;
            count++; 
        }
        int M = 0;
    
        M = kth(array, low, low + count, (count + 1) / 2); 


        int z = 0; 
        for (z = 0; z < high; z++) 
        {
            if (array[z] == M){
                break;
            }
        }
        
        int temp = array[z]; 
        array[z] = array[high - 1];
        array[high - 1] = temp;

        int pivot = partition(array, low, high - 1); 

        
        int indexOfM = low;
        while (array[indexOfM] < M){
            indexOfM++;
        }
        int smallerThanM = indexOfM - 1; 

        
        if (k <= smallerThanM - low){ 
            return kth(array, low, smallerThanM + 1, k);
        }
        else if (k <= pivot - low){ 
            return M;
        }
        else{ 
            return kth(array, pivot + 1, high, k - (pivot - low + 1));
        }
    }

    static void insertionSort (int[] array, int low, int high){
        for (int i = low + 1; i < high; i++) 
        {
            int temp = array[i]; 
            int j = i - 1;
            while (j >= low && temp < array[j]) 
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp; 
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high]; 
        int curr = low;
        while (curr <= high) {
            if (array[curr] < pivot)  
            {
                int temp = array[low];
                array[low] = array[curr];
                array[curr] = temp;
                low++;  
                curr++; 
            } else if (array[curr] > pivot) 
            {
                int temp = array[curr];
                array[curr] = array[high];
                array[high] = temp;
                high--; 
            } else {
                curr++;
            }
        }
        return high; 
    }
}