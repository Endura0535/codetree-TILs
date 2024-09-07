import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        st = new StringTokenizer(br.readLine());
        int a1 = Integer.parseInt(st.nextToken());
        int a2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int b1 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            arr = swap(arr, a1, a2);
            arr = swap(arr, b1, b2);
        }

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }

    static public int[] swap(int[] arr, int a, int b) {
        Queue<Integer> queue = new LinkedList<>();
        for (int j = b - 1; j >= a - 1; j--) {
            queue.add(arr[j]);
        }
        for (int j = a - 1; j <= b - 1; j++) {
            arr[j] = queue.poll();
        }
        return arr;
    }
}