/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import utils.getArtiInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author trator979
 */
public class ExceptionPDF {

    /*public static void main (String[] args){

        try {
            // TODO add your handling code here:
            getArtiInfo.FileInfo("BL200").forEach(System.out::println);
            List<List<String>> artList = getArtiInfo.FileInfo("BL200");
            for (List<String> strings : artList) {

                String artCode = strings.get(0);
                if (artCode == null) {
                    System.out.println("No pic here");
                    exceptionPdfGenerator("BL200",strings);
                }
            }
        } catch (SQLException | ClassNotFoundException | DocumentException | IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void exceptionPdfGenerator(String nbreArt, List<List<String>> articleInfos,File path) throws ClassNotFoundException, SQLException, DocumentException, IOException {

        BaseFont bf = BaseFont.createFont(
                BaseFont.COURIER,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
        BaseFont bf2 = BaseFont.createFont(
                BaseFont.COURIER_BOLD,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
        Font font = new Font(bf, 8f);
        Font font1 = new Font(bf, 8.5f);
        Font font2 = new Font(bf2, 9.5f);
        Font fontMin = new Font(bf, 7.5f);

        Document pdfDocument = new Document();
        PdfWriter writer = PdfWriter.getInstance(pdfDocument, new FileOutputStream(new File(path, nbreArt + "Exceptions.pdf")));
        pdfDocument.open();

        for (List<String> articleInfo : articleInfos) {

            pdfDocument.newPage();

            float[] columnNdTabWidths = new float[]{20f, 30f, 10f, 10f};
            float[] columnRdTabWidths = new float[]{20f, 20f, 15f, 15f};

            PdfPTable qrCodeTable = new PdfPTable(3);
            PdfPTable ndTab = new PdfPTable(4);
            ndTab.setWidths(columnNdTabWidths);
            PdfPTable rdTab = new PdfPTable(4);
            rdTab.setWidths(columnRdTabWidths);

            //---------------------------Table 1 assignement----------------------------------------
            PdfPCell cellLogo = new PdfPCell();


            Image image1 = Image.getInstance("C:/Users/Administrateur/Desktop/CoelecMidDev/src/images/landirenzo.png");
            image1.setAlignment(Element.ALIGN_CENTER);
            image1.scaleAbsolute(120, 75);

            cellLogo.addElement(image1);
            cellLogo.setBackgroundColor(BaseColor.WHITE);
            cellLogo.setBorder(Rectangle.NO_BORDER);
            cellLogo.setFixedHeight(47f);
//            qrCodeTable.addCell(cellLogo);

//            Barcode128 Bcode1 = barcode128((String) articleInfo.get(1));
//            PdfContentByte cb3 = writer.getDirectContent();
//            com.itextpdf.text.Image code128Image3 = Bcode1.createImageWithBarcode(cb3, null, null);
            PdfPCell cellCodeLiv = new PdfPCell();
//            cellCodeLiv.addElement(new Phrase("     " + (String) articleInfo.get(1) + "\n", font1));
            cellCodeLiv.addElement(new Phrase("First cell", font1));

//            cellCodeLiv.addElement(code128Image3);

//            cellCodeLiv.setBorder(Rectangle.NO_BORDER);
            cellCodeLiv.setPadding(5.0f);
            cellCodeLiv.setFixedHeight(47f);
            qrCodeTable.addCell(cellCodeLiv);

//            Barcode128 codeArti = barcode128((String) articleInfo.get(2));
//            PdfContentByte cbArti = writer.getDirectContent();
//            com.itextpdf.text.Image code128ImageArti = codeArti.createImageWithBarcode(cbArti, null, null);
            PdfPCell cellArti = new PdfPCell();
//            cellArti.addElement(new Phrase("    " + (String) articleInfo.get(2) + "\n", font1));
            cellArti.addElement(new Phrase("second cell" , font1));
//            cellArti.addElement(code128ImageArti);
//            cellArti.setBorder(Rectangle.NO_BORDER);
            cellArti.setPadding(5.0f);
            cellArti.setFixedHeight(47f);
            qrCodeTable.addCell(cellArti);

            PdfPCell cellHar = new PdfPCell();
            cellHar.addElement(new Phrase("3rd Cell", font2));
//            cellHar.addElement(new Phrase((String) articleInfo.get(3), font2));
//            cellHar.setBorder(Rectangle.NO_BORDER);
            qrCodeTable.addCell(cellHar);

            PdfPCell cellEmpty = new PdfPCell();
            cellEmpty.setColspan(3);
            cellEmpty.addElement(new Phrase("4th cell"));
//            cellEmpty.setBorder(Rectangle.NO_BORDER);
            cellEmpty.setPadding(5.0f);
            cellEmpty.setFixedHeight(47f);
            qrCodeTable.addCell(cellEmpty);

            PdfPCell cellTab = new PdfPCell();
            cellTab.addElement(new Phrase(
                    "Revisone: " + (String) articleInfo.get(4)
                            + "\nDATA REV: " + (String) articleInfo.get(5)
                            + "\nQTA'BOX : " + (String) articleInfo.get(6), font));
            cellTab.setPadding(5.0f);
            cellTab.setFixedHeight(47f);
            qrCodeTable.addCell(cellTab);

            //---------------------------Table 2 assignement----------------------------------------
            Barcode128 codeFORNITORE = barcode128((String) articleInfo.get(7));
            PdfContentByte cbFORNITORE = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageFORNITORE = codeFORNITORE.createImageWithBarcode(cbFORNITORE, null, null);
            PdfPCell cellFORNITORE = new PdfPCell();
//            cellFORNITORE.addElement(new Phrase("FORNITORE:", font1));
//            cellFORNITORE.addElement(new Phrase((String) articleInfo.get(7), font));
//            cellFORNITORE.addElement(code128ImageFORNITORE);
            cellFORNITORE.addElement(new Phrase("FORNITORE:", font1));
            cellFORNITORE.addElement(new Phrase("BarCode here", font1));
//            cellFORNITORE.setBorder(Rectangle.NO_BORDER);
            cellFORNITORE.setPadding(2.0f);
            cellFORNITORE.setFixedHeight(45f);
            ndTab.addCell(cellFORNITORE);

            Barcode128 codeClientRef = barcode128((String) articleInfo.get(8));
            PdfContentByte cbClientRef = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageClientRef = codeClientRef.createImageWithBarcode(cbClientRef, null, null);
            PdfPCell cellClientRef = new PdfPCell();
//            cellClientRef.addElement(new Phrase("N. ORDINE:", font1));
            cellClientRef.addElement(new Phrase("DDT. N:", font1));
            cellClientRef.addElement(new Phrase("BarCode here ", font1));
//            cellClientRef.addElement(new Phrase((String) articleInfo.get(8), font));
//            cellClientRef.addElement(code128ImageClientRef);
//
//            cellClientRef.setBorder(Rectangle.NO_BORDER);
            cellClientRef.setPadding(3.0f);
            cellClientRef.setFixedHeight(45f);
            ndTab.addCell(cellClientRef);

            Barcode128 codeQteLivre = barcode128((String) articleInfo.get(9));
            PdfContentByte cbQteLivre = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageQteLivre = codeQteLivre.createImageWithBarcode(cbQteLivre, null, null);
            PdfPCell cellQteLivre = new PdfPCell();
            cellQteLivre.addElement(new Phrase("QTA'SPED:", font1));
            cellQteLivre.addElement(new Phrase((String) articleInfo.get(9), font));
//            cellQteLivre.addElement(code128ImageQteLivre);

//            cellQteLivre.setBorder(Rectangle.NO_BORDER);
            cellQteLivre.setPadding(3.0f);
            cellQteLivre.setFixedHeight(45f);
            ndTab.addCell(cellQteLivre);

            Barcode128 codeLOT = barcode128((String) articleInfo.get(10));
            PdfContentByte cbLOT = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageLOT = codeLOT.createImageWithBarcode(cbLOT, null, null);
            PdfPCell cellLOT = new PdfPCell();
            cellLOT.addElement(new Phrase("N LOTTO:", font1));
            cellLOT.addElement(new Phrase((String) articleInfo.get(10), font));
//            cellLOT.addElement(code128ImageLOT);

//            cellLOT.setBorder(Rectangle.NO_BORDER);
            cellLOT.setPadding(2.0f);
            cellLOT.setFixedHeight(45f);
            ndTab.addCell(cellLOT);

            PdfPCell cellQCK = new PdfPCell();
            cellQCK.addElement(new Phrase("QCK ID:", fontMin));
            cellQCK.setPadding(3.0f);
            cellQCK.setFixedHeight(45f);
//            ndTab.addCell(cellQCK);

            PdfPCell cellVCK = new PdfPCell();
            cellVCK.addElement(new Phrase("VCK ID:", fontMin));
            cellVCK.setPadding(3.0f);
            cellVCK.setFixedHeight(45f);
//            ndTab.addCell(cellVCK);

            //---------------------------Table 3 assignement----------------------------------------
            Barcode128 codeDtSaisie = barcode128((String) articleInfo.get(11));

            PdfContentByte cb5 = writer.getDirectContent();
            com.itextpdf.text.Image code128Image5 = codeDtSaisie.createImageWithBarcode(cb5, null, null);
            PdfPCell cellDtSaisie = new PdfPCell();
            cellDtSaisie.addElement(new Phrase("DATA DDT:", font));
            cellDtSaisie.addElement(new Phrase((String) articleInfo.get(11), font));
//            cellDtSaisie.addElement(code128Image5);
//            cellDtSaisie.setBorder(Rectangle.NO_BORDER);
            cellDtSaisie.setPadding(5.0f);
            cellDtSaisie.setFixedHeight(53f);
            rdTab.addCell(cellDtSaisie);

            Barcode128 CodeNum = barcode128((String) articleInfo.get(12));
            PdfContentByte cbNum = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageNum = CodeNum.createImageWithBarcode(cbNum, null, null);
            PdfPCell cellNum = new PdfPCell();
            cellNum.addElement(new Phrase("DDT. N:", font));
            cellNum.addElement(new Phrase((String) articleInfo.get(12), font));
//            cellNum.addElement(code128ImageNum);
//            cellNum.setBorder(Rectangle.NO_BORDER);
            cellNum.setPadding(3.0f);
            cellNum.setFixedHeight(53f);
            rdTab.addCell(cellNum);

            PdfPCell cellQta = new PdfPCell();
            cellQta.addElement(new Phrase("N.BOX: ", font));
//            cellQta.addElement(new Phrase("   ", font));
            cellQta.addElement(new Phrase("BarCode here " , font2));
//            cellQta.setBorder(Rectangle.NO_BORDER);
            cellQta.setPadding(2.0f);
            cellQta.setFixedHeight(53f);
            rdTab.addCell(cellQta);

            PdfPCell cellNote = new PdfPCell();
            cellNote.addElement(new Phrase("NOTE: ", font));
            cellNote.addElement(new Phrase("MatrixCode here ", font));
            cellNote.setPadding(5.0f);
            cellNote.setFixedHeight(53f);
            rdTab.addCell(cellNote);

            BarcodeDatamatrix dm = new BarcodeDatamatrix();
            dm.generate((String) articleInfo.get(14));
            Image img = dm.createImage();

            PdfPCell cellMat = new PdfPCell(img, true);
            cellMat.setFixedHeight(53f);
            cellMat.setPadding(1f);
            cellMat.setBorder(Rectangle.NO_BORDER);
            cellMat.setHorizontalAlignment(Element.ALIGN_CENTER);
//            rdTab.addCell(cellMat);

            //---------------------------Document Fill----------------------------------------
            pdfDocument.add(qrCodeTable);
//            pdfDocument.add(new Phrase("\n            ----------------------------------"
//                    + "-----------------------------------------------------",
//                    font1));
            pdfDocument.add(ndTab);
            pdfDocument.add(rdTab);
        }
        pdfDocument.close();

    }

    public static Barcode128 barcode128(String code) {
        Barcode128 code128 = new Barcode128();
        code128.setFont(null);
        code128.setBaseline(-1);
        code128.setSize(12);
        code128.setCode(code);
        code128.setCodeType(Barcode128.CODE128);
        return code128;

    }

}
