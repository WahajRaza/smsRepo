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
import entity.Classes;
import interfaces.ClassInterface;
import sm.UserInfoServiceImpl;

public class ClassController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	@Wire
	Checkbox status;
	@Wire
	Textbox name, key;
	@Wire
	Button toggle, classSubmit, New;
	@Wire
	Component form, data;
	@Wire
	Listbox detailListbox;
	int class_id = 0;
	Classes selectedClass;
	ListModelList<Classes> classListModel;
	private ClassInterface classImp = new ClassImpl();
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}


	@Listen("onClick=#classSubmit;")
	public void classSubmit() {
		if (name.getValue() != "" && key.getValue() != "") {
			String ischeck = null;
			if (status.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			String result = classImp.insertClass(class_id, name.getValue(), ischeck, UserInfoServiceImpl.userid,
					key.getValue());
			Clients.showNotification(result + " succesfully.","info", form, "middle_center", 15);
		}
	}

	@Listen("onClassEdit = #detailListbox")
	public void doClassEdit(ForwardEvent evt) {
		Listitem litem = null;
		if (evt.getOrigin().getTarget().isListenerAvailable("onClick", true)) {
			Button btn = (Button) evt.getOrigin().getTarget();
			litem = (Listitem) btn.getParent().getParent();

		} else {
			Listbox listbox = (Listbox) evt.getOrigin().getTarget();
			litem = (Listitem) listbox.getSelectedItem();
		}
		Classes cls = (Classes) litem.getValue();
		this.fetch();
		class_id = cls.getId();
		name.setText(cls.getName());
		key.setText(cls.getValue());
		if (cls.getStatus().equals("Y")) {
			status.setChecked(true);
		} else {
			status.setChecked(false);
		}
	}



	@Listen("onClassDelete = #detailListbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Classes cls = (Classes) litem.getValue();
		litem.detach();
		classImp.deleteClass(cls);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 15);
//		Clients.showN
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			// sql
			data.setVisible(true);
			form.setVisible(false);
			List<Classes> result = classImp.getClasses();
			detailListbox.setModel(new ListModelList<Classes>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		class_id = 0;
		name.setRawValue("");
		key.setRawValue("");
		status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}

}
