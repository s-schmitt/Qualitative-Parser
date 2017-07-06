
package qualitative.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * @author Steve
 */
public class QualitativeParser {
    
    static List<String> words;
    static List<String> lines;
    static List<XWPFParagraph> paragraphs;
    static List<String> exclusions = new ArrayList<String>();
    static Map<String, String> freqs = new HashMap<String, String>();
    static String path;
    
    public static void main(String[] args){
        
        if(args.length > 0){
            path = args[0];
        }
        
        else{
            System.out.println("Give a file");
            System.exit(1);
        }

        QualitativeParser parser = new QualitativeParser();
        WordParser word = new WordParser(path);
        System.out.println(word.getParanum());
        
        exclusions.add("the");
        exclusions.add("a");
        exclusions.add("an");
        exclusions.add("because");
        exclusions.add("food");
        exclusions.add("have");
        exclusions.add("i");
        
        words = word.getWords();
        paragraphs = word.getPara();
        
        parser.printOccur(words);
        System.out.println(freqs);
        for(XWPFParagraph para : paragraphs){
            System.out.println(para.getText());
        }
        
        Iterator it = freqs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
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
            if(word.equalsIgnoreCase(exclusion))
                return false;
        }
        
        return true;
    }
    
}
