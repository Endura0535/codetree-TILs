import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) {
                stack.push(y);
                answer++;
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                answer++;
            }

            if (!stack.isEmpty()) {
                if (stack.peek() != y)
                    stack.push(y);
            } else stack.push(y);
        }
        System.out.println(answer);
    }
}
