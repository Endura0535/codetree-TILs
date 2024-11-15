import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        BigInteger B = BigInteger.valueOf(Long.parseLong(st.nextToken()));
        List<Integer> onList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 1) {
                onList.add(i);
            }
        }

        BigInteger idx = B;

        while (idx.compareTo(BigInteger.ZERO) > 0) {
            if(onList.isEmpty())
                break;

            List<Integer> temp = new ArrayList<>(onList);
            for (int x : onList) {
                int next = (x + 1) % N;
                if (onList.contains(next)) {
                    temp.remove(Integer.valueOf(next));
                } else {
                    temp.add(next);
                }
            }
            onList = temp;
            idx = idx.subtract(BigInteger.ONE);
        }

        for (int i = 0; i < N; i++) {
            if (onList.contains(i)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
