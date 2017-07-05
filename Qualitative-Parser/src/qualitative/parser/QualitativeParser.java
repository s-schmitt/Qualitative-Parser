
package qualitative.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Steve
 */
public class QualitativeParser {
    
    static List<String> words;
    static List<String> exclusions = new ArrayList<String>();
    static Map<String, String> freqs = new HashMap<String, String>();
    
    public static void main(String[] args){

        QualitativeParser parser = new QualitativeParser();
        ExcelParser excel = new ExcelParser("Test.xls");
        WordParser word = new WordParser("C:\\Users\\Steve\\Documents\\GitHub\\Qualitative-Parser\\test.docx");
        System.out.println(word.getParanum());
        words = word.getWords();
        parser.printOccur(words);
        
        Iterator it = freqs.entrySet().iterator();
        while (it.hasNext()) {
            int i = 1;
            Map.Entry pair = (Map.Entry)it.next();
            excel.writeToBookKey(0, i, pair.getKey().toString());
            excel.writeToBookVal(1, i, pair.getValue().toString());
            i++;
        }

    }   
    
    public void printOccur(List<String> list){
        for(String word : words){
            if(QualitativeParser.checkExclude(word)){
                int occurences = Collections.frequency(words, word);
                String freq = Integer.toString(occurences);
                exclusions.add(word);
                freqs.put(word, freq);  
            }
        }
        
        Iterator it = freqs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int val = Integer.valueOf(pair.getValue().toString());
            if(val <= 5)
                it.remove();
        }       
     
    }
    
    public static boolean checkExclude(String word){
        for(String exclusion : exclusions){
            if(word.equals(exclusion))
                return false;
        }
        
        return true;
    }
    
}