import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Relation {
        int a;
        int b;
        char status;

        public Relation(int a, int b, char status) {
            this.a = a;
            this.b = b;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Relation{" +
                    "a=" + a +
                    ", b=" + b +
                    ", status=" + status +
                    '}';
        }
    }

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        List<Relation> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Relation(a, b, c));
        }
        Collections.sort(list, (o1, o2) -> o1.a == o2.a ? o1.b - o2.b : o1.a - o1.a);
        DFS(list, arr);
        System.out.println(answer);
    }

    public static void DFS(List<Relation> list, int[] arr) {
        if (list.isEmpty()) {
            int x = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == 0) {
                    x *= 3;
                }
            }
            answer += x;
            return;
        }

        List<Relation> listCopy = new ArrayList<>(list);
        Relation rel = list.get(0);
        listCopy.remove(0);
        if (arr[rel.a] != 0) {
            if (rel.status == 'S') {
                arr[rel.b] = arr[rel.a];
                DFS(listCopy, arr);
            } else {
                arr[rel.b] = (arr[rel.a] + 1) % 3;
                if (arr[rel.b] == 0)
                    arr[rel.b] = 3;
                DFS(listCopy, arr);
                arr[rel.b] = (arr[rel.a] + 2) % 3;
                if (arr[rel.b] == 0)
                    arr[rel.b] = 3;
                DFS(listCopy, arr);
            }
        }

        if (arr[rel.a] == 0) {
            if (rel.status == 'S') {
                for (int i = 1; i <= 3; i++) {
                    arr[rel.a] = i;
                    arr[rel.b] = i;
                    DFS(listCopy, arr);
                }
            } else {
                for (int i = 1; i <= 3; i++) {
                    arr[rel.a] = i;
                    arr[rel.b] = (i + 1) % 3;
                    if (arr[rel.b] == 0)
                        arr[rel.b] = 3;
                    DFS(listCopy, arr);
                    arr[rel.b] = (i + 2) % 3;
                    if (arr[rel.b] == 0)
                        arr[rel.b] = 3;
                    DFS(listCopy, arr);
                }
            }
        }
    }
}
