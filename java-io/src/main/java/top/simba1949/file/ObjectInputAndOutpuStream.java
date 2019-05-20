package top.simba1949.file;

import top.simba1949.common.User;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 22:40
 */
public class ObjectInputAndOutpuStream {
	public static void main(String[] args) {
		encodeAndDecode();
	}

	public static void encodeAndDecode(){
		// 1.创建源
		User user = new User();
		user.setUsername("李白");
		user.setId(18);
		user.setFlag(true);
		// 2.选择流
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			// 3.操作流
			oos.writeObject(user);
			byte[] bytes = baos.toByteArray();

			ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			User decodeUser = (User) ois.readObject();
			System.out.println(decodeUser);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			if (null != oos){
				// 4.关闭流
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != ois){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
