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
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import entity.Student;
import sm.UserInfoServiceImpl;

public class StudentDetailController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	@Wire 
	Textbox dvalue;
	@Wire
	Component form,data;
	@Wire 
	Intbox sid,roll_no;
	@Wire
	Combobox sdclass,sdsection,sdfee;
	@Wire
	Checkbox dstatus;
	@Wire
	Listbox Listbox;
	int sdetail_id=0;
	private StudentInterface studentImp = new StudentImpl();
	@Listen("onClick=#submit;")
	public void Submit() {
			String ischeck = null;
			if (dstatus.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			int result = studentImp.insertStudentDetail(sdetail_id,sid.getValue(),roll_no.intValue(), sdclass.getValue(),
					sdsection.getValue(),sdfee.getValue(),ischeck,UserInfoServiceImpl.userid, 
					dvalue.getValue());
			Clients.showNotification((result > 0)? ((result ==sdetail_id)?"Data Updated":"Data Inserted ")+" Succesfully." : "Error" + " ","info",form, "middle_center", 1500);
			sdetail_id = result;
			List<String> classes = studentImp.getClasses(sid.getValue());
			sdclass.setModel(new ListModelList<String>(classes));
			List<String> sections = studentImp.getSections(sid.getValue());
			sdsection.setModel(new ListModelList<String>(sections));
			List<String> fees = studentImp.getFees(sid.getValue());
			sdfee.setModel(new ListModelList<String>(fees));
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
		Student student = (Student) litem.getValue();
		this.fetch();
		sdetail_id = student.getId();
		roll_no.setValue(student.getRoll_no());
		sdclass.setValue(student.getClass_id());
		sdsection.setValue(student.getSection_id());
		sdfee.setValue(student.getFee_id());
		dvalue.setText(student.getD_value());
		if (student.getD_status().equals("Y")) {
			dstatus.setChecked(true);
		} else {
			dstatus.setChecked(false);
		}
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
		litem.detach();
		studentImp.deleteStudentDetail(student);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 1500);
		List<String> classes = studentImp.getClasses(sid.getValue());
		sdclass.setModel(new ListModelList<String>(classes));
		List<String> sections = studentImp.getSections(sid.getValue());
		sdsection.setModel(new ListModelList<String>(sections));
		List<String> fees = studentImp.getFees(sid.getValue());
		sdfee.setModel(new ListModelList<String>(fees));
	}
	
	@Listen("onClick=#toggle")
	public void fetch() {	
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Student> result = studentImp.getStudentDetail(sid.getValue());			
			Listbox.setModel(new ListModelList<Student>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
		List<String> classes = studentImp.getClasses(sid.getValue());
		sdclass.setModel(new ListModelList<String>(classes));
		List<String> sections = studentImp.getSections(sid.getValue());
		sdsection.setModel(new ListModelList<String>(sections));
		List<String> fees = studentImp.getFees(sid.getValue());
		sdfee.setModel(new ListModelList<String>(fees));
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		sdetail_id = 0;
		dvalue.setRawValue("");
		roll_no.setRawValue(null);
		sdclass.setRawValue(null);
		sdsection.setRawValue(null);
		sdfee.setRawValue(null);
		dstatus.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}

}
