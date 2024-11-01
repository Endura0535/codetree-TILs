import java.util.*;
import java.io.*;

public class Main {

    static List<List<Integer>> indexList = new ArrayList<>();
    static List<Integer> startList = new ArrayList<>();
    static List<Integer> endList = new ArrayList<>();
    static int bracketCnt; 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Deque<Integer> list1 = new ArrayDeque<>();
        Deque<Integer> list2 = new ArrayDeque<>();

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(')
                list1.addLast(i);
            if(str.charAt(i) == ')')
                list2.addFirst(i);
        }

        startList.addAll(list1);
        endList.addAll(list2);
        bracketCnt = list1.size();

        DFS(0, new ArrayList<>());

        List<String> answerList = new ArrayList<>();

        for(List<Integer> list : indexList){
            if(list.isEmpty())    continue;
            Collections.sort(list);
            int idx = 0;
            String string = "";
            for(int i = 0; i < str.length(); i++){
                if(idx == list.size()){
                    string += str.charAt(i);
                    continue;
                }
                if(list.get(idx) != i)
                    string += str.charAt(i);
                else
                    idx++;
            }
            answerList.add(string);
        }
        Collections.sort(answerList);
        for(String string : answerList){
            System.out.println(string);
        }
    }

    public static void DFS(int idx, List<Integer> list){
        if(idx == bracketCnt){
            indexList.add(list);
            return;
        }

        DFS(idx+1, list);
        List<Integer> newList = new ArrayList<>();
        for(int i : list){
            newList.add(i);
        }
        newList.add(startList.get(idx));
        newList.add(endList.get(idx));
        DFS(idx+1, newList);
    }
}