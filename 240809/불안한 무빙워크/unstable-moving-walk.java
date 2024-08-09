import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Deque<Point> upBelt = new ArrayDeque<>();
        Deque<Integer> downBelt = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            upBelt.addLast(new Point(Integer.parseInt(st.nextToken()), 0));
        }

        for (int i = 0; i < n; i++) {
            downBelt.addFirst(Integer.parseInt(st.nextToken()));
        }

        int answer = 0;
        while (countZero(upBelt, downBelt) < k) {
            answer++;
            step1(upBelt, downBelt);
            if (countZero(upBelt, downBelt) >= k)
                break;

            step2(upBelt, downBelt);
            if (countZero(upBelt, downBelt) >= k) {
                break;
            }
            step3(upBelt, downBelt);
        }

        System.out.println(answer);
    }

    public static void step1(Deque<Point> upBelt, Deque<Integer> downBelt) {
        upBelt.addFirst(new Point(downBelt.pollFirst(), 0));
        downBelt.addLast(upBelt.pollLast().x);
        if (upBelt.peekLast().y == 1)
            upBelt.peekLast().y = 0;
    }

    public static void step2(Deque<Point> upBelt, Deque<Integer> downBelt) {
        int size = upBelt.size();
        for (int i = 0; i < size - 1; i++) {
            Point p = upBelt.pollFirst();
            if (p.y == 1 && upBelt.peekFirst().x > 0) {
                upBelt.peekFirst().x--;
                upBelt.peekFirst().y = 1;
            }
            upBelt.addLast(p);
        }
        if (upBelt.peekFirst().y == 1)
            upBelt.addLast(new Point(upBelt.pollFirst().x, 0));
    }

    public static void step3(Deque<Point> upBelt, Deque<Integer> downBelt) {
        if (upBelt.peekFirst().x > 0) {
            upBelt.peekFirst().x--;
            upBelt.peekFirst().y = 1;
        }
    }

    public static int countZero(Deque<Point> upBelt, Deque<Integer> downBelt) {
        int cnt = 0;
        for (Point p : upBelt) {
            if (p.x == 0) {
                cnt++;
            }
        }
        for (int i : downBelt) {
            if (i == 0) {
                cnt++;
            }
        }
        return cnt;
    }
}