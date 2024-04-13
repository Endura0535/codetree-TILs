import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());


        int next = Integer.MAX_VALUE;
        int answer = 0;
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int place = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            if(next >= speed){
                answer++;
                next = speed;
            }
        }

        System.out.println(answer);

    }
}