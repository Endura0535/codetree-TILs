import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int answer = 0;
    static List<Integer> developerList = new ArrayList<>();
    static List<Integer> companyList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        developerList = new ArrayList<>();
        companyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            developerList.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            companyList.add(Integer.parseInt(st.nextToken()));
        }

        dfs(0, new ArrayList<>(), new boolean[N]);
        System.out.println(answer);
    }

    public static void dfs(int idx, ArrayList<Integer> list, boolean[] visited) {
        if (list.size() == N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;
            if (companyList.get(i) >= developerList.get(idx)) {
                list.add(i);
                visited[i] = true;
                dfs(idx + 1, list, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
