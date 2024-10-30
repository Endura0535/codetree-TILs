import java.util.*;
import java.io.*;

public class Main {
    // 우상좌하
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(map[i], -1);
        }
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '/')
                    map[i + 1][j + 1] = 1;
                else
                    map[i + 1][j + 1] = 0;
            }
        }

        for (int i = 1; i <= N; i++) {
            DFS(1, i, 0, 0);
            DFS(M, i, 2, 0);
        }
        for (int i = 1; i <= M; i++) {
            DFS(i, 1, 3, 0);
            DFS(i, N, 1, 0);
        }

        System.out.println(answer);
    }

    public static void DFS(int x, int y, int d, int cnt) {
        if (cnt != 0) {
            if (map[y][x] == -1) {
                answer = Math.max(answer, cnt);
                return;
            }
        }

        if (map[y][x] == 0) {   // / : 우상좌하 -> 하좌상우
            DFS(x + dx[3 - d], y + dy[3 - d], 3 - d, cnt + 1);
        } else {     // \ : 우상좌하 -> 상우하좌
            int nd = d % 2 == 0 ? d + 1 : d - 1;
            DFS(x + dx[nd], y + dy[nd], nd, cnt + 1);
        }
    }
}