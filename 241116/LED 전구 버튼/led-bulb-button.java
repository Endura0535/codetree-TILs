import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        List<Integer> onList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 1)
                onList.add(i);
        }

        int cycle = 0;
        List<List<Integer>> cycleList = new ArrayList<>();
        int idx = B;
        while (idx > 0) {
            cycleList.add(new ArrayList<>(onList));
            if(cycleList.equals(onList)){
                cycle = B - idx;
                break;
            }
            int size = onList.size();
            List<Integer> temp = new ArrayList<>(onList);
            for (int x : onList) {
                int next = (x + 1) % N;
                if (onList.contains(next))
                    temp.remove(temp.indexOf(next));
                else
                    temp.add(next);
            }
            onList = temp;
            idx--;
        }

        if(cycle != 0)
            onList = cycleList.get(B % cycle);

        for (int i = 0; i < N; i++) {
            if (onList.contains(i))
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
