package sm;
import java.sql.Types;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;


import entity.var;

public class MainController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	Component form, data;
	@Wire
	Tabbox tbx;
	@Wire
	Tab fd;
	@Wire
	Textbox name, key;

	@Wire
	Listbox detailListbox;
	@Wire
	Checkbox status;

	@Wire
	Button edit, classSubmit;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	@Listen("onClick=#classSubmit;")
	public void classSubmit() {
		try {
			var.cst = var.conn.prepareCall("{? =call zk.add_class(?,?,?,?,?)}");
			var.cst.registerOutParameter(1, Types.VARCHAR);
			var.cst.setInt(2, 3);
			var.cst.setString(3, name.getValue());
			if (status.isChecked()) {
				var.cst.setString(4, "Y");
			} else {
				var.cst.setString(4, "N");
			}
			var.cst.setInt(5, UserInfoServiceImpl.userid);
			var.cst.setString(6, key.getValue());
			var.cst.execute();
			System.out.println(var.cst.getObject(1).toString());
			Clients.showNotification(var.cst.getObject(1).toString());
		} catch (Exception e) {
			System.out.println("false arha hai" + e);
		}

	}
	@Listen("onClick=#submit_fm")
	public void changetab() {
		tbx.setSelectedTab(fd);
	}
	@Listen("onClick=#toggle;")
	public void fetch() {
		if (edit.getLabel().equals("Toggle")) {
			// sql
			data.setVisible(true);
			form.setVisible(false);
			edit.setLabel("New");
		} else {
			data.setVisible(false);
			form.setVisible(true);
			edit.setLabel("Toggle");
		}
	}
}
