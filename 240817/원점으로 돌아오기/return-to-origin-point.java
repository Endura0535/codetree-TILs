import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N;
    static int answer = 0;
    static Point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        
        xMap.put(0, new ArrayList<>());
        yMap.put(0, new ArrayList<>());
        xMap.get(0).add(0);
        yMap.get(0).add(0);

        boolean[] visited = new boolean[N + 1];
        points = new Point[N + 1];
        points[0] = new Point(0, 0);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            xMap.computeIfAbsent(x, k -> new ArrayList<>());
            yMap.computeIfAbsent(y, k -> new ArrayList<>());

            xMap.get(x).add(y);
            yMap.get(y).add(x);

            points[i] = new Point(x, y);
        }

        DFS(0, 0, xMap, yMap, 0, 0, visited);
        System.out.println(answer);
    }

    private static void DFS(int x, int y, Map<Integer, List<Integer>> xMap, Map<Integer, List<Integer>> yMap, int direction, int visit, boolean[] visited) {

        if (visit == N + 1 && x == 0 && y == 0) {
            answer++;
            return;
        }

        // x축 이동
        if (xMap.containsKey(x)) {
            for (int value : new ArrayList<>(xMap.get(x))) {
                if (direction != 1 && value > y) {  // 위쪽으로 이동
                    int idx = 0;
                    boolean isVisited = false;
                    for (int i = 0; i <= N; i++) {
                        if (points[i].x == x && points[i].y == value) {
                            idx = i;
                            if (visited[i])
                                isVisited = true;
                            break;
                        }
                    }
                    if (!isVisited) {
                        visited[idx] = true;
                        DFS(x, value, xMap, yMap, 1, visit + 1, visited);
                        visited[idx] = false;
                    }
                }
                if (direction != 3 && value < y) {  // 아래쪽으로 이동
                    int idx = 0;
                    boolean isVisited = false;
                    for (int i = 0; i <= N; i++) {
                        if (points[i].x == x && points[i].y == value) {
                            idx = i;
                            if (visited[i])
                                isVisited = true;
                            break;
                        }
                    }
                    if (!isVisited) {
                        visited[idx] = true;
                        DFS(x, value, xMap, yMap, 3, visit + 1, visited);
                        visited[idx] = false;
                    }
                }
            }
        }

        // y축 이동
        if (yMap.containsKey(y)) {
            for (int value : new ArrayList<>(yMap.get(y))) {
                if (direction != 2 && value > x) {  // 오른쪽으로 이동
                    int idx = 0;
                    boolean isVisited = false;
                    for (int i = 0; i <= N; i++) {
                        if (points[i].x == value && points[i].y == y) {
                            idx = i;
                            if (visited[i])
                                isVisited = true;
                            break;
                        }
                    }
                    if (!isVisited) {
                        visited[idx] = true;
                        DFS(value, y, xMap, yMap, 2, visit + 1, visited);
                        visited[idx] = false;
                    }
                }
                if (direction != 4 && value < x) {  // 왼쪽으로 이동
                    int idx = 0;
                    boolean isVisited = false;
                    for (int i = 0; i <= N; i++) {
                        if (points[i].x == value && points[i].y == y) {
                            idx = i;
                            if (visited[i])
                                isVisited = true;
                            break;
                        }
                    }

                    if (!isVisited) {
                        visited[idx] = true;
                        DFS(value, y, xMap, yMap, 4, visit + 1, visited);
                        visited[idx] = false;
                    }
                }
            }
        }
    }

    private static Map<Integer, List<Integer>> deepCopyMap(Map<Integer, List<Integer>> original) {
        Map<Integer, List<Integer>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }
}