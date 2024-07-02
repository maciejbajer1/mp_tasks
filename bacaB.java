//counting possible triangles using binary search
import java.util.Scanner;
class Source 
{
    public static Scanner scanner = new Scanner(System.in); 
    public static int T[];
    public static int numOfSegments;
    public static void main(String[] args) throws Exception
    {
        int numOfSets = scanner.nextInt(); 
        
        for (int i = 1; i < numOfSets + 1; i++) 
        {
            Source array = new Source(); 
            numOfSegments = scanner.nextInt(); 
            T = new int[numOfSegments]; 

            for (int j = 0; j < numOfSegments; j++) 
            {
                int segment = scanner.nextInt(); 
                T[j] = segment; 
            }

            for (int k = 0; k < (T.length - 1); k++)  
            { 
                int min = k; 
                for (int j = k + 1; j < T.length; j++) 
                {
                    if (T[j] < T[min]) 
                    {
                        min = j;
                    } 
                } 
                int temp = T[min]; 
                T[min] = T[k]; 
                T[k] = temp; 
            } 
            String coutcik = ""; 
            int coutCount = 0; 
            int numOfTriangles2 = 0; 
            int numOfTriangles = 0; 
            for (int i2 = 0; i2 < (T.length - 2); i2++) 
            {
                for (int j2 = i2 + 1; j2 < (T.length - 1); j2++) 
                {
                    int sum = T[i2] + T[j2]; 
                    
                    int k2 = array.SearchBinFirst(sum); 
                    if (k2 > j2) numOfTriangles = numOfTriangles + k2 - j2 - 1; 
                    if (numOfTriangles > numOfTriangles2 && coutCount < 10 && k2 > j2) 
                    {
                        int ileRazy = numOfTriangles - numOfTriangles2; 
                        while(j2 + 1 <= k2 - 1 && 0 < ileRazy && coutCount < 10) 
                        {
                            coutcik += ("(" + i2 + "," + j2 + "," + (k2 - ileRazy) + ") ");
                            ileRazy--;
                            coutCount++;
                        }
                    }
                    numOfTriangles2 = numOfTriangles;
                }
            }


            System.out.println(i + ": n= " + numOfSegments); 
            
            
            int licznik = 0; 
            for (int x = 0; x < numOfSegments; x++)
            {
                System.out.print(T[x] + " ");
                licznik++;
                if ((licznik == 25) || (licznik == 50) || (licznik == 75) || (licznik == 100)) 
                {
                    System.out.println();
                }
            }
            if ((licznik != 25) && (licznik != 50) && (licznik != 75) && (licznik != 100)) 
            {
                System.out.println();
            }

            
            if (numOfTriangles > 0)
            {
                System.out.println(coutcik);
                
            } 
            
            System.out.println("Total number of triangles is: "  + numOfTriangles); 
        }
    }
    
    public int SearchBinFirst(int x)
    {
        int left = 0;
        
        int right = numOfSegments - 1; 
        while (left <= right) 
        {
            int mid = (left + right) / 2; 
            if (T[mid] >= x) 
            {
                right = mid - 1; 
            }
            else 
            {
                left = mid + 1; 
            }
               
        }
        return left; 
    }
}

