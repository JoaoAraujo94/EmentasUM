/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ementum.run;

import ementum.handler.PDFHandler;

/**
 *
 * @author joao1
 */
public class EmentasUM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = args[0];
        PDFHandler pdh = new PDFHandler(path);
        pdh.setUp();
        String text = pdh.getText();
        System.out.println(text);
    }
    
}
