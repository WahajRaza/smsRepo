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

public class FeeDetailController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	Textbox dvalue;
	@Wire
	Intbox fid;
	@Wire
	Component form, data;
	@Wire
	Combobox ft;
	@Wire
	Include fd;
	@Wire
	Checkbox dstatus;
	@Wire
	Listbox Listbox;
	int fdetail_id = 0;
	Intbox fgid, fgdid;
	Textbox fgtid;
	Tabbox tbx2;
	Tab tab2;
	Div detailData, detailForm, fgdetailData, fgdetailForm;
	private FeeInterface feeImp = new FeeImpl();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		tbx2 = (Tabbox) fd.getParent().getParent().getParent();
		tab2 = (Tab) tbx2.getFellow("tab_fg");
		tab2.setDisabled(true);
		System.out.println("detail");
	}

	@Listen("onClick=#submit;")
	public void Submit() {
		String ischeck = null;
		if (dstatus.isChecked()) {
			ischeck = "Y";
		} else {
			ischeck = "N";
		}
		int result = feeImp.insertFeeDetail(fdetail_id, fid.getValue(), ft.getValue(), ischeck,
				UserInfoServiceImpl.userid, dvalue.getValue());
		if (result == 0) {
			Clients.showNotification("Error", "info", form, "middle_center", 1500);
		} else if (result == 3) {
			Clients.showNotification("Fee Already Exist", "error", ft, "end_center", 2000);
		} else {
			Clients.showNotification(
					((result == fdetail_id) ? "Data Updated" : "Data Inserted ")
							+ " Succesfully.",
					"info", fd, "middle_center", 1500);
			fdetail_id = result;
			tab2.setDisabled(false);
			fgid = (Intbox) tbx2.getFellow("fg").getFellow("fgid");
			fgdid = (Intbox) tbx2.getFellow("fg").getFellow("fgdid");
			fgtid = (Textbox) tbx2.getFellow("fg").getFellow("fgtid");
			fgid.setValue(fid.getValue());
			fgdid.setValue(result);
			fgtid.setValue(ft.getValue());
			detailData = (Div) tbx2.getFellow("fg").getFellow("data");
			detailForm = (Div) tbx2.getFellow("fg").getFellow("form");
			detailData.setVisible(false);
			detailForm.setVisible(true);
			List<String> feetype = feeImp.getFeetypes(fid.getValue());
			ft.setModel(new ListModelList<String>(feetype));
		}
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
		tab2.setDisabled(false);
		fdetail_id = fee.getId();
		ft.setValue(fee.getFee_type());
		dvalue.setText(fee.getDvalue());
		if (fee.getDstatus().equals("Y")) {
			dstatus.setChecked(true);
		} else {
			dstatus.setChecked(false);
		}
		fgid = (Intbox) tbx2.getFellow("fg").getFellow("fgid");
		fgdid = (Intbox) tbx2.getFellow("fg").getFellow("fgdid");
		fgtid = (Textbox) tbx2.getFellow("fg").getFellow("fgtid");
		fgid.setValue(fid.getValue());
		fgdid.setValue(fdetail_id);
		fgtid.setValue(ft.getValue());
		List<String> feetypes = feeImp.getFeetypes(fid.getValue());
		ft.setModel(new ListModelList<String>(feetypes));
	}

	@Listen("onDelete = #Listbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Fee fee = (Fee) litem.getValue();
		litem.detach();
		feeImp.deleteFeeDetail(fee);
		Clients.showNotification("Data deleted succesfully.", "info", data, "middle_center", 1500);
		List<String> feetypes = feeImp.getFeetypes(fid.getValue());
		ft.setModel(new ListModelList<String>(feetypes));
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Fee> result = feeImp.getFeeDetail(fid.getValue());
			Listbox.setModel(new ListModelList<Fee>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
		List<String> feetypes = feeImp.getFeetypes(fid.getValue());
		ft.setModel(new ListModelList<String>(feetypes));
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		fdetail_id = 0;
		tab2.setDisabled(true);
		ft.setValue(null);
		dvalue.setValue("");
		dstatus.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}

}
