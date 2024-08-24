import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Character> alphabetList = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        List<Integer>[] words = new List[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = new ArrayList<>();
            for (int j = 0; j < word.length(); j++) {
                words[i].add(alphabetList.indexOf(word.charAt(j)));
            }
        }

        List<String> fastWords = new ArrayList<>();
        List<String> slowWords = new ArrayList<>();
        String[] fastArr = new String[N];
        String[] slowArr = new String[N];


        for (int i = 0; i < N; i++) {
            StringBuilder fast = new StringBuilder();
            StringBuilder slow = new StringBuilder();

            Collections.sort(words[i]);
            for (int j = 0; j < words[i].size(); j++) {
                fast.append(alphabetList.get(words[i].get(j)));
            }

            Collections.sort(words[i], Collections.reverseOrder());
            for (int j = 0; j < words[i].size(); j++) {
                slow.append(alphabetList.get(words[i].get(j)));
            }

            fastWords.add(fast.toString());
            slowWords.add(slow.toString());
            fastArr[i] = fast.toString();
            slowArr[i] = slow.toString();
        }

        for (int i = 0; i < N; i++) {
            String fastWord = fastArr[i];
            String slowWord = slowArr[i];
            fastWords.add(slowWord);
            fastWords.remove(fastWord);
            slowWords.add(fastWord);
            slowWords.remove(slowWord);

            Collections.sort(fastWords, Collections.reverseOrder());
            Collections.sort(slowWords);

            int fastestIdx = slowWords.indexOf(fastWord) + 1;
            int slowestIdx = N - fastWords.indexOf(slowWord);

            System.out.println(fastestIdx + " " + slowestIdx);

            fastWords.remove(slowWord);
            fastWords.add(fastWord);
            slowWords.remove(fastWord);
            slowWords.add(slowWord);
        }
    }
}