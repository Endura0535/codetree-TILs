import java.util.*;

public class Main {
    static final int MAX = 55;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int n, t;
    static int[][] food = new int[MAX][MAX];
    static int[][] faith = new int[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static boolean[][] defended = new boolean[MAX][MAX];
    static List<Leader> leaders = new ArrayList<>();
    static List<Member> group = new ArrayList<>();

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Leader {
        int typeCount, negFaith;
        Pair pos;
        Leader(int typeCount, int negFaith, Pair pos) {
            this.typeCount = typeCount;
            this.negFaith = negFaith;
            this.pos = pos;
        }
    }

    static class Member {
        int negFaith;
        Pair pos;
        Member(int negFaith, Pair pos) {
            this.negFaith = negFaith;
            this.pos = pos;
        }
    }

    static boolean out(int x, int y) {
        return x < 1 || x > n || y < 1 || y > n;
    }

    static int countType(int mask) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if ((mask & (1 << i)) != 0) count++;
        }
        return count;
    }

    static void dfs(int x, int y, int val) {
        visited[x][y] = true;
        group.add(new Member(-faith[x][y], new Pair(x, y)));
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (out(nx, ny) || visited[nx][ny] || food[nx][ny] != val) continue;
            dfs(nx, ny, val);
        }
    }

    static void lunch() {
        leaders.clear();
        for (int i = 1; i <= n; i++) Arrays.fill(visited[i], false);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (visited[i][j]) continue;
                group.clear();
                int val = food[i][j];
                dfs(i, j, val);
                group.sort((a, b) -> {
                    if (a.negFaith != b.negFaith) return Integer.compare(a.negFaith, b.negFaith);
                    if (a.pos.x != b.pos.x) return Integer.compare(a.pos.x, b.pos.x);
                    return Integer.compare(a.pos.y, b.pos.y);
                });
                Pair leader = group.get(0).pos;
                faith[leader.x][leader.y] += group.size();
                leaders.add(new Leader(countType(val), -faith[leader.x][leader.y], leader));
            }
        }
    }

    static void evening() {
        for (int i = 1; i <= n; i++) Arrays.fill(defended[i], false);
        leaders.sort((a, b) -> {
            if (a.typeCount != b.typeCount) return Integer.compare(a.typeCount, b.typeCount);
            if (a.negFaith != b.negFaith) return Integer.compare(a.negFaith, b.negFaith);
            if (a.pos.x != b.pos.x) return Integer.compare(a.pos.x, b.pos.x);
            return Integer.compare(a.pos.y, b.pos.y);
        });

        for (Leader l : leaders) {
            int x = l.pos.x, y = l.pos.y;
            if (defended[x][y]) continue;
            int dir = faith[x][y] % 4;
            int power = faith[x][y] - 1;
            faith[x][y] = 1;
            int cur = food[x][y];
            int nx = x, ny = y;
            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                if (out(nx, ny)) break;
                if (food[nx][ny] == cur) continue;

                int targetFaith = faith[nx][ny];
                if (power > targetFaith) {
                    power -= (targetFaith + 1);
                    faith[nx][ny] = targetFaith + 1;
                    food[nx][ny] = cur;
                    defended[nx][ny] = true;
                } else {
                    faith[nx][ny] += power;
                    food[nx][ny] |= cur;
                    defended[nx][ny] = true;
                    break;
                }
            }
        }
    }

    static void printResult() {
        int[] res = new int[8];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                res[food[i][j]] += faith[i][j];
            }
        }
        System.out.println(res[7] + " " + res[3] + " " + res[5] + " " + res[6] + " " + res[4] + " " + res[2] + " " + res[1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        t = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            String s = sc.next();
            for (int j = 1; j <= n; j++) {
                char c = s.charAt(j - 1);
                if (c == 'T') food[i][j] = 1;
                else if (c == 'C') food[i][j] = 2;
                else food[i][j] = 4;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                faith[i][j] = sc.nextInt();
            }
        }
        while (t-- > 0) {
            lunch();
            evening();
            printResult();
        }
    }
}
