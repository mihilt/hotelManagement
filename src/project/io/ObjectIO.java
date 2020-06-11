package project.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO {

	public void saveObject(Object vo, String Filename) {

		try (FileOutputStream fos = new FileOutputStream(Filename);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(bos);) {

			oos.writeObject(vo);
		} catch (IOException e) {
		}
	}

	public Object loadObject(Object vo, String Filename) {

		try (FileInputStream fis = new FileInputStream(Filename);
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);) {

			vo = ois.readObject();

			return vo;
		} catch (IOException | ClassNotFoundException e) {
		}
		return null;
	}
}
