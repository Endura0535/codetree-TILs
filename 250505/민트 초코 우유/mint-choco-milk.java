import java.io.*;
import java.util.*;

public class Main {

    static class Member implements Comparable<Member> {
        int x, y, value;
        Set<Character> hs = new HashSet<>();

        Member(int x, int y, char ch) {
            this.x = x;
            this.y = y;
            hs.add(ch);
        }

        int getPower() {
            int power = value - 1;
            value = 1;
            return power;
        }

        public int compareTo(Member o) {
            if (value != o.value) return Integer.compare(o.value, value);
            if (x != o.x) return Integer.compare(x, o.x);
            return Integer.compare(y, o.y);
        }
    }

    static class Group implements Comparable<Group> {
        List<Member> list = new ArrayList<>();
        Member leader;
        int typeCode, size;

        void add(Member m) {
            list.add(m);
        }

        void finalizeGroup() {
            leader = Collections.min(list);
            typeCode = getType(leader);
            size = leader.hs.size();
        }

        public int compareTo(Group o) {
            if (size != o.size) return Integer.compare(size, o.size);
            return leader.compareTo(o.leader);
        }
    }

    static int N, T;
    static Member[][] map;
    static boolean[][] grouped, damaged;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new Member[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = new Member(i, j, line.charAt(j - 1));
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j].value = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            reset();
            lunch();
            List<Group> groups = group();
            evening(groups);
            report();
        }

        System.out.print(sb);
    }

    static void reset() {
        grouped = new boolean[N + 1][N + 1];
        damaged = new boolean[N + 1][N + 1];
    }

    static void lunch() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j].value++;
            }
        }
    }

    static List<Group> group() {
        List<Group> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!grouped[i][j]) {
                    Group g = bfs(i, j);
                    g.finalizeGroup();
                    balance(g);
                    result.add(g);
                }
            }
        }
        return result;
    }

    static Group bfs(int x, int y) {
        Group g = new Group();
        Deque<Member> q = new ArrayDeque<>();
        q.add(map[x][y]);
        grouped[x][y] = true;
        g.add(map[x][y]);

        while (!q.isEmpty()) {
            Member cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (!inRange(nx, ny) || grouped[nx][ny]) continue;
                if (!same(cur, map[nx][ny])) continue;
                grouped[nx][ny] = true;
                q.add(map[nx][ny]);
                g.add(map[nx][ny]);
            }
        }
        return g;
    }

    static void balance(Group g) {
        for (Member m : g.list) {
            if (m != g.leader) {
                m.value--;
                g.leader.value++;
            }
        }
    }

    static void evening(List<Group> groups) {
        Collections.sort(groups);
        for (Group g : groups) {
            Member leader = g.leader;
            if (damaged[leader.x][leader.y]) continue;

            int power = leader.value - 1;
            leader.value = 1;
            int dir = (power + 1) % 4;
            int nx = leader.x, ny = leader.y;
            int type = getType(leader);

            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                if (!inRange(nx, ny)) break;

                Member target = map[nx][ny];
                if (getType(target) == type) continue;

                int curVal = target.value;
                if (power > curVal) {
                    target.value += 1;
                    power -= (curVal + 1);
                    target.hs.clear();
                    target.hs.addAll(leader.hs);
                    damaged[nx][ny] = true;
                } else {
                    target.value += power;
                    target.hs.addAll(leader.hs);
                    damaged[nx][ny] = true;
                    break;
                }
            }
        }
    }

    static void report() {
        int[] sum = new int[8];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[getType(map[i][j])] += map[i][j].value;
            }
        }
        for (int i = 1; i <= 7; i++) sb.append(sum[i]).append(" ");
        sb.append("\n");
    }

    static boolean inRange(int x, int y) {
        return x > 0 && x <= N && y > 0 && y <= N;
    }

    static boolean same(Member a, Member b) {
        return a.hs.size() == b.hs.size() && a.hs.containsAll(b.hs);
    }

    static int getType(Member m) {
        if (m.hs.size() == 3) return 1;
        if (m.hs.size() == 2) {
            if (m.hs.contains('T') && m.hs.contains('C')) return 2;
            if (m.hs.contains('M') && m.hs.contains('T')) return 3;
            if (m.hs.contains('C') && m.hs.contains('M')) return 4;
        }
        if (m.hs.contains('M')) return 5;
        if (m.hs.contains('C')) return 6;
        if (m.hs.contains('T')) return 7;
        return 0;
    }
}
