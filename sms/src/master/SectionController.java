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
import entity.Sections;
import interfaces.SectionInterface;
import sm.UserInfoServiceImpl;

public class SectionController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	@Wire
	Checkbox status;
	@Wire
	Textbox name, key;
	@Wire
	Component form, data;
	@Wire
	Listbox detailListbox;
	int section_id = 0;
	Sections selectedSection;
	ListModelList<Sections> sectionListModel;
	SectionInterface sectionImp = new SectionImpl();
	
	@Listen("onClick=#Submit;")
	public void sectionSubmit() {
		if (name.getValue() != "" && key.getValue() != "") {
			String ischeck = null;
			if (status.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			String result = sectionImp.insertSection(section_id, name.getValue(), ischeck, UserInfoServiceImpl.userid,
					key.getValue());
			Clients.showNotification(result + " succesfully.","info", form, "middle_center", 15);
		}
	}

	@Listen("onEdit = #detailListbox")
	public void doSectionEdit(ForwardEvent evt) {
		Listitem litem = null;
		if (evt.getOrigin().getTarget().isListenerAvailable("onClick", true)) {
			Button btn = (Button) evt.getOrigin().getTarget();
			litem = (Listitem) btn.getParent().getParent();

		} else {
			Listbox listbox = (Listbox) evt.getOrigin().getTarget();
			litem = (Listitem) listbox.getSelectedItem();
		}
		Sections sec = (Sections) litem.getValue();
		this.fetch();
		section_id = sec.getId();
		name.setText(sec.getName());
		key.setText(sec.getValue());
		if (sec.getStatus().equals("Y")) {
			status.setChecked(true);
		} else {
			status.setChecked(false);
		}
	}



	@Listen("onDelete = #detailListbox")
	public void doSectionDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Sections sec = (Sections) litem.getValue();
		litem.detach();
		sectionImp.deleteSection(sec);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 15);
//		Clients.showN
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			// sql
			data.setVisible(true);
			form.setVisible(false);
			List<Sections> result = sectionImp.getSections();
			detailListbox.setModel(new ListModelList<Sections>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		section_id = 0;
		name.setRawValue("");
		key.setValue("");
		status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
	
}
