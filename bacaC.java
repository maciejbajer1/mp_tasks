//conversion from ONP to INF and INF to ONP
import java.util.Scanner;
public class Source 
{
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        int numOfSets = Integer.parseInt(in.nextLine()); 
        for (int i = 0; i < numOfSets; i++) 
        {
            String input = in.nextLine(); 
            char type2 = input.charAt(0);
            if (type2 == 'I') 
            {
                String clearedONP = clear(input); 
                String outputONP = toONP(clearedONP); 
                System.out.print("ONP: ");
                String outpucikONP = "";
                if (outputONP != "error")  
                {
                    outpucikONP = format(outputONP); 
                }
                else
                {
                    outpucikONP = "error";
                }
                System.out.println(outpucikONP);
            }
            else  
            {   
                String clearedINF = clear2(input);
                System.out.print("INF: ");
                String o = toINF(clearedINF);
                String outpucikINF = "";
                if (o != "error") 
                {
                    outpucikINF = format(o); 
                }
                else
                {
                    outpucikINF = "error";
                }
                System.out.println(outpucikINF);
            }
        }
   }

    
    public static class Stack 
    {
        
        char[] tab;  
        int maxSize = 256; 
        int top; 

        public Stack() 
        {
            tab = new char[maxSize];
            top = 0;
        }

        public void push(char x) 
        {
            top++; 
            tab[top] = x; 
        }

        public char pop()  
        {
            char x = tab[top];
            top--;
            return x;
        }

        public char peek() 
        {
            char x = tab[top];
            return x;
        }

        public boolean isEmpty() 
        { 
            if (top == 0) return true;
            return false;
        }        
    }
    
    
    private static boolean isLetter(char x)
    {
        if (x >= 'a' && x <= 'z') 
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    
    static int prio(char ch)
    {
        if (ch == '=')
            return 1;
        else if (ch == '|')
            return 2;
        else if (ch == '&')
            return 3;
        else if (ch == '>' || ch == '<')
            return 4;
        else if (ch == '+' || ch == '-')
            return 5;
        else if (ch == '*' || ch == '/' || ch == '%')
            return 6;
        else if (ch == '^')
            return 7;
        else if (ch == '!' || ch == '~')
            return 8;
        else if (isLetter(ch))
            return 9;
        else
            return -1;
    }
     
    
    static boolean left(char ch) 
    {
        if (ch == '|' || ch == '&' || ch == '>' || ch == '<' || ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '%' || ch == '(' || ch == ')')
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    
    static boolean right(char ch) 
    {
        if (ch == '=' || ch == '^' || ch == '!' || ch == '~')
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    
    static String toONP (String input)
    {
        Stack stack = new Stack();  
        String output = new String(""); 
 
        int state = 0; 
        int count = 0; 

        for (int i = 0; i < input.length(); ++i) 
        {
            
            char x = input.charAt(i);
            
            
            if (state == 0) 
            {
                
                if (isLetter(x)) state = 1;
                else if (x == '~' || x == '!') state = 2;
                else if (x == '(') 
                {
                    state = 0;
                    count++;
                }
                else 
                {
                    output = "error";
                    return output;
                }
            }
            else if (state == 1) 
            {
                
                if (x == ')') 
                {
                    state = 1;
                    count--;
                }
                else if (x == '|' || x == '&' || x == '>' || x == '<' || x == '+' || x == '-' || x == '/' || x == '*' || x == '%' || x == '=' || x == '^') state = 0;
                else
                {
                    output = "error";
                    return output;
                }
            }
            else if (state == 2) 
            {
                
                if (x == '(')
                {
                    state = 0;
                    count++;
                }
                else if (isLetter(x)) state = 1;
                else if (x == '~' || x == '!') state = 2;
                else
                {
                    output = "error";
                    return output;
                }
            }
            if (count < 0) 
            {
                output = "error";
                return output;
            }

            
            
            if (isLetter(x))
            {
                output += x; 
            }
            
            
            else if (x == '(')
            {
                stack.push(x); 
            }
         
            
            else if (x == ')')
            {
                
                while (!stack.isEmpty() && stack.peek() != '(') 
                {
                    output += stack.pop();
                }
                stack.pop();
            }
            else 
            {
                
                
                while (!stack.isEmpty() && prio(x) <= prio(stack.peek()) && left(x))
                {
                    output += stack.pop(); 
                }
                
                
                while (!stack.isEmpty() && prio(x) < prio(stack.peek()) && right(x))
                {
                    output += stack.pop(); 
                }
                stack.push(x); 
            }
        } 

        if (count != 0) 
        {
            output = "error";
            return output;
        }

        
        while (!stack.isEmpty()) 
        {
            output += stack.pop();
        }
        return output;
    }

    
    public static String clear (String input)
    {
        StringBuilder changed = new StringBuilder();
        for (int i = 0; i < input.length(); i++) 
        {
            char x = input.charAt(i); 
            
            
            if (isLetter(x) || "()+-=!~^*&|<>/%".indexOf(x) != -1)
            {
                changed.append(x);
            }
        }
        
        return changed.toString();
    }

    
    public static String clear2 (String input)
    {
        StringBuilder changed = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
        {
            char x = input.charAt(i);
            if (isLetter(x) || "+-=!~^*&|<>/%".indexOf(x) != -1)
            {
                changed.append(x);
            }
        }
        return changed.toString();
    }
   
    
    public static String format(String input) 
    {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++)  
        {
            output.append(input.charAt(i)); 
            if (i != input.length() - 1) 
            {
                output.append(" "); 
            }    
        }
        return output.toString();
    }
    
    public static String toINF (String input)
    {
       
        int l = 0; 
        char [] x = input.toCharArray(); 
        StackString stack = new StackString(x.length); 
        StackInt stack2 = new StackInt(x.length); 
        String tmp; 
        for (int i = 0; i < x.length; i++) 
        {
            char c = x[i]; 

            if (isLetter(c))  
            {
                stack.push(c); 
                stack2.push(prio(c)); 
                l++; 
            } 
            else 
            {
                tmp = ""; 
                if ((c != '~') && (c != '!')) 
                {
                    l--; 
                    if (l <= 0)  
                    {
                    return "error"; 
                    }
                    if (c == '=' || c == '^') 
                    {
                        if (stack2.peek() < prio(c)) 
                                                     
                        { 
                            tmp = "(" + stack.pop() + ")"; 
                        } 
                        else 
                        {
                            tmp = stack.pop(); 
                        }
                    } 
                    else  
                    {
                        if (stack2.peek() <= prio(c))  
                        { 
                            tmp = "(" + stack.pop() + ")"; 
                        } 
                        else 
                        {
                            tmp = stack.pop(); 
                        }
                    }

                    stack2.pop();
                    String currentTmp = tmp; 
                    tmp = ""; 
                    
                    if (c == '=' || c == '^') 
                    {
                        if (stack2.peek() <= prio(c)) 
                        {
                            tmp = "(" + stack.pop() + ")" + c + currentTmp; 
                        } 
                        else 
                        {
                            tmp = stack.pop() + c + currentTmp; 
                        }
                    } 
                    else 
                    {
                        if (stack2.peek() < prio(c))
                        {
                            tmp = "(" + stack.pop() + ")" + c + currentTmp; 

                        }
                        else
                        {
                            tmp = stack.pop() + c + currentTmp; 

                        }
                    }
                } 
                else 
                {
                    if (stack2.peek() < prio(c)) 
                    {
                        tmp = c + "(" + stack.pop() + ")";

                    } 
                    else 
                    {
                        tmp = c + stack.pop();

                    }
                }
                stack2.pop();
                stack.push(tmp.toString()); 
                stack2.push(prio(c)); 
            }
        }
        if (l != 1) 
        {
            return "error";
        }
        String out = stack.pop(); 
        return out;
        
    }

    
    
    public static class StackInt 
    {
        int[] tab; 
        int size; 
        int top;

        public StackInt(int s) 
        {
            size = s;
            tab = new int[s];
            top = -1;
        }

        public void push(int x) 
        {
            top++;
            tab[top] = x;
        }

        public int pop() 
        {
            if (nonEmpty()) 
            {
                int x = tab[top];
                top--;
                return x;
            } else
            {
                return 0;
            }
        }

        public int peek() 
        {
            int x = tab[top];
            return x;
        }     
        boolean nonEmpty()     
        { 
            return (top >= 0);
        }
    }

    
    
    public static class StackString
    {
        String[] tab; 
        int size; 
        int top;

        public StackString(int s) 
        {
            size = s;
            tab = new String[s];
            top = -1;
        }

        public void push(String x) 
        {
            top++;
            tab[top] = x;
        }

        public void push(char x) 
        {
            top++;
            tab[top] = Character.toString(x);
        }

        public String pop() 
        {
            if (nonEmpty()) {
                String x = tab[top];
                top--;
                return x;
            } else {
                return "";
            }
        }

        public String peek() 
        {
            String x = tab[top];
            return x;
        }
        boolean nonEmpty() 
        {
        return (top >= 0);
        }
    
    } 

}
