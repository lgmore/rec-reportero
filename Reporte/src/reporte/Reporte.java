package reporte;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class Reporte {  

    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://sbd2.rec.una.py:5432/rh_rec?currentSchema", "lmore", "info1042");
        
        String selectTableSQL = "SELECT nrodoc FROM rh.Personal WHERE nrodoc = '3626419'";
        ResultSet rs = conexion.createStatement().executeQuery(selectTableSQL);
        String userid = "";
        while (rs.next()) {
            userid = rs.getString("nrodoc");
        }
        
        HashMap map = new HashMap();
        map.put("cedula", userid);
        
        File archivo = new File("/home/informatica/NetBeansProjects/Reporte/src/reporte/reporte1.jasper");
        
        JasperReport reporte = (JasperReport) JRLoader.loadObject(archivo);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, map, conexion);
        
        JRExporter exporter = new JRPdfExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
        exporter.exportReport();
    }
    
}