
package qualitative.parser;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Steve
 */
public class ExcelParser {

    String path;
    Workbook workbook1;
    WritableWorkbook copy;
    WritableSheet sheet2;
            
    public ExcelParser(String name){
                
        path = name;
            try {
                WritableWorkbook book = Workbook.createWorkbook(new File(name));
                WritableSheet sheet = book.createSheet("First Sheet",0);
                
                Workbook workbook1 = Workbook.getWorkbook(new File(name));
                WritableWorkbook copy = Workbook.createWorkbook(new File(name));
                WritableSheet sheet2 = copy.getSheet(0);
                
                Label label = new Label(0, 0, "Code");
                Label label2 = new Label(1,0, "Occurences");
                sheet2.addCell(label);
                sheet2.addCell(label2);
            } catch (IOException ex) {
                Logger.getLogger(QualitativeParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void writeToBookVal(int x, int y, String word){
        
        try {          
            jxl.write.Number num = new jxl.write.Number(x,y,Integer.valueOf(word));
            sheet2.addCell(num);
            copy.write();
            copy.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeToBookKey(int x, int y, String word){
        try { 
            Label lab = new Label(x,y,word);
            sheet2.addCell(lab);
            copy.write();
            copy.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
