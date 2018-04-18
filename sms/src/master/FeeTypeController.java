package master;

import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import entity.FeeTypes;
import interfaces.FeeTypeInterface;
import sm.UserInfoServiceImpl;

public class FeeTypeController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	Checkbox status,rec;
	@Wire
	Textbox name, key;
	@Wire
	Component form, data;
	@Wire
	Listbox detailListbox;
	int feetype_id = 0;
	FeeTypes selectedfeetype;
	ListModelList<FeeTypes> sectionListModel;
	FeeTypeInterface feetypeImp = new FeeTypeImpl();
	
	@Listen("onClick=#Submit;")
	public void feetypeSubmit() {
		if (name.getValue() != "" && key.getValue() != "") {
			String ischeck = null;
			String isrec=null;
			if(rec.isChecked()) {
				isrec="Y";
			}
			else {
				isrec="N";
			}
			if (status.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			String result = feetypeImp.insertFeeType(feetype_id, name.getValue(),isrec, ischeck, UserInfoServiceImpl.userid,
					key.getValue());
			Clients.showNotification(result + " succesfully.","info", form, "middle_center", 15);
		}
	}

	@Listen("onEdit = #detailListbox")
	public void doEdit(ForwardEvent evt) {
		Listitem litem = null;
		if (evt.getOrigin().getTarget().isListenerAvailable("onClick", true)) {
			Button btn = (Button) evt.getOrigin().getTarget();
			litem = (Listitem) btn.getParent().getParent();

		} else {
			Listbox listbox = (Listbox) evt.getOrigin().getTarget();
			litem = (Listitem) listbox.getSelectedItem();
		}
		FeeTypes ft = (FeeTypes) litem.getValue();
		this.fetch();
		feetype_id = ft.getId();
		name.setText(ft.getName());
		key.setText(ft.getValue());
		if (ft.getRec().equals("Y")) {
			rec.setChecked(true);
		} else {
			rec.setChecked(false);
		}
		if (ft.getStatus().equals("Y")) {
			status.setChecked(true);
		} else {
			status.setChecked(false);
		}		
	}



	@Listen("onDelete = #detailListbox")
	public void doDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		FeeTypes ft = (FeeTypes) litem.getValue();
		litem.detach();
		feetypeImp.deleteFeeType(ft);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 15);
//		Clients.showN
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			// sql
			data.setVisible(true);
			form.setVisible(false);
			List<FeeTypes> result = feetypeImp.getFeeTypes();
			detailListbox.setModel(new ListModelList<FeeTypes>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		feetype_id = 0;
		name.setRawValue("");
		key.setValue("");
		rec.setChecked(false);
		status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
