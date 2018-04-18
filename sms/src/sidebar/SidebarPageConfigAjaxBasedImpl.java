/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package sidebar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import services.SidebarPage;
import services.SidebarPageConfig;

public class SidebarPageConfigAjaxBasedImpl implements SidebarPageConfig {

	// HashMap<String,SidebarPage> pageMap = new
	// LinkedHashMap<String,SidebarPage>();
	ArrayList<SidebarPage> pageMap1 = new ArrayList<SidebarPage>();
	ArrayList<SidebarPage> pageMap2 = new ArrayList<SidebarPage>();
	ArrayList<SidebarPage> pageMap3 = new ArrayList<SidebarPage>();
	ArrayList<SidebarPage> pageMap4 = new ArrayList<SidebarPage>();
	HashMap<String, ArrayList<SidebarPage>> parent1 = new LinkedHashMap<String, ArrayList<SidebarPage>>();

	public SidebarPageConfigAjaxBasedImpl() {
		pageMap1.add(new SidebarPage("Class", "/imgs/site.png", "/pages/master_entry/class.zul"));
		pageMap1.add(new SidebarPage("Section", "/imgs/demo.png", "/pages/master_entry/section.zul"));
		pageMap1.add(new SidebarPage("Fee Type", "/imgs/doc.png", "/pages/master_entry/fee_type.zul"));
		pageMap1.add(new SidebarPage("Discount", "/imgs/doc.png", "/pages/master_entry/discount.zul"));
		parent1.put("setup", pageMap1);

		pageMap2.add(new SidebarPage("Period Module","/imgs/demo.png","/tabbox/periodmodule.zul"));
		pageMap2.add(new SidebarPage("Student Module", "/imgs/demo.png","/tabbox/studentmodule.zul"));
		pageMap2.add(new SidebarPage("Fee Module", "/imgs/site.png","/tabbox/feemodule.zul"));		
		parent1.put("transaction", pageMap2);

		pageMap3.add(new SidebarPage("Student Master", "/imgs/site.png", "/jasper.zul"));
		pageMap3.add(new SidebarPage("Student Details", "/imgs/demo.png", "/pages/student_module/student_detail.zul"));
		pageMap3.add(new SidebarPage("Student Discount", "/imgs/doc.png","/pages/student_module/student_discount.zul"));
		pageMap3.add(new SidebarPage("Student Fine", "/imgs/doc.png","/pages/student_module/student_fine.zul"));
		parent1.put("reports", pageMap3);
	}

	public List<ArrayList<SidebarPage>> getPages() {
		return new ArrayList<ArrayList<SidebarPage>>(parent1.values());
	}

	public HashMap<String, ArrayList<SidebarPage>> gethash() {
		return new HashMap<String, ArrayList<SidebarPage>>(parent1);
	}

	public ArrayList<SidebarPage> getPage(String name) {

		return parent1.get(name);
	}

}