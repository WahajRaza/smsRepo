package rep_mvc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Iframe;

import entity.var;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Jasper extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 1L;
Connection con=null;
	@Wire
	Component div;
	public Jasper() {	  
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://ZK/postgres", "postgres",
					"postgres");
		} catch (Exception e) {
			System.out.println("Connection Failed because " + e);
		}
	}
//	@Wire 
//	Jasperreport report;
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
//		Connection c= var.conn;
		String source="/periodRep.jasper";
		Jasperreport report=(Jasperreport) div.getFellow("report");
		report.setDataConnection(con);
		report.setSrc(source);
		report.setType("pdf");
		System.out.println(con.isClosed() +""+ var.conn.isClosed());
//		if(var.conn.isClosed()) {
//			var.conn.
//		}
//		System.out.println(c);
//		InputStream is=Jasper.class.getResourceAsStream(source);
////		JasperReport report=JasperCompileManager.compileReport(is);
//		JasperPrint jp=JasperFillManager.fillReport("src/periodRep.jasper", null);
//		Iframe frame=(Iframe) div.getFellow("report");
//		JasperExportManager.exportReportToPdfFile(jp,"src/periodRep.jasper");
//		File f = new File("src/periodRep.pdf");
//        byte[] buffer = new byte[(int) f.length()];
//        FileInputStream fs = new FileInputStream(f);
//        fs.read(buffer);
//        fs.close();
//        ByteArrayInputStream is = new ByteArrayInputStream(buffer);
//        AMedia amedia = new AMedia("periodRep.pdf", "pdf", "application/pdf", is);
//        frame.setContent(amedia);
//		JasperViewer jv=new JasperViewer(jp,false);
//		jv.setVisible(true);
	}
}
