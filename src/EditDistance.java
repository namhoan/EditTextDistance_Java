/**
Namho An
4/5/2015
COSI12b
PA06
**/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.io.*;
import java.util.*;
import java.io.File;

public class EditDistance {
	private static final Thread Thread = null;//for the exit execution
	//input the file name and open and receives in the dictionary
	public static void main(String[] args) throws FileNotFoundException {
		   dictionary = new  HashSet<String>();
		   Scanner scanner = new Scanner(System.in);
		   System.out.print("Enter name of dictionary file: ");
		   String fileName = scanner.nextLine();
		   File input = new File(fileName);
		   Scanner reader = new Scanner(input);
		   while(reader.hasNextLine()) {
			   String word = reader.nextLine();
			   dictionary.add(word);
		   }
       try {
    	   inputDictionary();
       }
       catch (FileNotFoundException e) {
    	   System.out.println("File is not found");
       }
	   }
	   //Indicate the dictionary in the Set<String> 
	   static Set<String> dictionary;
	   //User prompt and print out the result with dictionary using HashSet<String>
	   public static void inputDictionary() throws FileNotFoundException {	   
		   while(true){ //user prompt to input the words that execute
		   Scanner scanner = new Scanner(System.in);
		   System.out.print("Enter two words separated by a space :");
		   String startWord = scanner.next();
           String endWord = scanner.next();
           List<String> list = new ArrayList<String>();
		   for(String string : listWords(startWord,endWord)) {
			   list.add(string);
		   }
			   if(dictionary.contains(startWord)&&dictionary.contains(endWord)){
				   System.out.print("Path = ");
				   if(list.size() >= 1) {
					   System.out.print(list.get(0));
				   for(int i=1; i < list.size(); i++){
					   System.out.print(" ," + list.get(i));
				   }
				   }
				   
				   System.out.println();
				   System.out.print("Edit distance = ");
				   System.out.println(list.size()-1);
		       }else if (!dictionary.contains(startWord)&&!dictionary.contains(endWord)){
                   System.out.println("Word does not exist");				   
			   }else if (Collections.frequency(list, startWord) != Collections.frequency(list, endWord)){
				   System.out.println("No Solution");
			   }else if (startWord == "quit"){
				   Runtime.getRuntime().addShutdownHook(Thread);//exit from the operation
				   
			   }
	   }
	   }
   
		   
	   //List of the words using the editPath method
	   //returns after changing the words using queue
        public static List<String> listWords(String startWord, String endWord) {
            final Queue<String> queue = new LinkedList<String>();
            final Map<String, String> backward = new HashMap<String, String>();
            queue.add(startWord);
            backward.put(startWord, null);

            while (!queue.isEmpty()) {
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) {
                    return editPath(backward, endWord);
                }
                changeWords(currentWord, queue, backward);
            }
            return Collections.EMPTY_LIST;
        }
       //Replace the alphabets in the single words
        private static void changeWords(String startWord, Queue<String> queue, Map<String, String> backward) {
            for (int i = 0; i < startWord.length(); i++) {
                char[] endWord = startWord.toCharArray();
                for (char j = 'a'; j < 'z'; j++) {
                    endWord[i] = j;
                    String word = new String(endWord);
                    if (validate(word, backward, startWord)) {
                        queue.add(word);
                        backward.put(word, startWord);
                    }
                }
            }
        }
        //Return as a boolean type and check if the word in dictionary is valid
        private static boolean validate(String word, Map<String, String> backTrack, String startWord) {
            return dictionary.contains(word) && !backTrack.containsKey(word) && !word.equals(startWord);
        }
        //Edit the path and returns the reverse order of the endWord
        private static List<String> editPath(Map<String, String> backTrack, String endWord) {
            final List<String> wordList = new ArrayList<String>();
            String word = endWord;
            while (backTrack.containsKey(word)) {
                wordList.add(word);
                word = backTrack.get(word);
            }
            Collections.reverse(wordList);
            return wordList;
        }
}
