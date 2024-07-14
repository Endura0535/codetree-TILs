import java.util.*;
import java.io.*;

public class Main {

    static List<Integer> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        int answer = 0;

        for(int i = 0; i < list.size(); i++){
            visited = new boolean[N];
            int cnt = 0;
            boom(list.get(i), i, 1);
            for(int j = 0; j < N; j++){
                if(visited[j]){
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }

    static void boom(int x, int idx, int range){
        visited[idx] = true;

        for(int i = idx - 1; i >= 0 ; i--){
            if(list.get(i) >= x - range && !visited[i]){
                visited[i] = true;
                boom(list.get(i), i, range + 1);
            }
            else
                break;
        }
        for(int i = idx + 1; i < list.size() ; i++){
            if(list.get(i) <= x + range && !visited[i]){
                visited[i] = true;
                boom(list.get(i), i, range + 1);
            }
            else
                break;
        }
    }
}