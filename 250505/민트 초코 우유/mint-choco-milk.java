import java.util.*;

public class Main {
    static final int MAX = 55;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int N, T;
    static int[][] food = new int[MAX][MAX];
    static int[][] faith = new int[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static boolean[][] defended = new boolean[MAX][MAX];
    static String[] input = new String[MAX];

    static List<Leader> leaders = new ArrayList<>();
    static List<Member> group = new ArrayList<>();

    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Leader {
        int foodCount, negFaith;
        Pos pos;
        Leader(int foodCount, int negFaith, int x, int y) {
            this.foodCount = foodCount;
            this.negFaith = negFaith;
            this.pos = new Pos(x, y);
        }
    }

    static class Member {
        int negFaith;
        Pos pos;
        Member(int negFaith, int x, int y) {
            this.negFaith = negFaith;
            this.pos = new Pos(x, y);
        }
    }

    static boolean outOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    static int countBits(int val) {
        int cnt = 0;
        for (int b = 1; b <= 4; b <<= 1) {
            if ((val & b) > 0) cnt++;
        }
        return cnt;
    }

    static void dfs(int x, int y, int target) {
        visited[x][y] = true;
        group.add(new Member(-faith[x][y], x, y));

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (outOfRange(nx, ny) || visited[nx][ny]) continue;
            if (food[nx][ny] != target) continue;
            dfs(nx, ny, target);
        }
    }

    static void lunch() {
        leaders.clear();
        for (int i = 1; i <= N; i++) Arrays.fill(visited[i], false);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j]) continue;
                group.clear();
                dfs(i, j, food[i][j]);

                group.sort((a, b) -> {
                    if (a.negFaith != b.negFaith) return Integer.compare(a.negFaith, b.negFaith);
                    if (a.pos.x != b.pos.x) return Integer.compare(a.pos.x, b.pos.x);
                    return Integer.compare(a.pos.y, b.pos.y);
                });

                int lx = group.get(0).pos.x;
                int ly = group.get(0).pos.y;
                faith[lx][ly] += group.size();
                int count = countBits(food[i][j]);
                leaders.add(new Leader(count, -faith[lx][ly], lx, ly));
            }
        }
    }

    static void evening() {
        for (int i = 1; i <= N; i++) Arrays.fill(defended[i], false);

        leaders.sort((a, b) -> {
            if (a.foodCount != b.foodCount) return Integer.compare(a.foodCount, b.foodCount);
            if (a.negFaith != b.negFaith) return Integer.compare(a.negFaith, b.negFaith);
            if (a.pos.x != b.pos.x) return Integer.compare(a.pos.x, b.pos.x);
            return Integer.compare(a.pos.y, b.pos.y);
        });

        for (Leader l : leaders) {
            int x = l.pos.x, y = l.pos.y;
            if (defended[x][y]) continue;

            int dir = faith[x][y] % 4;
            int pow = faith[x][y] - 1;
            faith[x][y] = 1;
            int cur = food[x][y];

            while (true) {
                x += dx[dir];
                y += dy[dir];
                if (outOfRange(x, y)) break;
                if (food[x][y] == cur) continue;

                int targetFaith = faith[x][y];

                if (pow > targetFaith) {
                    faith[x][y]++;
                    pow -= (targetFaith + 1);
                    food[x][y] = cur;
                    defended[x][y] = true;
                } else {
                    faith[x][y] += pow;
                    food[x][y] |= cur;
                    defended[x][y] = true;
                    break;
                }
            }
        }
    }

    static void output() {
        int[] sum = new int[8];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[food[i][j]] += faith[i][j];
            }
        }
        System.out.println(sum[7] + " " + sum[3] + " " + sum[5] + " " + sum[6] + " " + sum[4] + " " + sum[2] + " " + sum[1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            input[i] = sc.next();
            for (int j = 1; j <= N; j++) {
                char ch = input[i].charAt(j - 1);
                if (ch == 'T') food[i][j] = 1;
                else if (ch == 'C') food[i][j] = 2;
                else if (ch == 'M') food[i][j] = 4;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                faith[i][j] = sc.nextInt();
            }
        }

        for (int t = 0; t < T; t++) {
            lunch();
            evening();
            output();
        }
    }
}