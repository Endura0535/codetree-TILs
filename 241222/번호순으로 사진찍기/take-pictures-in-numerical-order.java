import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Point(Math.min(a, b), Math.max(a, b)));
        }
        Collections.sort(list, (o1, o2) -> {
            if (o1.y == o2.y)
                return o1.x - o2.x;
            return o1.y - o2.y;
        });

        int answer = 1;
        int idx = 0;
        for (Point now : list) {
            if (now.x <= idx) {
                idx = now.y - 1;
                continue;
            }
            idx = now.y - 1;
            answer++;
        }

        System.out.println(answer);
    }
}
