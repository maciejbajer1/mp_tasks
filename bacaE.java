//searching in 2d array using binary search

import java.util.Scanner;
class Source {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) 
    {
        int z = in.nextInt();
        for (int i = 0; i < z; i++)
        {
            int numOfcols; 
            int numOfrows; 
            numOfrows = in.nextInt(); 
            numOfcols = in.nextInt(); 
            int array[][] = new int [numOfrows][numOfcols]; 
            for (int r = 0; r < numOfrows; r++)
            {
                for (int c = 0; c < numOfcols; c++)
                {
                    int num = in.nextInt(); 
                    array [r][c] = num; 
                }
            }

            int x = in.nextInt(); 
            
            int[] r1 = recFirst(array, x, 0, numOfrows, numOfcols);
            int[] r2 = recLast(array, x, numOfrows - 1, numOfrows, numOfcols);
            int[] x1 = iterFirst(array, x, numOfrows, numOfcols);
            int[] x2 = iterLast(array, x, numOfrows, numOfcols);
            
            
            if ( r1[0] == -1 || r1[1] == -1)
            {
                System.out.println("recFirst: " + x + " missing in array");
            }
            else
            {
                System.out.println("recFirst: " + x + " = a[" + r1[0] + "][" + r1[1] + "]");
            }
            if ( r2[0] == -1 || r2[1] == -1)
            {
                System.out.println("recLast: " + x + " missing in array");
            }
            else
            {
                System.out.println("recLast: " + x + " = a[" + r2[0] + "][" + r2[1] + "]");
            }
            if ( x1[0] == -1 || x1[1] == -1)
            {
                System.out.println("iterFirst: " + x + " missing in array");
            }
            else
            {
                System.out.println("iterFirst: " + x + " = a[" + x1[0] + "][" + x1[1] + "]");
            }

            if ( x2[0] == -1 || x2[1] == -1)
            {
                System.out.println("iterLast: " + x + " missing in array");
            }
            else
            {
                System.out.println("iterLast: " + x + " = a[" + x2[0] + "][" + x2[1] + "]");
            }
            if (i != (z - 1))
            {
                System.out.println("---");
            }
            else
            {
                System.out.print("---");
            }
        } 
    }
    
    private static int[] recFirst(int[][] array, int x, int row, int numOfrows, int numOfCols) {
        
        if (row >= numOfrows) 
        {
            return new int[]{-1, -1}; 
        }

        int[] result = recFirst2(array, x, row, 0, numOfCols - 1); 
        if (result[0] != -1) 
        {
            return result;
        }
    
        return recFirst(array, x, row + 1, numOfrows, numOfCols); 
    }
    
    private static int[] recFirst2(int[][] array, int x, int row, int low, int high) 
    {
        if (low > high) 
        {
            return new int[]{-1, -1}; 
        }
    
        int mid = low + (high - low) / 2; 
        
        
        if ((mid == 0 || x > array[row][mid - 1]) && array[row][mid] == x)  
        {
            return new int[]{row, mid}; 
        } 
        else if (x > array[row][mid]) 
        {
            return recFirst2(array, x, row, mid + 1, high); 
        } 
        else 
        {
            return recFirst2(array, x, row, low, mid - 1); 
        }
    }
    
    private static int[] recLast(int[][] array, int x, int row, int numOfrows, int numOfCols) {
        if (row < 0) 
        {
            return new int[]{-1, -1};  
        }
    
        int[] result = recLast2(array, x, row, 0, numOfCols - 1); 
        if (result[0] != -1)
        {
            return result;
        }
        
        return recLast(array, x, row - 1, numOfrows, numOfCols);
    }

    private static int[] recLast2(int[][] array, int x, int row, int low, int high) {
        if (low > high) 
        {
            return new int[]{-1, -1}; 
        }
    
        int mid = low + (high - low) / 2;
        
        if ((mid == high || x < array[row][mid + 1]) && array[row][mid] == x) 
        {
            return new int[]{row, mid}; 
        } 
        else if (x < array[row][mid])  
        {
            return recLast2(array, x, row, low, mid - 1);
        } 
        else 
        {
            return recLast2(array, x, row, mid + 1, high);
        }
    }
    
    private static int[] iterFirst(int array[][], int x, int numOfrows, int numOfCols) {
    
        int index1 = -1; 
        int index2 = -1;
        
        
        for (int i = 0; i < numOfrows; i++) 
        {
            int low = 0; 
            int high = numOfCols - 1; 
            
            while (low <= high)
            {
                int mid = low + (high - low) / 2; 
                
                if ((mid == 0 || array[i][mid - 1] < x) && array[i][mid] == x) 
                {
                    return new int[]{i, mid}; 
                }
                else if (x > array[i][mid]) 
                {
                    low = mid + 1;
                }  
                else 
                {
                    high = mid - 1;
                }
            }
        }
        return new int[]{index1, index2}; 
    }
     
    private static int[] iterLast(int array[][], int x, int numOfrows, int numOfCols) {
    
        int index1 = -1;
        int index2 = -1;
        
        
        for (int i = numOfrows - 1 ; i >= 0; i--)
        {
            int low = 0;
            int high = numOfCols - 1; 
            while (low <= high)
            {
                int mid = low + (high - low) / 2; 
                
                if ((mid == numOfCols - 1 || array[i][mid + 1] > x) && array[i][mid] == x) 
                {
                    return new int[]{i, mid}; 
                }
                else if (x < array[i][mid]) 
                {
                    high = mid - 1;
                }  
                else 
                {
                    low = mid + 1;
                }
            }
        }
        return new int[]{index1, index2}; 
    }
}