/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sahnoun Yusuf
 */
public class model2Info {
    
    public static List<List<String>> FileInfo(String username,String password) throws ClassNotFoundException, SQLException {
        ResultSet rs = DatabaseConnection.getInformationsModel2(username,password);

        List<List<String>> artList = new ArrayList<>();

        while (rs.next()) {
            String LOGO = rs.getString(1);
            String CODE_ARTI = rs.getString(2);
            String arti_plan = rs.getString(3);
            String LBLC_DESIGNATION = rs.getString(4);
            String ARTI_INDICE = rs.getString(5);
            String DT_INDICE = rs.getString(6);
            String LBLC_QTE_A_LIVRER_UV = rs.getString(7);
            String CLNT_NOTRE_REF = rs.getString(8);
            String CDE_CLIENT_REF = rs.getString(9);
            String QTE_LIVRE = rs.getString(10);
            String LOT = rs.getString(11);
            String DT_ENVOIE = rs.getString(12);
            String BL_NUMERO = rs.getString(13);
            String COCL_NUMERO = rs.getString(14);
            String LBLC_NUMERO_COLIS = rs.getString(15);
            List<String> list = new ArrayList<>(
                    Arrays.asList(LOGO, CODE_ARTI, arti_plan, LBLC_DESIGNATION, ARTI_INDICE,
                    DT_INDICE, LBLC_QTE_A_LIVRER_UV, CLNT_NOTRE_REF, CDE_CLIENT_REF, QTE_LIVRE, LOT,
                    DT_ENVOIE, BL_NUMERO, COCL_NUMERO, LBLC_NUMERO_COLIS));
            artList.add(list);
        }

        return artList;
    }
    
}
