package project.controller;

import java.io.File;

import project.gui.additional.NewMemberSettingGui;
import project.gui.main.FrontGui;

public class BridgeController {
	
	public static FrontGui fg;

	public BridgeController(String id) {

		String idPath = "files/membersDataFiles/" + id;
		File idFolder = new File(idPath);

		if (!idFolder.exists())
			new NewMemberSettingGui(id);
		else
			fg = new FrontGui(id);
	}
	
}
