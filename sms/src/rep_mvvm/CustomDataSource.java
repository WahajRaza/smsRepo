package rep_mvvm;

import java.util.ArrayList;
import java.util.List;

import entity.Period;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import period_module.PeriodImp;
import period_module.PeriodModule;

public class CustomDataSource implements JRDataSource {

	List<Period> period;
	PeriodModule periodimp=new PeriodImp();
	private int index = -1;

	public CustomDataSource() {
		period=new ArrayList<Period>();
		period=periodimp.getPeriod();		
	}

	public boolean next() throws JRException {
		index++;
		return (index < period.size());
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;
		String fieldName = field.getName();
		
		if ("terms".equals(fieldName)) {
			value = period.get(index).getTerms();
		} else if ("month".equals(fieldName)) {
			value = period.get(index).getMonth();
		} else if ("tdate".equals(fieldName)) {
			value = period.get(index).getTo();
		} else if ("p_active".equals(fieldName)) {
			value = period.get(index).getStatus();
		}
		else if ("fdate".equals(fieldName)) {
			value = period.get(index).getFrom();
		} else if ("tests".equals(fieldName)) {
			value = period.get(index).getTests();
		} else if ("detail_id".equals(fieldName)) {
			value = period.get(index).getDp_id();
		}else if ("name".equals(fieldName)) {
			value = period.get(index).getName();
		} else if ("d_active".equals(fieldName)) {
			value = period.get(index).getD_status();
		}
		System.out.println(value);
		return value;
	}
}
