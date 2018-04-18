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
import org.zkoss.zul.Div;
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

public class FeeController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	Component form, data;
	@Wire
	Textbox value;
	@Wire
	Checkbox status;
	@Wire
	Combobox fperiod,fclass;
	@Wire
	Include fm;
	@Wire
	Listbox Listbox;
	private int fee_id = 0;
	Intbox fid,fgid;
	Tabbox tbx,tbx2;
	Combobox ft;
	Tab tab,tab2;
	Div detailData, detailForm,fgdetailData,fgdetailForm;
	
	private FeeInterface feeImp = new FeeImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		System.out.println("master");
		tbx = (Tabbox) fm.getParent().getParent().getParent();
		tab = (Tab) tbx.getFellow("tab_fd");
		tab.setDisabled(true);
		tbx2 = (Tabbox) fm.getParent().getParent().getParent();		
		tab2 = (Tab) tbx2.getFellow("tab_fg");
		tab2.setDisabled(true);
		List<String> periods = feeImp.getPeriods();
		fperiod.setModel(new ListModelList<String>(periods));
		List<String> classes = feeImp.getClasses();
		fclass.setModel(new ListModelList<String>(classes));
	}
	@Listen("onClick=#submit;")
	public void Submit() {
		Clients.showBusy(form, "Please Wait ...");
		String ischeck = null;
		if (status.isChecked()) {
			ischeck = "Y";
		} else {
			ischeck = "N";
		}
		int result = feeImp.insertFee(fee_id, fperiod.getValue(), fclass.getValue(),
				ischeck, UserInfoServiceImpl.userid, value.getValue());
		if (result == 0) {
			Clients.showNotification("Error", "info", form, "middle_center", 1500);
		} else if (result == 3) {
			Clients.showNotification("Fee Already Exist", "error", fperiod, "end_center", 2000);
		} else {
			Clients.showNotification(
					((result == fee_id) ? "Data Updated" : "Data Inserted ")
							+ " Succesfully.",
					"info", (Include) tbx.getFellow("fd"), "middle_center", 1500);
			fee_id = result;						
			fid = (Intbox) tbx.getFellow("fd").getFellow("fid");
			fgid = (Intbox) tbx.getFellow("fg").getFellow("fgid");			
			tbx.setSelectedTab(tab);
			tab.setDisabled(false);			
			fid.setValue(result);
			fgid.setValue(result);
			detailData = (Div) tbx.getFellow("fd").getFellow("data");
			detailForm = (Div) tbx.getFellow("fd").getFellow("form");
			detailData.setVisible(false);
			detailForm.setVisible(true);
			
//			fgid = (Intbox) tbx.getFellow("fg").getFellow("fgid");
//			tab2.setDisabled(false);
//			fgid.setValue(result);
//			fgdetailData = (Div) tbx.getFellow("fg").getFellow("data");
//			fgdetailForm = (Div) tbx.getFellow("fg").getFellow("form");
//			fgdetailData.setVisible(false);
//			fgdetailForm.setVisible(true);			
			Clients.showNotification((result > 0)? ((result ==fee_id)?"Data Updated":"Data Inserted ")+
					" Succesfully." : "Error" + " ","info",form, "middle_center", 1500);
			ft=(Combobox) tbx.getFellow("fd").getFellow("ft");	
			List<String> feetype = feeImp.getFeetypes(fid.getValue());
			ft.setModel(new ListModelList<String>(feetype));
			ft.setValue(null);
		}
		Clients.clearBusy(form);
	}

	@Listen("onEdit = #Listbox")
	public void doEdit(ForwardEvent evt) {
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
		fee_id = fee.getId();
		fperiod.setText(fee.getFperiod());
		fclass.setText(fee.getFclass());
		if (fee.getStatus().equals("Y")) {
			status.setChecked(true);
		} else {
			status.setChecked(false);
		}
		value.setValue(fee.getValue());		
		tab.setDisabled(false);
		fid = (Intbox) tbx.getFellow("fd").getFellow("fid");
		fgid = (Intbox) tbx.getFellow("fg").getFellow("fgid");
		fid.setValue(fee_id);
		fgid.setValue(fee_id);
		detailData = (Div) tbx.getFellow("fd").getFellow("data");
		detailForm = (Div) tbx.getFellow("fd").getFellow("form");
		detailData.setVisible(false);
		detailForm.setVisible(true);		
		tab2.setDisabled(true);
//		fgid = (Intbox) tbx.getFellow("fg").getFellow("fgid");
//		fgid.setValue(fee_id);
//		fgdetailData = (Div) tbx.getFellow("fg").getFellow("data");
//		fgdetailForm = (Div) tbx.getFellow("fg").getFellow("form");
//		fgdetailData.setVisible(false);
//		fgdetailForm.setVisible(true);
		ft=(Combobox) tbx.getFellow("fd").getFellow("ft");	
		List<String> feetype = feeImp.getFeetypes(fid.getValue());
		ft.setModel(new ListModelList<String>(feetype));
		ft.setValue(null);
	}

	@Listen("onDelete = #Listbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Fee fee = (Fee) litem.getValue();
		feeImp.deleteFee(fee);
		litem.detach();
		Clients.showNotification("Data deleted succesfully.", "info", data, "middle_center", 1500);
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Fee> result = feeImp.getFee();
			Listbox.setModel(new ListModelList<Fee>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		tab.setDisabled(true);
		tab2.setDisabled(true);
		fee_id = 0;
		fperiod.setText("");
		fclass.setText("");
		value.setValue("");
		status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
