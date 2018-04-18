package rep_mvvm;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkex.zul.Jasperreport;

import entity.var;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class IReport extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	@Wire
	Component div;
	JasperPrint jasperPrint;
	 JasperReport report;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
//		JasperReport report = (JasperReport) div.getFellow("report");
		report = JasperCompileManager.compileReport(
				"/reports/periodreport.jrxml");
				jasperPrint = JasperFillManager.fillReport(
				report, new HashMap(), new JREmptyDataSource());
				JasperExportManager.exportReportToPdfFile(
				jasperPrint, "/rep/periodreport.pdf");
		
//		div.appendChild(rep);
//		HashMap<String, Object> param = new HashMap<String, Object>();
//		param.put("title", "2013");
//		 System.out.println("wahaj exception");
//		// zul component
//		JasperCompileManager.compileReport("C:\\Users\\Administrator\\Documents\\sms\\src\\periodreport.jrxml");		
		 
//		report.setDataConnection(var.conn);
//		report.setParameters(param);
//		report.setSrc("/periodreport.jasper");
	}
	
}
