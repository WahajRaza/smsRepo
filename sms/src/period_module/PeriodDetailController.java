package period_module;


import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import entity.Period;
import sm.UserInfoServiceImpl;

public class PeriodDetailController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire 
	Textbox dkey;
	@Wire
	Component form,data;
	@Wire 
	Intbox pid;
	@Wire
	Combobox pdmonth;
	@Wire
	Checkbox dstatus;
	@Wire
	Listbox Listbox;
	int pdetail_id=0;
	private PeriodModule periodImp = new PeriodImp();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
System.out.println("call");
	}
	@Listen("onClick=#submit;")
	public void periodSubmit() {
			String ischeck = null;
			if (dstatus.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			int result = periodImp.insertPeriodDetail(pdetail_id,pid.getValue(), pdmonth.getValue().toString(),UserInfoServiceImpl.userid, ischeck,
					dkey.getValue());
			Clients.showNotification((result > 0)? ((result ==pdetail_id)?"Data Updated":"Data Inserted ")+" Succesfully." : "Error" + " ","info",form, "middle_center", 1500);
			pdetail_id = result;
			List<String> months = periodImp.getMonths(pid.getValue());
			pdmonth.setModel(new ListModelList<String>(months));
			this.fieldsEmpty();
	}

	@Listen("onEdit = #Listbox")
	public void doPeriodEdit(ForwardEvent evt) {
		Listitem litem = null;
		if (evt.getOrigin().getTarget().isListenerAvailable("onClick", true)) {
			Button btn = (Button) evt.getOrigin().getTarget();
			litem = (Listitem) btn.getParent().getParent();

		} else {
			Listbox listbox = (Listbox) evt.getOrigin().getTarget();
			litem = (Listitem) listbox.getSelectedItem();
		}
		Period period = (Period) litem.getValue();
		this.fetch();
		pdetail_id = period.getId();
		pdmonth.setText(period.getMonth());
		dkey.setText(period.getD_value());
		
		if (period.getD_status().equals("Y")) {
			dstatus.setChecked(true);
		} else {
			dstatus.setChecked(false);
		}
		List<String> months = periodImp.getMonths(pid.getValue());
		pdmonth.setModel(new ListModelList<String>(months));
	}

	@Listen("onDelete = #Listbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Period period = (Period) litem.getValue();
		litem.detach();
		periodImp.deletePeriodDetail(period);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 1500);
		
		List<String> months = periodImp.getMonths(pid.getValue());
		pdmonth.setModel(new ListModelList<String>(months));
		
		
	}
	
	@Listen("onClick=#toggle")
	public void fetch() {
		List<String> months = periodImp.getMonths(pid.getValue());
		pdmonth.setModel(new ListModelList<String>(months));
		
		
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Period> result = periodImp.getPeriodDetail(pid.getValue());
			
			Listbox.setModel(new ListModelList<Period>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		pdetail_id = 0;
		pdmonth.setRawValue(null);
		dkey.setValue("");
		dstatus.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
