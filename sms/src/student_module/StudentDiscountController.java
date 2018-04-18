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

public class StudentDiscountController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	@Wire 
	Textbox dis_value;
	@Wire
	Component form,data;
	@Wire 
	Intbox year,stid;
	@Wire
	Combobox discount;
	@Wire
	Checkbox dis_status;
	@Wire
	Listbox Listbox;
	
	int sdiscount_id=0;
	
	private StudentInterface studentImp = new StudentImpl();
	
	@Listen("onClick=#submit;")
	public void Submit() {
			String ischeck = null;
			if (dis_status.isChecked()) {
				ischeck = "Y";
			} else {
				ischeck = "N";
			}
			int result = studentImp.insertStudentDiscount(sdiscount_id,stid.getValue(),year.getValue(),discount.getValue(),ischeck,UserInfoServiceImpl.userid, 
					dis_value.getValue());
			Clients.showNotification((result > 0)? ((result ==sdiscount_id)?"Data Updated":"Data Inserted ")+" Succesfully." : "Error" + " ","info",form, "middle_center", 1500);
			sdiscount_id = result;
			List<String> disc = studentImp.getDiscounts(stid.getValue());
			discount.setModel(new ListModelList<String>(disc));
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
		Student student = (Student) litem.getValue();
		this.fetch();
		sdiscount_id = student.getId();
		year.setValue(student.getYear());
		discount.setValue(student.getDiscount());
		dis_value.setText(student.getDis_value());
		if (student.getDis_status().equals("Y")) {
			dis_status.setChecked(true);
		} else {
			dis_status.setChecked(false);
		}
		List<String> disc = studentImp.getDiscounts(stid.getValue());
		discount.setModel(new ListModelList<String>(disc));
	}

	@Listen("onDelete = #Listbox")
	public void doTodoDelete(ForwardEvent evt) {
		Button btn = (Button) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent();
		Student student = (Student) litem.getValue();
		litem.detach();
		studentImp.deleteStudentDiscount(student);
		Clients.showNotification("Data deleted succesfully.","info", data, "middle_center", 1500);
		List<String> disc = studentImp.getDiscounts(stid.getValue());
		discount.setModel(new ListModelList<String>(disc));
	}
	
	@Listen("onClick=#toggle")
	public void fetch() {	
		if (!data.isVisible()) {
			data.setVisible(true);
			form.setVisible(false);
			List<Student> result = studentImp.getStudentDiscount(stid.getValue());			
			Listbox.setModel(new ListModelList<Student>(result));
		} else {
			data.setVisible(false);
			form.setVisible(true);
		}
		List<String> disc = studentImp.getDiscounts(stid.getValue());
		discount.setModel(new ListModelList<String>(disc));
	}

	@Listen("onClick=#New")
	public void fieldsEmpty() {
		sdiscount_id = 0;
		year.setRawValue(null);
		discount.setRawValue("");
		dis_value.setRawValue("");
		dis_status.setChecked(false);
		data.setVisible(false);
		form.setVisible(true);
	}
}
