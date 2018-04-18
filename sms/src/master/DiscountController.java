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
import entity.Discount;
import interfaces.DiscountInterface;
import sm.UserInfoServiceImpl;

public class DiscountController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	@Wire
	Checkbox status;
	@Wire
	Textbox name, desc,key;
	@Wire
	Component form, data;
	@Wire
	Listbox detailListbox;
	int discount_id = 0;
	Discount selectedDiscount;
	ListModelList<Discount> sectionListModel;
	DiscountInterface discountImp = new DiscountImpl();
	
	@Listen("onClick=#Submit;")
	public void Submit() {
		if (name.getValue() != "" && key.getValue() != "") {
			String ischeck = null;
			if (status.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			String result = discountImp.insertDiscount(discount_id, name.getValue(),desc.getValue(), ischeck, UserInfoServiceImpl.userid,
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
		Discount dis = (Discount) litem.getValue();
		this.fetch();
		discount_id = dis.getId();
		name.setText(dis.getName());
		desc.setText(dis.getDesc());
		key.setText(dis.getValue());
		if (dis.getStatus().equals("Y")) {
			status.setChecked(true);
		} else {
			status.setChecked(false);
		}
	}



	@Listen("onDelete = #detailListbox")
	public void doDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Discount dis = (Discount) litem.getValue();
		litem.detach();
		discountImp.deleteDiscount(dis);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 15);
//		Clients.showN
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			// sql
			data.setVisible(true);
			form.setVisible(false);
			List<Discount> result = discountImp.getDiscount();
			detailListbox.setModel(new ListModelList<Discount>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		discount_id = 0;
		name.setRawValue("");
		desc.setValue(null);
		key.setValue("");
		status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
