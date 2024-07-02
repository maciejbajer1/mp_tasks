//Kadane algorithm on 2d array
import java.util.Scanner;
class Source
{
    static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args)
    {
        int z = scanner.nextInt(); 

        for (int i = 0; i < z; i++) 
        {
            int numOfz; 
            int numOfcols; 
            int numOfrows; 
            numOfz = scanner.nextInt(); 
            scanner.next(); 
            numOfrows = scanner.nextInt(); 
            numOfcols = scanner.nextInt(); 
            boolean allNeg = true; 

            int array[][] = new int [numOfrows][numOfcols]; 

            
            for (int r = 0; r < numOfrows; r++) 
            {
                for (int c = 0; c < numOfcols; c++) 
                {
                    int intiger = scanner.nextInt(); 
                    if (intiger > 0) 
                    {
                        array[r][c] = (intiger * 3); 
                    }
                    else 
                    {
                        array[r][c] = (intiger * 2); 
                    }
                    if (array[r][c] > 0) 
                    {
                        allNeg = false;
                    }
                    if (array[r][c] == 0) 
                    {
                        allNeg = false;                        
                    } 
                }
            }

            if (allNeg) 
            {
                System.out.println(numOfz + ": ms_tab is empty");
            }
            else 
            {
                
                System.out.print(numOfz); 
                kadane(array); 
            }
        }
    }
    public static void kadane (int array[][]) 
    {
        int maxSum = array[0][0]; 
        int maxL = 0; 
        int maxR = 0; 
        int maxB = 0; 
        int maxT = 0; 
        int temp [] = new int[array.length]; 
        int x = 0; 
        boolean leksykograficzny = false; 
        int maxE = 0; 
        int s = 0; 

        
        for (int l = 0; l < array[0].length; l++) 
        {
            for (int r = l; r < array[0].length; r++) 
            {
                for (int row1 = 0; row1 < array.length; row1++) 
                {
                    temp[row1] += array[row1][r]; 

                    if (array[row1][r] > maxSum) 
                    {   
                        x = (r - l + 1); 
                        maxSum = array[row1][r]; 
                        
                        maxL = l; 
                        maxR = r;
                        maxT = row1;
                        maxB = row1;

                    }
                }
                maxE = 0; 
                s = 0; 
                for (int row = 0; row < array.length; row++) 
                {
                    maxE += temp[row]; 

                    if (maxE > maxSum) 
                    {
                       
                        maxSum = maxE; 
                        maxL = l; 
                        maxR = r; 
                        maxT = s; 
                        maxB = row; 
                        x = (r - l + 1) * (row - s + 1); 

                    }
                    else if (maxE == maxSum) 
                    {
                        int el = (r - l + 1) * (row - s + 1);
                        if (el < x) 
                        {   
                            
                            maxSum = maxE;
                            maxL = l;
                            maxR = r;
                            maxT = s;
                            maxB = row;
                            x = (r - l + 1) * (row - s + 1);
                        }
                        
                        
                        if (s < maxT) 
                        {
                            leksykograficzny = true;
                        } 
                        else if (maxT < s)
                        {
                            leksykograficzny = false;
                        }
                        if (s == maxT)
                        {
                            if (row < maxB)
                            {
                                leksykograficzny = true;
                            }
                            else if (maxB < row)
                            {
                                leksykograficzny = false;
                            }
                            if (row == maxB)
                            {
                                if (l < maxL)
                                {
                                    leksykograficzny = true;
                                }
                                else if (maxL < l)
                                {
                                    leksykograficzny = false;
                                }
                                if (l == maxL)
                                {
                                    if (r < maxR)
                                    {
                                        leksykograficzny = true;
                                    }
                                    else if (maxR < r)
                                    {
                                        leksykograficzny = false;
                                    }
                                    else
                                    {
                                        leksykograficzny = false;
                                    }
                                }
                            }
                        }
                        
                        else if ((el == x) && leksykograficzny)
                        {
                            
                            maxSum = maxE;
                            maxL = l;
                            maxR = r;
                            maxT = s;
                            maxB = row;
                            x = (r - l + 1) * (row - s + 1);
                        }
                    } 
                    if (maxE <= 0) 
                    {
                        maxE = 0; 
                        s = row + 1; 
                    }
                    if (r == array[0].length - 1) 
                    {
                        temp[row] = 0; 
                    }
                }
               
            }
        }
        
        System.out.println(": " + "ms_tab = " + "a[" + maxT + ".." + maxB+ "][" + maxL + ".." + maxR + "], " + "msum=" + maxSum);
    }
}