package ementum.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFHandler {
    private PDDocument PDD;
    private COSDocument COS;
    private PDFTextStripper PDT;
    private PDFParser PAR;
    private File FILE;
    
    public PDFHandler() throws IOException {
        this.PDD = new PDDocument();
        this.COS = new COSDocument();
        this.PDT = new PDFTextStripper();
        this.PAR = null;
        this.FILE = null;
    }
    
    public PDFHandler(String filePath) {
        try {
            FILE = new File(filePath);
            PAR = new PDFParser(new FileInputStream(this.FILE));
        } catch (IOException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> parseMenu(String menu) {
        String[] untrimmed = menu.split("\\r?\\n");
        ArrayList<String> trimmed = new ArrayList<String>();
        String month = "undefined";
        String Sday = "undefined";
        int day = 0;
        for(int i = 0; i < untrimmed.length; i++) {
            String s = untrimmed[i];
            if(i == 0) {
                month = s.substring(18);
            } else {
                if(s.equals(" ") || s.isEmpty() || s.equals(" 2 ")) {
                    
                } else {
                    if(s.substring(0, 3).equals("Dia")) {
                        Sday = s.substring(4, 6);
                        Sday = Sday.trim();
                        day = Integer.parseInt(Sday);
                    } else {
                        String food = day + " " + month + s;
                        trimmed.add(food);
                    }
                }
            }
        }
        return trimmed;
    }
    
    public void setUp() {
        try {
            PAR.parse();
            COS = PAR.getDocument();
            PDT = new PDFTextStripper();
            PDD = new PDDocument(COS);            
        } catch (IOException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getText() {
        try {
            PDT.setStartPage(1);
            PDT.setEndPage(PDD.getNumberOfPages());
            String text = PDT.getText(PDD);
            return text;
        } catch (IOException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
