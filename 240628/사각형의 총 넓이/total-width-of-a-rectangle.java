import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        boolean[][] map = new boolean[10001][10001];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int x = x1; x < x2; x++){
                for(int y = y2; y < y1; y++){
                    map[y][x] = true;
                }
            }
        }

        int answer = 0;
        for(int x = 0; x <= 10000; x++){
            for(int y = 0; y <= 10000; y++){
                if(map[y][x])
                    answer++;
            }
        }

        System.out.println(answer);
        
    }
}