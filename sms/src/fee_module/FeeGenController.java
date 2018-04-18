package fee_module;

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
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import entity.Fee;
import sm.UserInfoServiceImpl;

public class FeeGenController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;	
	@Wire 
	Textbox fgvalue,fgtid;
	@Wire
	Intbox fgid,fgdid,fee_amount,dis_amount,rec_amount;
	@Wire
	Component form,data;
	@Wire
	Include fg;
	@Wire
	Combobox fgsid;
	@Wire
	Checkbox fgstatus;
	@Wire
	Listbox Listbox;
	int fgen_id=0;
	private FeeInterface feeImp = new FeeImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		List<String> student = feeImp.getStudents();
		fgsid.setModel(new ListModelList<String>(student));
	}
	
	@Listen("onClick=#submit;")
	public void Submit() {
			String ischeck = null;
			if (fgstatus.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			int result = feeImp.insertFeeGen(fgen_id,fgid.intValue(),fgdid.intValue(),
					fgtid.getValue(),fgsid.getValue(),fee_amount.intValue(),dis_amount.intValue(),
					rec_amount.intValue(),ischeck,UserInfoServiceImpl.userid, 
					fgvalue.getValue());
			if (result == 0) {
				Clients.showNotification("Error", "info", form, "middle_center", 1500);
			} else if (result == 3) {
				Clients.showNotification("Fee Already Exist", "error", fgsid, "end_center", 2000);
			} else {
				Clients.showNotification(
						((result == fgen_id) ? "Data Updated" : "Data Inserted ")
								+ " Succesfully.",
						"info", fg, "middle_center", 1500);
			fgen_id = result;
			List<String> student = feeImp.getStudents();
			fgsid.setModel(new ListModelList<String>(student));
			this.fieldsEmpty();}
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
		Fee fee = (Fee) litem.getValue();
		this.fetch();
		fgen_id = fee.getId();
		fgsid.setValue(fee.getFgsm());
		fee_amount.setValue(fee.getF_amount());
		dis_amount.setValue(fee.getDis_amount());
		rec_amount.setValue(fee.getRec_amount());
		fgvalue.setText(fee.getFgvalue());
		if (fee.getFgstatus().equals("Y")) {
			fgstatus.setChecked(true);
		} else {
			fgstatus.setChecked(false);
		}			
		List<String> student = feeImp.getStudents();
		fgsid.setModel(new ListModelList<String>(student));
	}

	@Listen("onDelete = #Listbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Fee fee = (Fee) litem.getValue();
		litem.detach();
		feeImp.deleteFeeGen(fee);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 1500);
		List<String> student = feeImp.getStudents();
		fgsid.setModel(new ListModelList<String>(student));
	}
	
	@Listen("onClick=#toggle")
	public void fetch() {	
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Fee> result = feeImp.getFeeGen(fgid.getValue(),fgdid.getValue());			
			Listbox.setModel(new ListModelList<Fee>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
		List<String> student = feeImp.getStudents();
		fgsid.setModel(new ListModelList<String>(student));
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		fgen_id = 0;	
		fgsid.setValue(null);
		fee_amount.setValue(null);
		dis_amount.setValue(null);
		rec_amount.setValue(null);
		fgvalue.setValue(null);
		fgstatus.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
