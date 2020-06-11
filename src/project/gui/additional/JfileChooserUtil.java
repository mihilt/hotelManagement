package project.gui.additional;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class JfileChooserUtil {

	public static String jFileChooserUtil() {

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));

		chooser.setAcceptAllFileFilterUsed(false);

		chooser.setDialogTitle("탐색");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("png 파일", "png");
		FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("jpg 파일", "jpg");

		chooser.addChoosableFileFilter(pngFilter);
		chooser.addChoosableFileFilter(jpgFilter);

		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			folderPath = "";
		}

		return folderPath;

	}

}
