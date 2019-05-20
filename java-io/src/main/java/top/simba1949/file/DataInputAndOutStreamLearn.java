package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 22:08
 */
public class DataInputAndOutStreamLearn {
	public static void main(String[] args) {
		readAndWrite();
	}

	public static void readAndWrite(){
		// 1.创建源
		// 2.选择流
		DataOutputStream os = null;
		DataInputStream is = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			os = new DataOutputStream(baos);
			// 3.操作流
			os.writeUTF("长城在");
			os.writeBoolean(false);
			os.writeChar(97);
			os.writeDouble(1.00);
			byte[] bytes = baos.toByteArray();

			// 读取的顺序和写入的顺序一致
			is = new DataInputStream(new ByteArrayInputStream(bytes));

			String s = is.readUTF();
			boolean b = is.readBoolean();
			char c = is.readChar();
			double v = is.readDouble();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.关闭流
			if (null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}