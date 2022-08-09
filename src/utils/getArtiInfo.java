/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import utils.DatabaseConnection;

/**
 *
 * @author trator979
 */
public class getArtiInfo {

    public static List<List<String>> FileInfo(String nbreArt,String username,String password) throws ClassNotFoundException, SQLException {
        ResultSet rs = DatabaseConnection.getInformations(nbreArt,username,password);

        List<List<String>> artList = new ArrayList<>();

        while (rs.next()) {
            String LOGO = rs.getString(19);
            String CODE_ARTI = rs.getString(6);
            String arti_plan = rs.getString(4);
            String LBLC_DESIGNATION = rs.getString(3);
            String ARTI_INDICE = rs.getString(5);
            String DT_INDICE = rs.getString(7);
            String LBLC_QTE_A_LIVRER_UV = rs.getString(13);
            String CLNT_NOTRE_REF = rs.getString(39);
            String CDE_CLIENT_REF = rs.getString(11);
            String QTE_LIVRE = rs.getString(20);
            String LOT = rs.getString(37);
            String DT_ENVOIE = rs.getString(38);
            String BL_NUMERO = rs.getString(9);
            String COCL_NUMERO = rs.getString(15);
            String LBLC_NUMERO_COLIS = rs.getString(22);
            String qrcode = rs.getString(40);
            List<String> list = new ArrayList<>(
                    Arrays.asList(LOGO, CODE_ARTI, arti_plan, LBLC_DESIGNATION, ARTI_INDICE,
                    DT_INDICE, LBLC_QTE_A_LIVRER_UV, CLNT_NOTRE_REF, CDE_CLIENT_REF, QTE_LIVRE, LOT,
                    DT_ENVOIE, BL_NUMERO, COCL_NUMERO, LBLC_NUMERO_COLIS, qrcode));
            artList.add(list);
        }

        return artList;
    }

}
