//Quick sort but without recursion, without stack. So that time complexity is O(n * log2 n) and space complexity O(1).

import java.util.Scanner;
public class Source {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        int z = in.nextInt(); 
        for (int i = 0; i < z; i++)
        {
            int numOfelements = in.nextInt(); 
            long[] array = new long[numOfelements]; 
            for (int j = 0; j < numOfelements; j++){ 
                array[j] = in.nextLong(); 
            }
            quickSort(array); 
           
            for (int k = 0; k < numOfelements; k++){
                    System.out.print(array[k] + " "); 
            }
            System.out.println();
        }
    }
    
    private static int median (long[] array, int a, int c) {
        int b = (a + c) / 2;
        if ((array[a] < array[b] && array[b] < array[c]) || (array[c] < array[b] && array[b] < array[a]))
            return b; 
        else if ((array[b] < array[a] && array[a] < array[c]) || (array[c] < array[a] && array[a] < array[b]))
            return a; 
        else
            return c; 
    }

    private static void InsertionSort (long[] array, int left, int right){
        for (int i = left + 1; i < right; i++) 
        {
            long temp = array[i]; 
            int j = i - 1;
            while (j >= left && temp < array[j]) 
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j+1] = temp; 
        }
    }
    
    
    
    public static void quickSort(long[] arr) {
        
        
        int low = 0; 
        int high = arr.length - 1; 
        long q = 0; 
        int i = 0; 
        int temp = high; 
        int size = 0; 

        while (true){
            i--;
            size = temp - low + 1; 
            while (low < temp) 
            {
                size = temp - low + 1;

                if (size <= 20) 
                {
                    InsertionSort(arr, low, temp + 1);
                    low = temp; 
                }
                else 
                {
                    q = partition(arr, low, temp); 

                    int max = low; 
                    for (int j = low + 1; j <= (int)(q - 1); j++) 
                    {
                    if (arr[j] > arr[max])
                    {
                        max = j;
                    }
                }
                long x = arr[max];
                arr[max] = arr[(int)(q - 1)];
                arr[(int)(q - 1)] = arr[(int)q];
                arr[(int)q] = arr[high];
                arr[high] = x;

                temp = (int)(q - 1); 

                i++;
                }
               
            }
            if (i < 0 || (low == arr.length - 1)) 
            {
                break;
            }
            low++;  
            temp = findHigh(arr, low); 

            
            long z = arr[high]; 
            arr[high] = arr[low];
            arr[low] = arr[low - 1];
            arr[low - 1] = z;
        }
	}

    public static int findHigh (long[] arr, int low){
        for(int i = low; i < arr.length; i++) 
        {
            if (arr[i] < arr[low - 1])
            {
                return i; 
            }
        }
        return arr.length - 1; 

    }
    
    public static long partition(long[] arr, int left, int right) 
    {
        int x = median(arr, left, right); 
        long z = arr[x];
        arr[x] = arr[right]; 
        arr[right] = z;

        long pivot = arr[right]; 
        int i = left - 1; 
        for (int j = left; j < right; j++) 
        {
            if (arr[j] <= pivot)  
            {
                i++;
                long temp = arr[i]; 
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        long temp = arr[i + 1]; 
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1; 
    }
}