
package qualitative.parser;

import java.io.File;
import java.io.FileInputStream;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordParser {

        private int paranum = 0;
        List<XWPFParagraph> paragraphs;
        List<String> words = new ArrayList<String>();
       
	public WordParser(String fileName) {

		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);

			paragraphs = document.getParagraphs();	
			paranum = paragraphs.size();
			fis.close();
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        public int getParanum(){
            return paranum;
        }
        
        public List<String> getWords(){
            
            for(XWPFParagraph para: paragraphs){
                String line = para.getText();

                BreakIterator breakIterator = BreakIterator.getWordInstance();
                breakIterator.setText(line);
                int lastIndex = breakIterator.first();
                while (BreakIterator.DONE != lastIndex) {
                    int firstIndex = lastIndex;
                    lastIndex = breakIterator.next();
                    if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(line.charAt(firstIndex))) {
                        words.add(line.substring(firstIndex, lastIndex));
                    }
                }               
            }
            
            return words;
        
        }
        
        public List<XWPFParagraph> getPara(){
            
            return paragraphs;
        }
}
