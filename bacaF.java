//Iterative and recursive version of something similar to subset sum problem.

import java.util.Scanner;
public class Source 
{   
    static boolean found = false; 
    public static void main(String[] args)
    {
        int z = in.nextInt();
        for (int i = 0; i < z; i++) 
        {
            int capacity = 0;
            int numOfelements = 0;
            capacity = in.nextInt(); 
            numOfelements = in.nextInt(); 
            int[] array = new int[numOfelements];
            boolean[] out = new boolean[numOfelements]; 
            for (int i2 = 0; i2 < numOfelements; i2++) 
            {
                int element = in.nextInt();
                array[i2] = element;
            }
            recPackage(capacity, 0, array, out, 0); 

            if (found == true)
            {
                StackSimPackage(capacity, array, numOfelements); 
            }
            else {
                System.out.println("BRAK"); 
            }
            found = false; 
            found1 = false;
        
        }
    }

    static boolean found1 = false;

    public static String display(boolean[] out, int[] array) {
        return displayRecursive(out, array, 0, "");
    }
    private static String displayRecursive(boolean[] out, int[] array, int index, String output) {
        if (index == out.length) {
            return output; 
        }
    
        if (out[index] == true) {
            output += array[index] + " "; 
        }
    
        return displayRecursive(out, array, index + 1, output); 
    }
    public static void recPackage(int capacity, int currentSum, int[] array, boolean[] out, int index) {
        if (currentSum == capacity) 
        {
            found1 = true; 
            found = true;
            String output = display(out, array);
            StringBuilder outputREC = new StringBuilder(); 
            outputREC.append("REC: ").append(capacity).append(" = ").append(output.trim());
            System.out.println(outputREC); 
            return; 
        }

        if (currentSum > capacity || index >= array.length) 
        {
            return;
        }

        if (currentSum + array[index] <= capacity) 
        {
            out[index] = true; 
            recPackage(capacity, currentSum + array[index], array, out, index + 1); 
            if (found1) 
            {
                return;
            }
            out[index] = false; 
        }

        recPackage(capacity, currentSum, array, out, index + 1); 
    }

    public static class CustomObject 
    {
        int customIndex; 
        int customSum; 
    
        public CustomObject (int i, int s) 
        {
            customIndex = i;
            customSum = s;
        }
    }
    
    public static class CustomStack 
    {
        int topIndex; 
        CustomObject[] objectArray; 
    
        public CustomStack (int x) 
        {
            objectArray = new CustomObject[x];
            topIndex = -1;
        }
    
        public CustomObject pop () 
        {
            CustomObject tempObject = objectArray[topIndex];
            topIndex--;
            return tempObject;
        }
        public void pushItem(int i, int s) 
        {
            CustomObject newCustomObject = new CustomObject(i,s);
            topIndex++;
            objectArray[topIndex] = newCustomObject;
        }
        public boolean isStackEmpty () 
        {
            return (topIndex == -1);
        }
        public CustomObject peekTop () 
        {
            CustomObject tempObject = objectArray[topIndex];
            return tempObject;
        }
    }
    
    
    static void StackSimPackage (int capacity, int[] array, int numOfelements) {

        CustomStack customStack = new CustomStack(5000); 
        int currentIndex = 0; 
        int currentSum = 0; 
        boolean found2 = false; 
        boolean skipFirst = false; 
    
        while (true) 
        {
            if (currentSum == capacity) 
            {
                found2 = true; 
                break;
            }
            if (currentSum > capacity || currentIndex >= numOfelements) 
            {
    
                if (currentIndex == numOfelements && customStack.isStackEmpty()) 
                {
                    if (!skipFirst) 
                    {
                        skipFirst = true;
                        continue;
                    } 
                    else 
                    {
                        System.out.println("BRAK"); 
                        break;
                    }
                }
    
                while(true) 
                {
                    CustomObject popped = customStack.pop(); 
                    currentIndex = popped.customIndex; 
                    currentSum -= popped.customSum; 
                    if (popped.customSum != 0) 
                    {
                        currentIndex++; 
                        break;
                    }
                }  
            } 
            else 
            {
                if (currentSum + array[currentIndex] <= capacity) 
                {
                    customStack.pushItem(currentIndex, array[currentIndex]); 
                    currentSum += array[currentIndex]; 
                    currentIndex++; 
                }
                else 

                {
                    customStack.pushItem(currentIndex, 0);
                    currentIndex++; 
                }
            }
        }
        if (found2) 
        {
            StringBuilder output2 = new StringBuilder(); 
            String out2 = "";
            for (int i = 0; i <= customStack.topIndex; i++) 
            {
                CustomObject temp = customStack.objectArray[i]; 
                if (temp.customSum != 0) 
                {
                   out2 += " " + temp.customSum; 
                }
            }
            output2.append("ITER: ").append(capacity).append(" = ").append(out2.trim()); 
            System.out.println(output2);
        }
    }
}