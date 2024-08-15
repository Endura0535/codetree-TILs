import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

    public static int maxIcebergChunks(int[] H) {
        int N = H.length;
        int maxChunks = 1;

        Set<Integer> uniqueHeights = new HashSet<>();
        for (int h : H) {
            uniqueHeights.add(h);
        }

        List<Integer> sortedHeights = new ArrayList<>(uniqueHeights);
        Collections.sort(sortedHeights);

        for (int height : sortedHeights) {
            int chunks = 0;
            boolean inChunk = false;

            for (int i = 0; i < N; i++) {
                if (H[i] > height) {
                    if (!inChunk) {
                        chunks++;
                        inChunk = true;
                    }
                } else {
                    inChunk = false;
                }
            }

            maxChunks = Math.max(maxChunks, chunks);
        }

        return maxChunks;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] H = new int[N];

        for (int i = 0; i < N; i++) {
            H[i] = sc.nextInt();
        }

        System.out.println(maxIcebergChunks(H));
        sc.close();
    }
}