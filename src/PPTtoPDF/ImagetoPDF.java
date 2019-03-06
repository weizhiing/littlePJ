package PPTtoPDF;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
 
public class ImagetoPDF {
    
    public static void main(String[] args) {
        
        
    }
    
    public static void imageToPdf(String pdfToPath, String temp) {
    	
    	int fileCount = 0;
    	int folderCount = 0;
    	File d = new File(temp);
    	File list[] = d.listFiles();
    	for(int i = 0; i < list.length; i++){
    	if(list[i].isFile()){
    	fileCount++;
    	}else{
    	folderCount++;
    	}
    	}
    	
    	System.out.println(fileCount);
    	System.out.println("Chapter 6 example 3: using a relative path for HTML");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            
            PdfWriter.getInstance(document, new FileOutputStream(pdfToPath));
          //  HtmlWriter writer = HtmlWriter.getInstance(document, new FileOutputStream("Chap0603.html"));
            
          //  writer.setImagepath("../../images/kerstmis/");
            
            // step 3: we open the document
            document.open();
            
            for(int i=0;i<=fileCount-1;i++) {
            // step 4: we add content
            Image jpg = Image.getInstance(temp+"pic_"+i+".jpeg");
            jpg.scalePercent(50);
            document.add(jpg);
            }
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
    }
}
