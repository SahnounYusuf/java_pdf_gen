/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceolec;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import BackUp.PdfGener;
import services.StickerChooser;
import utils.getArtiInfo;
import utils.DatabaseConnection;

/**
 *
 * @author trator979
 */
public class pdfGen extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();

    public void updateTable(String blclNbre) throws ClassNotFoundException, SQLException {

        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        ResultSet rs = DatabaseConnection.getInformations(blclNbre,userVar.getText(),passVar.getText());

        try {

            Object[] columnData = new Object[15];

            while (rs.next()) {
                columnData[0] = rs.getString(6);
                columnData[1] = rs.getString(4);
                columnData[2] = rs.getString(3);
                columnData[3] = rs.getString(5);
                columnData[4] = rs.getString(7);
                columnData[5] = rs.getString(13);
                columnData[6] = rs.getString(39);
                columnData[7] = rs.getString(11);
                columnData[8] = rs.getString(20);
                columnData[9] = rs.getString(37);
                columnData[10] = rs.getString(38);
                columnData[11] = rs.getString(9);
                columnData[12] = rs.getString(15);
                columnData[13] = rs.getString(22);
                columnData[14] = rs.getString(40);
                model.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("database error: " + e);
        }
    }

    /**
     * Creates new form pdfGen
     */
    public pdfGen() throws ClassNotFoundException {
        initComponents();

        Object col[] = {"CODE_ARTI", "arti_plan", "LBLC_DESIGNATION", "ARTI_INDICE",
            "DT_INDICE", "LBLC_QTE_A_LIVRER_UV", "CLNT_NOTRE_REF", "CDE_CLIENT_REF",
            "QTE_LIVRE","LOT", "DT_ENVOIE", "BL_NUMERO", "COCL_NUMERO","LBLC_NUMERO_COLIS",
                "qrcode"};
        model.setColumnIdentifiers(col);
        table.setModel(model);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numero_var = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userVar = new javax.swing.JTextField();
        passVar = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 63));

        jLabel1.setText("blcl_numero");

        numero_var.setToolTipText("Product Code");

        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genTable(evt);
            }
        });

        jButton1.setText("Save File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFile(evt);
            }
        });

        jButton2.setText("Print Info");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInfo(evt);
            }
        });

        jLabel2.setText("Login");

        jLabel3.setText("Password");

        userVar.setToolTipText("username");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numero_var, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(userVar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerate)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(passVar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userVar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passVar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero_var, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFile
        // TODO add your handling code here:

        String var = numero_var.getText();
        String user = userVar.getText();
        String pass = passVar.getText();

        if (user.isEmpty()|| pass.isEmpty()) {//checking the date is null or not
            JOptionPane.showMessageDialog(null, "Invalid Username or password ...");

        } else {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Save Backup");
            chooser.setApproveButtonText("Save");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

                System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                System.out.print("getSelectedFile() : " + chooser.getSelectedFile());
                try {
                    StickerChooser.chooser(var,chooser.getSelectedFile(),user,pass);
                } catch (DocumentException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(pdfGen.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Report Saved...");

            }
        }

    }//GEN-LAST:event_saveFile

    private void genTable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genTable
        // TODO add your handling code here:
        String var = numero_var.getText();
        String user = userVar.getText();
        String pass = passVar.getText();

        if (user.isEmpty()|| pass.isEmpty())
            JOptionPane.showMessageDialog(null, "Invalid Username or password ...");
        else{
            try {
                updateTable(var);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(pdfGen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_genTable

    private void printInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInfo
        try {
            // TODO add your handling code here:
            getArtiInfo.FileInfo(numero_var.getText(),userVar.getText(),passVar.getText()).forEach(System.out::println);
            List<List<String>> artList = getArtiInfo.FileInfo(numero_var.getText(),userVar.getText(),passVar.getText());
            for (List<String> strings : artList) {
                System.out.println(strings.get(0));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(pdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printInfo

    /**
     * @param args the command line arguments
     */
    public static void pdfGen() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pdfGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new pdfGen().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(pdfGen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField numero_var;
    private javax.swing.JPasswordField passVar;
    private javax.swing.JTable table;
    private javax.swing.JTextField userVar;
    // End of variables declaration//GEN-END:variables

}
