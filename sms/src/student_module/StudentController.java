package student_module;

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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import entity.Period;
import entity.Student;
import sm.UserInfoServiceImpl;

public class StudentController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	Component form, data;
	@Wire
	Textbox name, address, town, city, landline, cell_no, jclass, value, reg;
	@Wire
	Checkbox status;
	@Wire
	Include sm;
	@Wire
	Datebox adm_date;
	@Wire
	Listbox Listbox;
	private int student_id = 0;
	Intbox sid,stid;
	Tabbox tbx;
	Combobox sdclass, sdsection, sdfee,disc;
	Tab tab, tab2;
	Div detailData, detailForm,disdetailData,disdetailForm;

	private StudentInterface studentImp = new StudentImpl();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		tbx = (Tabbox) sm.getParent().getParent().getParent();
		tab = (Tab) tbx.getFellow("tab_sd");
		tab2 = (Tab) tbx.getFellow("tab_dis");
		tab.setDisabled(true);
		tab2.setDisabled(true);
	}

	@Listen("onClick=#submit;")
	public void Submit() {
		String ischeck = null;
		if (status.isChecked()) {
			ischeck = "Y";
		} else {
			ischeck = "N";
		}
		int result = studentImp.insertStudent(student_id, name.getValue(), reg.getValue(), address.getValue(),
				town.getValue(), city.getValue(), landline.getValue(), cell_no.getValue(), jclass.getValue(),
				adm_date.getValue(), ischeck, UserInfoServiceImpl.userid, value.getValue());
		if (result == 0) {
			Clients.showNotification("Error", "info", form, "middle_center", 1500);
		} else if (result == 3) {
			Clients.showNotification("Registration number already given to another student.", "error", reg, "end_center", 2000);
			reg.focus();
		} else {
			student_id = result;
			
			sid = (Intbox) tbx.getFellow("sd").getFellow("sid");
			tbx.setSelectedTab(tab);
			tab.setDisabled(false);
			tab2.setDisabled(false);
			sid.setValue(result);			
			detailData = (Div) tbx.getFellow("sd").getFellow("data");
			detailForm = (Div) tbx.getFellow("sd").getFellow("form");
			detailData.setVisible(false);
			detailForm.setVisible(true);
			
			stid = (Intbox) tbx.getFellow("dis").getFellow("stid");
			tab2.setDisabled(false);
			stid.setValue(result);
			disdetailData = (Div) tbx.getFellow("dis").getFellow("data");
			disdetailForm = (Div) tbx.getFellow("dis").getFellow("form");
			disdetailData.setVisible(false);
			disdetailForm.setVisible(true);			

			Clients.showNotification(((result == student_id) ? "Data Updated" : "Data Inserted ") + " Succesfully.",
					"info", (Include) tbx.getFellow("sd"), "middle_center", 1500);
			
			disc=(Combobox) tbx.getFellow("dis").getFellow("discount");			
			sdclass = (Combobox) tbx.getFellow("sd").getFellow("sdclass");
			sdsection = (Combobox) tbx.getFellow("sd").getFellow("sdsection");
			sdfee = (Combobox) tbx.getFellow("sd").getFellow("sdfee");
			
			List<String> discounts = studentImp.getDiscounts(sid.getValue());
			disc.setModel(new ListModelList<String>(discounts));
			List<String> classes = studentImp.getClasses(sid.getValue());
			sdclass.setModel(new ListModelList<String>(classes));
			List<String> sections = studentImp.getSections(sid.getValue());
			sdsection.setModel(new ListModelList<String>(sections));
			List<String> fees = studentImp.getFees(sid.getValue());
			sdfee.setModel(new ListModelList<String>(fees));
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

		Student student = (Student) litem.getValue();
		this.fetch();
		student_id = student.getId();
		name.setText(student.getName());
		reg.setText(student.getReg_no());
		address.setText(student.getAddress());
		town.setText(student.getTown());
		city.setText(student.getCity());
		landline.setText(student.getLandline());
		cell_no.setText(student.getCell_no());
		jclass.setText(student.getJclass());
		adm_date.setValue(student.getAdmission());
		if (student.getStatus().equals("Y")) {
			status.setChecked(true);
		} else {
			status.setChecked(false);
		}
		value.setValue(student.getValue());
		
		tab.setDisabled(false);
		sid = (Intbox) tbx.getFellow("sd").getFellow("sid");
		sid.setValue(student_id);
		detailData = (Div) tbx.getFellow("sd").getFellow("data");
		detailForm = (Div) tbx.getFellow("sd").getFellow("form");
		detailData.setVisible(false);
		detailForm.setVisible(true);
		
		tab2.setDisabled(false);
		stid = (Intbox) tbx.getFellow("dis").getFellow("stid");
		stid.setValue(student_id);
		disdetailData = (Div) tbx.getFellow("dis").getFellow("data");
		disdetailForm = (Div) tbx.getFellow("dis").getFellow("form");
		disdetailData.setVisible(false);
		disdetailForm.setVisible(true);
		
		disc=(Combobox) tbx.getFellow("dis").getFellow("discount");			
		sdclass = (Combobox) tbx.getFellow("sd").getFellow("sdclass");
		sdsection = (Combobox) tbx.getFellow("sd").getFellow("sdsection");
		sdfee = (Combobox) tbx.getFellow("sd").getFellow("sdfee");
		
		List<String> discounts = studentImp.getDiscounts(sid.getValue());
		disc.setModel(new ListModelList<String>(discounts));
		List<String> classes = studentImp.getClasses(sid.getValue());
		sdclass.setModel(new ListModelList<String>(classes));
		List<String> sections = studentImp.getSections(sid.getValue());
		sdsection.setModel(new ListModelList<String>(sections));
		List<String> fees = studentImp.getFees(sid.getValue());
		sdfee.setModel(new ListModelList<String>(fees));
	}

	@Listen("onDelete = #Listbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Student student = (Student) litem.getValue();
		studentImp.deleteStudent(student);
		litem.detach();
		Clients.showNotification("Data deleted succesfully.", "info", data, "middle_center", 1500);
	}

	@Listen("onClick=#toggle")
	public void fetch() {
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Student> result = studentImp.getStudent();
			Listbox.setModel(new ListModelList<Student>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		tab.setDisabled(true);
		tab2.setDisabled(true);
		student_id = 0;
		name.setRawValue("");
		reg.setRawValue("");
		address.setRawValue("");
		town.setRawValue("");
		city.setRawValue("");
		landline.setRawValue("");
		cell_no.setRawValue("");
		jclass.setRawValue("");
		value.setRawValue("");
		adm_date.setRawValue(null);
		status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
