import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        ArrayList<Point> list = new ArrayList<>();

        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int location = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            list.add(new Point(location, speed));
        }

        int speed = Integer.MAX_VALUE;
        int answer = 0;

        for(int i = t - 1; i >= 0; i--){
            Point p = list.get(i);
            if(p.y <= speed){
                answer++;
                speed = p.y;
            }
        }

        System.out.println(answer);
    }
}