package ementum.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
