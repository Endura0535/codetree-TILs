import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (map.containsKey(1))
                map.put(1, map.get(1) + C);
            else map.put(1, C);

            if (map.containsKey(s))
                map.put(s, map.get(s) + G - C);
            else map.put(s, G - C);

            if (map.containsKey(e))
                map.put(e, map.get(e) + H - G);
            else map.put(e, H - G);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int answer = 0;
        int pre = 0;
        for (int key : list) {
            pre += map.get(key);
            answer = Math.max(answer, pre);
        }

        System.out.println(answer);
    }
}