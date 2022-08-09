/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author trator979
 */
public class DatabaseConnection {

//    public static String dbURL = "jdbc:oracle:thin:@SRV-COELEC-S1:1521:dbcoelec";
    public static String dbURL = "jdbc:oracle:thin:@SERV-MSI01:1521:DBMSIC";
//    public static String username = "GPCOELEC";
//    public static String password = "OCTAL";

    public static Connection getConnection(String username, String password) throws ClassNotFoundException {

        Connection db = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            db = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return db;
    }

    public static ResultSet getInformations(String nbreArt, String username, String password) throws ClassNotFoundException, SQLException {
        Connection db = DatabaseConnection.getConnection(username, password);

        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery("select row_number() over ( partition by LBLC_LIGNE order by LBLC_LIGNE ) cpt,\n"
                + "arti_libelle_court||' - '||LBLC_DESIGNATION,\n"
                + "LBLC_DESIGNATION, \n"
                + "'*'||arti_plan||'*' arti_plan,\n"
                + "ARTI_INDICE,\n"
                + "'*'||LBLC_ARTI_CODE_LIVRE||'*'  CODE_ARTI,\n"
                + "to_char(ARTI_USER_DT1,'YYYY-MM-DD') DT_INDICE,\n"
                + "  LBLC_BLCL_NUMERO,\n"
                + "'*'||LBLC_BLCL_NUMERO||'*' BL_NUMERO,\n"
                + "'*'||LBLC_CDCL_NUMERO||'*' CDE_NUMERO,\n"
                + "'*'||pk_cdcl.getReferenceClient(LBLC_CDCL_NUMERO)||'*' CDE_CLIENT_REF,\n"
                + "  LBLC_LIGNE ,                             \n"
                + "      LICC_QTE * (nvl(COCL_NUMERO_COLIS_FIN,0) - nvl(COCL_NUMERO_COLIS_DEBUT,0) + 1) LBLC_QTE_A_LIVRER_UV ,         \n"
                + "COCL_NUMERO_COLIS_FIN,\n"
                + "COCL_NUMERO,\n"
                + "      LBLC_UNIT_CODE_UV,\n"
                + "      LBLC_COEF_UV_UE,\n"
                + "      LBLC_ARTI_CODE_LIVRE,\n"
                + "      ARTI_USER_CAR1 LOGO,\n"
                + "'*'||LBLC_QTE_A_LIVRER_UV||'*' QTE_LIVRE,\n"
                + "      unit_libelle          UV,\n"
                + "      LICC_COCL_NUMERO         LBLC_NUMERO_COLIS,\n"
                + "    blcl_clnt_code,\n"
                + "    LBLC_CDCL_NUMERO,\n"
                + "    LBLC_LCCL_LIGNE,\n"
                + "      BLCL_USER_NUM1,\n"
                + "      BLCL_USER_NUM2,\n"
                + "    BLCL_USER_CAR2,\n"
                + "CLNT_NOM||CLNT_COMPL_NOM CLAD_NOM,\n"
                + "    CLAD_ADRESSE1,\n"
                + "    CLAD_ADRESSE2,\n"
                + "    CLAD_ADRESSE3,\n"
                + "    CLAD_PAYS_CODE,\n"
                + "    PAYS_LIBELLE,\n"
                + "    BLCL_DT_ENVOI,\n"
                + "'*'||BLCL_DT_ENVOI||'*' DT_ENVOIE_CB,\n"
                + "'*'||to_char(BLCL_DT_ENVOI,'IW/IY/D')||'*' LOT,\n"
                + "'*'||BLCL_DT_ENVOI||'*' DT_ENVOIE,\n"
                + "'*'||CLNT_NOTRE_REF||'*'  CLNT_NOTRE_REF,\n"
                + "arti_code||'%'||to_char(ARTI_USER_DT1,'YY/MM/D')\n"
                + "        ||'%'||CLNT_NOTRE_REF||'%'|| ARTI_INDICE||'%' qrcode\n"
                + "from     ligne_bl_client,\n"
                + "    bl_client,\n"
                + "    ligne_colisage_client,\n"
                + "    colisage_client,\n"
                + "    client_adresse,\n"
                + "    pays,\n"
                + "    article,\n"
                + "    unite,\n"
                + "client\n"
                + "where     LBLC_BLCL_NUMERO = BLCL_NUMERO\n"
                + "and     LICC_BLCL_NUMERO = BLCL_NUMERO\n"
                + "and     LICC_LBLC_LIGNE = LBLC_LIGNE\n"
                + "--\n"
                + "and     arti_code = LBLC_ARTI_CODE_LIVRE\n"
                + "and     LBLC_UNIT_CODE_UV = unit_code\n"
                + "and     COCL_BLCL_NUMERO = BLCL_NUMERO\n"
                + "and     COCL_NUMERO = LICC_COCL_NUMERO\n"
                + "and     LBLC_QTE_A_LIVRER_UV <> 0\n"
                + "-- --\n"
                + "--Adresse client --\n"
                + "and blcl_clnt_code  = clnt_code \n"
                + "and        blcl_clnt_code  = clad_clnt_code\n"
                + "and        CLAD_NUMERO = BLCL_CLAD_NUMERO\n"
                + "and       CLAD_PAYS_CODE  = PAYS_CODE\n"
                + "and    LBLC_BLCL_NUMERO like \n"
                + "'" + nbreArt + "%'\n"
                + "--and LICC_COCL_NUMERO  like :p_2\n"
                + "order by LBLC_NUMERO_COLIS");
        System.out.println("Query excuted");
//        ResultSet rs = stmt.executeQuery("select BLCL_CLNT_CODE, CLNT_NOM, lblc_blcl_numero,  to_char(BLCL_DT_SAISIE,'DD/MM/YYYY') data_ddt,  lblc_ligne, lblc_cdcl_numero, "
//                + "lblc_lccl_ligne,LBLC_QTE_A_LIVRER_UV,\n"
//                + "LBLC_ARTI_CODE_LIVRE, LBLC_DESIGNATION  ,\n"
//                + "lblc_blcl_numero||'%'||BLCL_DT_SAISIE||'%'||lblc_ligne||'%'||lblc_cdcl_numero||'%'||lblc_lccl_ligne||'%'||LBLC_QTE_A_LIVRER_UV||'%'||LBLC_ARTI_CODE_LIVRE DATAMATRIX\n"
//                + "  from ligne_bl_client, bl_client, client\n"
//                + "  where lblc_blcl_numero = blcl_numero\n"
//                + "  and BLCL_CLNT_CODE = clnt_code\n"
//                + "  and blcl_numero = '" + nbreArt
//                + "'");

        return rs;
    }

    public static ResultSet getInformationsModel2(String username, String password) throws ClassNotFoundException, SQLException {
        Connection db = DatabaseConnection.getConnection(username, password);

        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery("select ETIQ_NUMERO, \n"
                + "ETIQ_JETON_EDITION, \n"
                + "ETIQ_TYET_CODE,\n"
                + "ETIQ_DT_CREATION, \n"
                + "ETIQ_PERS_INITIALE, \n"
                + "ETIQ_TEXTE1 nous,  \n"
                + "ETIQ_TEXTE2 titre, \n"
                + "ETIQ_TEXTE3 coda,\n"
                + "ETIQ_TEXTE4 indice,\n"
                + "ETIQ_TEXTE5 designation, \n"
                + "ETIQ_TEXTE6 refcli, \n"
                + "ETIQ_TEXTE7 qte, \n"
                + "ETIQ_TEXTE8 nof,\n"
                + "ETIQ_TEXTE9 refcdecli,\n"
                + "jopa_ligne NUM_EMBALLAGE\n"
                + "from ETIQUETTE, job_parameter ");
        return rs;
    }

}
