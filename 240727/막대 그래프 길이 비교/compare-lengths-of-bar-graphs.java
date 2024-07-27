import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i <= n; i++){
            int x = Integer.parseInt(st.nextToken());
            stack.push(x);
            Stack<Integer> newStack = new Stack<>();
            int y = 0;
            for(int j = i; j >= 0; j--){
                if(j == 0){
                    sb.append(j).append(" ");
                    break;
                }
                y = stack.pop();
                newStack.push(y);
                if(y > x){
                    sb.append(j).append(" ");
                    break;
                }
            }
            while(!newStack.isEmpty()){
                stack.push(newStack.pop());
            }
        }
        
        System.out.println(sb);
    }
}