/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SidebarPageConfig {
	/** get pages of this application **/
	public List<ArrayList<SidebarPage>> getPages();
	
	/** get page **/
	public ArrayList<SidebarPage> getPage(String name);
	
	public HashMap<String,ArrayList<SidebarPage>> gethash();
}