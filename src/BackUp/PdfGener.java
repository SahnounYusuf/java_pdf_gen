/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUp;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeDatamatrix;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import utils.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author trator979
 */
public class PdfGener {

    public static Document FileFormat(String nbreArt, File path,String username,String password) throws ClassNotFoundException, SQLException, FileNotFoundException, DocumentException, IOException {

        ResultSet rs = DatabaseConnection.getInformations(nbreArt,username,password);

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
//        java.io.File pdfFile = new java.io.File("hallo.pdf");
//        com.itextpdf.text.pdf.PdfWriter.getInstance(pdfDocument, new java.io.FileOutputStream(pdfFile));
//        PdfWriter writer = PdfWriter.getInstance(pdfDocument, new FileOutputStream(path+"/hallo.pdf"));
        PdfWriter writer = PdfWriter.getInstance(pdfDocument, new FileOutputStream(new File(path, nbreArt + ".pdf")));
        pdfDocument.open();
        
        

        while (rs.next()) {
//            String cpt = rs.getString(1);
//            String court_designation = rs.getString(2);
            String LBLC_DESIGNATION = rs.getString(3);
            String arti_plan = rs.getString(4);
            String ARTI_INDICE = rs.getString(5);
            String CODE_ARTI = rs.getString(6);
            String DT_INDICE = rs.getString(7);
//            String LBLC_BLCL_NUMERO = rs.getString(8);
            String BL_NUMERO = rs.getString(9);
//            String CDE_NUMERO = rs.getString(10);
            String CDE_CLIENT_REF = rs.getString(11);
//            String LBLC_LIGNE = rs.getString(12);
            String LBLC_QTE_A_LIVRER_UV = rs.getString(13);
//            String COCL_NUMERO_COLIS_FIN = rs.getString(14);
            String COCL_NUMERO = rs.getString(15);
//            String LBLC_UNIT_CODE_UV = rs.getString(16);
//            String LBLC_COEF_UV_UE = rs.getString(17);
//            String LBLC_ARTI_CODE_LIVRE = rs.getString(18);
            String LOGO = rs.getString(19);
            String QTE_LIVRE = rs.getString(20);
//            String UV = rs.getString(21);
            String LBLC_NUMERO_COLIS = rs.getString(22);
//            String blcl_clnt_code = rs.getString(23);
//            String LBLC_CDCL_NUMERO = rs.getString(24);
//            String LBLC_LCCL_LIGNE = rs.getString(25);
//            String BLCL_USER_NUM1 = rs.getString(26);
//            String BLCL_USER_NUM2 = rs.getString(27);
//            String BLCL_USER_CAR2 = rs.getString(28);
//            String CLAD_NOM = rs.getString(29);
//            String CLAD_ADRESSE1 = rs.getString(30);
//            String CLAD_ADRESSE2 = rs.getString(31);
//            String CLAD_ADRESSE3 = rs.getString(32);
//            String CLAD_PAYS_CODE = rs.getString(33);
//            String PAYS_LIBELLE = rs.getString(34);
//            String BLCL_DT_ENVOI = rs.getString(35);
//            String DT_ENVOIE_CB = rs.getString(36);
            String LOT = rs.getString(37);
            String DT_ENVOIE = rs.getString(38);
            String CLNT_NOTRE_REF = rs.getString(39);
            String qrcode = rs.getString(40);

            pdfDocument.newPage();

            float[] columnNdTabWidths = new float[]{15f, 13f, 12f, 17f, 6.5f, 6.5f};
            float[] columnRdTabWidths = new float[]{20f, 20f, 10f, 10f, 10f};

            PdfPTable qrCodeTable = new PdfPTable(3);
            PdfPTable ndTab = new PdfPTable(6);
            ndTab.setWidths(columnNdTabWidths);
            PdfPTable rdTab = new PdfPTable(5);
            rdTab.setWidths(columnRdTabWidths);

            //---------------------------Table 1 assignement----------------------------------------
            PdfPCell cellLogo = new PdfPCell();
            
            if (LOGO.equals("LANDI")) {
                Image image1 = Image.getInstance("D:/NetBeansProjects/CEOLEC/src/images/landirenzo.png");
                image1.setAlignment(Element.ALIGN_CENTER);
                image1.scaleAbsolute(120, 75);

                cellLogo.addElement(image1);
                cellLogo.setBackgroundColor(BaseColor.WHITE);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                cellLogo.setFixedHeight(47f);
                qrCodeTable.addCell(cellLogo);
            } else if (LOGO.equals("LOVATO")) {
                Image image1 = Image.getInstance("D:/NetBeansProjects/CEOLEC/src/images/levato.png");
                image1.setAlignment(Element.ALIGN_CENTER);
                image1.scaleAbsolute(120, 75);

                cellLogo.addElement(image1);
                cellLogo.setBackgroundColor(BaseColor.WHITE);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                cellLogo.setFixedHeight(47f);
                qrCodeTable.addCell(cellLogo);

            } else if (LOGO.equals("AEB")) {
                Image image1 = Image.getInstance("D:/NetBeansProjects/CEOLEC/src/images/aeb.png");
                image1.setAlignment(Element.ALIGN_CENTER);
                image1.scaleAbsolute(120, 75);

                cellLogo.addElement(image1);
                cellLogo.setBackgroundColor(BaseColor.WHITE);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                cellLogo.setFixedHeight(47f);
                qrCodeTable.addCell(cellLogo);
            }

            System.out.println("Max number is : " + getMaxNbre(nbreArt,username,password));

            Barcode128 Bcode1 = barcode128(CODE_ARTI);
            PdfContentByte cb3 = writer.getDirectContent();
            com.itextpdf.text.Image code128Image3 = Bcode1.createImageWithBarcode(cb3, null, null);
            PdfPCell cellCodeLiv = new PdfPCell();
            cellCodeLiv.addElement(new Phrase("     " + CODE_ARTI + "\n", font1));
            cellCodeLiv.addElement(code128Image3);

            cellCodeLiv.setBorder(Rectangle.NO_BORDER);
            cellCodeLiv.setPadding(5.0f);
            cellCodeLiv.setFixedHeight(47f);
            qrCodeTable.addCell(cellCodeLiv);

            Barcode128 codeArti = barcode128(arti_plan);
            PdfContentByte cbArti = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageArti = codeArti.createImageWithBarcode(cbArti, null, null);
            PdfPCell cellArti = new PdfPCell();
            cellArti.addElement(new Phrase("    " + arti_plan + "\n", font1));
            cellArti.addElement(code128ImageArti);

            cellArti.setBorder(Rectangle.NO_BORDER);
            cellArti.setPadding(5.0f);
            cellArti.setFixedHeight(47f);

            qrCodeTable.addCell(cellArti);

//            PdfPCell cellFige = new PdfPCell();
//            cellFige.addElement(new Phrase(arti_plan, font));
//            cellFige.setBorder(Rectangle.NO_BORDER);
//            qrCodeTable.addCell(cellFige);
            PdfPCell cellHar = new PdfPCell();
            cellHar.addElement(new Phrase("  ", font2));
            cellHar.addElement(new Phrase(LBLC_DESIGNATION, font2));
            cellHar.setBorder(Rectangle.NO_BORDER);
            qrCodeTable.addCell(cellHar);

            PdfPCell cellEmpty = new PdfPCell();
            cellEmpty.addElement(new Phrase("        "));
            cellEmpty.setBorder(Rectangle.NO_BORDER);
            qrCodeTable.addCell(cellEmpty);

            PdfPCell cellTab = new PdfPCell();
            cellTab.addElement(new Phrase("Revisone: " + ARTI_INDICE + "\nDATA REV: " + DT_INDICE + "\nQTA'BOX : " + LBLC_QTE_A_LIVRER_UV, font));
            cellTab.setPadding(5.0f);
            cellTab.setFixedHeight(47f);
            qrCodeTable.addCell(cellTab);

            //---------------------------Table 2 assignement----------------------------------------
//            PdfPCell cellFornitore = new PdfPCell();
//            cellFornitore.addElement(new Phrase("FORNITORE:\n BarCode HERE", font));
//            cellFornitore.setBorder(Rectangle.NO_BORDER);
//            ndTab.addCell(cellFornitore);
            Barcode128 codeFORNITORE = barcode128(CLNT_NOTRE_REF);
            PdfContentByte cbFORNITORE = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageFORNITORE = codeFORNITORE.createImageWithBarcode(cbFORNITORE, null, null);
            PdfPCell cellFORNITORE = new PdfPCell();
            cellFORNITORE.addElement(new Phrase("FORNITORE:", font1));
            cellFORNITORE.addElement(new Phrase(CLNT_NOTRE_REF, font));
            cellFORNITORE.addElement(code128ImageFORNITORE);

            cellFORNITORE.setBorder(Rectangle.NO_BORDER);
            cellFORNITORE.setPadding(2.0f);
            cellFORNITORE.setFixedHeight(45f);
            ndTab.addCell(cellFORNITORE);

//            PdfPCell cellOrdine = new PdfPCell();
//            cellOrdine.addElement(new Phrase("N. ORDINE:\n BarCode HERE", font));
//            cellOrdine.setBorder(Rectangle.NO_BORDER);
//            ndTab.addCell(cellOrdine);
            Barcode128 codeClientRef = barcode128(CDE_CLIENT_REF);
            PdfContentByte cbClientRef = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageClientRef = codeClientRef.createImageWithBarcode(cbClientRef, null, null);
            PdfPCell cellClientRef = new PdfPCell();
            cellClientRef.addElement(new Phrase("N. ORDINE:", font1));
            cellClientRef.addElement(new Phrase(CDE_CLIENT_REF, font));
            cellClientRef.addElement(code128ImageClientRef);

            cellClientRef.setBorder(Rectangle.NO_BORDER);
            cellClientRef.setPadding(3.0f);
            cellClientRef.setFixedHeight(45f);
            ndTab.addCell(cellClientRef);

//            PdfPCell cellQtaSped = new PdfPCell();
//            cellQtaSped.addElement(new Phrase("FORNITORE:\n BarCode HERE", font));
//            cellQtaSped.setBorder(Rectangle.NO_BORDER);
//            ndTab.addCell(cellQtaSped);
            Barcode128 codeQteLivre = barcode128(QTE_LIVRE);
            PdfContentByte cbQteLivre = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageQteLivre = codeQteLivre.createImageWithBarcode(cbQteLivre, null, null);
            PdfPCell cellQteLivre = new PdfPCell();
            cellQteLivre.addElement(new Phrase("QTA'SPED:", font1));
            cellQteLivre.addElement(new Phrase(QTE_LIVRE, font));
            cellQteLivre.addElement(code128ImageQteLivre);

            cellQteLivre.setBorder(Rectangle.NO_BORDER);
            cellQteLivre.setPadding(3.0f);
            cellQteLivre.setFixedHeight(45f);
            ndTab.addCell(cellQteLivre);

//            PdfPCell cellLotto = new PdfPCell();
//            cellLotto.addElement(new Phrase("N LOTTO:\n BarCode HERE", font));
//            cellLotto.setBorder(Rectangle.NO_BORDER);
//            ndTab.addCell(cellLotto);
            Barcode128 codeLOT = barcode128(LOT);
            PdfContentByte cbLOT = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageLOT = codeLOT.createImageWithBarcode(cbLOT, null, null);
            PdfPCell cellLOT = new PdfPCell();
            cellLOT.addElement(new Phrase("N LOTTO:", font1));
            cellLOT.addElement(new Phrase(LOT, font));
            cellLOT.addElement(code128ImageLOT);

            cellLOT.setBorder(Rectangle.NO_BORDER);
            cellLOT.setPadding(2.0f);
            cellLOT.setFixedHeight(45f);
            ndTab.addCell(cellLOT);

            PdfPCell cellQCK = new PdfPCell();
            cellQCK.addElement(new Phrase("QCK ID:", fontMin));
            cellQCK.setPadding(3.0f);
            cellQCK.setFixedHeight(45f);
            ndTab.addCell(cellQCK);

            PdfPCell cellVCK = new PdfPCell();
            cellVCK.addElement(new Phrase("VCK ID:", fontMin));
            cellVCK.setPadding(3.0f);
            cellVCK.setFixedHeight(45f);
            ndTab.addCell(cellVCK);

            //---------------------------Table 3 assignement----------------------------------------
            Barcode128 codeDtSaisie = barcode128(DT_ENVOIE);

            PdfContentByte cb5 = writer.getDirectContent();
            com.itextpdf.text.Image code128Image5 = codeDtSaisie.createImageWithBarcode(cb5, null, null);
            PdfPCell cellDtSaisie = new PdfPCell();
            cellDtSaisie.addElement(new Phrase("DATA DDT:", font));
            cellDtSaisie.addElement(new Phrase(DT_ENVOIE, font));
            cellDtSaisie.addElement(code128Image5);
            cellDtSaisie.setBorder(Rectangle.NO_BORDER);
            cellDtSaisie.setPadding(5.0f);
            cellDtSaisie.setFixedHeight(53f);
            rdTab.addCell(cellDtSaisie);

            Barcode128 CodeNum = barcode128(BL_NUMERO);
            PdfContentByte cbNum = writer.getDirectContent();
            com.itextpdf.text.Image code128ImageNum = CodeNum.createImageWithBarcode(cbNum, null, null);
            PdfPCell cellNum = new PdfPCell();
            cellNum.addElement(new Phrase("DDT. N:", font));
            cellNum.addElement(new Phrase(BL_NUMERO, font));
            cellNum.addElement(code128ImageNum);
            cellNum.setBorder(Rectangle.NO_BORDER);
            cellNum.setPadding(3.0f);
            cellNum.setFixedHeight(53f);
            rdTab.addCell(cellNum);

            PdfPCell cellQta = new PdfPCell();
            cellQta.addElement(new Phrase("N.BOX: ", font));
            cellQta.addElement(new Phrase("   ", font));
            cellQta.addElement(new Phrase("  "+LBLC_NUMERO_COLIS + "/"+ getMaxNbre(nbreArt,username,password), font2));
            cellQta.setBorder(Rectangle.NO_BORDER);
            cellQta.setPadding(2.0f);
            cellQta.setFixedHeight(53f);
            rdTab.addCell(cellQta);

            PdfPCell cellNote = new PdfPCell();
            cellNote.addElement(new Phrase("NOTE: ", font));
            cellNote.setPadding(5.0f);
            cellNote.setFixedHeight(53f);
            rdTab.addCell(cellNote);

            BarcodeDatamatrix dm = new BarcodeDatamatrix();
            dm.generate(qrcode);
            Image img = dm.createImage();

            PdfPCell cellMat = new PdfPCell(img, true);
            cellMat.setFixedHeight(53f);
            cellMat.setPadding(1f);
            cellMat.setBorder(Rectangle.NO_BORDER);
            cellMat.setHorizontalAlignment(Element.ALIGN_CENTER);
            rdTab.addCell(cellMat);

            //---------------------------Document Fill----------------------------------------
            pdfDocument.add(qrCodeTable);
            pdfDocument.add(new Phrase("\n            ----------------------------------"
                    + "-----------------------------------------------------",
                    font1));
            pdfDocument.add(ndTab);
            pdfDocument.add(rdTab);
        }

        pdfDocument.close();

        return pdfDocument;

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
    
    public static int getMaxNbre(String nbreArt,String username,String password ) throws SQLException, ClassNotFoundException{
        
        ResultSet rs = DatabaseConnection.getInformations(nbreArt,username,password);
        int i = 0;
        while (rs.next()) {
            if (Integer.parseInt(rs.getString(15))>i){
                i = Integer.parseInt(rs.getString(15));
            } 
        }
        
        return i;
        
    }

}
