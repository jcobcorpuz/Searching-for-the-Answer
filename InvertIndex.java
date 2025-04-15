import java.io.File;
import java.io.FileWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class InvertIndex {
    public static void main(String[] args){
        try{
            Scanner scanner = new Scanner(new File("input.txt"));
            ArrayList<String> words = new ArrayList<>();
            while(scanner.hasNext()){
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if(!word.equals("")){
                    words.add(word);
                }
            }
            scanner.close();

            HashMap<String, ArrayList<Integer>> index = new HashMap<>();
            String[] commonWordsArr = {"a", "and", "the", "to", "for", "they", "an", "in", "of"};
            ArrayList<String> commonWords = new ArrayList<>();
            for (int i = 0; i < commonWordsArr.length; i++){
                commonWords.add(commonWordsArr[i]);
            }

            for(int i = 0; i < words.size(); i++){
                String word = words.get(i);
                if (!commonWords.contains(word)){
                    if (!index.containsKey(word)){
                        index.put(word, new ArrayList<Integer>());
                    }
                    index.get(word).add(i);
                }
            }

            FileWriter writer = new FileWriter("invertedIndex.txt");
            writer.write("{\n");
            ArrayList<String> keys = new ArrayList<>(index.keySet());
            Collections.sort(keys);
            int size = keys.size();

            for(int i = 0; i < size; i++){
                String key = keys.get(i);
                writer.write("  \"" + key + "\": " + index.get(key));
                if (i < size - 1){
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("}\n");
            writer.close();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
