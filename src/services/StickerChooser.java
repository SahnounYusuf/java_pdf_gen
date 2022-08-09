/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ceolec.pdfGen;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.getArtiInfo;

/**
 *
 * @author Administrateur
 */
public class StickerChooser {
    
    public static void chooser(String nbreArt, File path,String username,String password) throws ClassNotFoundException, DocumentException, IOException {
        
        try {
            // TODO add your handling code here:
            getArtiInfo.FileInfo(nbreArt,username,password).forEach(System.out::println);
            List<List<String>> artList = getArtiInfo.FileInfo(nbreArt,username,password);

            List<List<String>> standardList = new ArrayList<>();
            List<List<String>> exceptionList = new ArrayList<>();

            for (List<String> strings : artList) {

                String artCode = strings.get(0);
                if (artCode == null) {
                    System.out.println("No pic here");
//                    ExceptionPDF.exceptionPdfGenerator(nbreArt,artList.get(indx),path);
                    exceptionList.add(strings);
                } else {
                    System.out.println("With pic");
//                    StandardPDF.FileFormat(nbreArt,artList.get(indx), path,pdfDocument);
                    standardList.add(strings);
                }


            }
            System.out.println("=================================List of Standard====================================");
            standardList.forEach(System.out::println);
            System.out.println("creating pdf for standard list ...");
            if (standardList.isEmpty())
                System.out.println("Standard List is empty!");
            else {
                StandardPDF.FileFormat(nbreArt, standardList, path,username,password);
                System.out.println("Standard list PDF Created!");
            }
            System.out.println("=================================List of Exception====================================");
            exceptionList.forEach(System.out::println);
            System.out.println("creating pdf for exceptionList ...");
            if (exceptionList.isEmpty())
                System.out.println("Exception List is empty!");
            else
            {
                ExceptionPDF.exceptionPdfGenerator(nbreArt, exceptionList, path);
                System.out.println("Exception list PDF created!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(pdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
