import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdfparser.PDFParser;

import java.io.File;

public class PdfParser {

    public String text(File file, int start, int end)
    {
        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        String parsedText = "";

        try{
            pdDoc = PDDocument.load(file);

            pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(start);
            pdfStripper.setEndPage(end);
            parsedText = pdfStripper.getText(pdDoc);
            System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
        }
        catch(Exception e){
            System.err.println("couldn't parse" + e);
            e.printStackTrace();
            try{
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }

        return parsedText;
    }
}
