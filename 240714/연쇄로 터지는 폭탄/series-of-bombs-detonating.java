import java.util.*;
import java.io.*;

public class Main {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        int answer = 0;

        for(int i = 0; i < list.size(); i++){
            answer = Math.max(answer, boom(list.get(i), i, 1).size());
        }
        System.out.println(answer);
    }

    static Set<Integer> boom(int x, int idx, int range){

        Set<Integer> set = new HashSet<>();
        set.add(x);
        for(int i = idx - 1; i >= 0 ; i--){
            if(list.get(i) >= x - range)
                set.addAll(boom(list.get(i), i, range + 1));
            else
                break;
        }
        for(int i = idx + 1; i < list.size() ; i++){
            if(list.get(i) <= x + range)
                set.addAll(boom(list.get(i), i, range + 1));
            else
                break;
        }

        return set;
    }
}