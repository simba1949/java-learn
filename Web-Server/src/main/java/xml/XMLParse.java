package xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author SIMBA1949
 * @date 2019/5/29 22:10
 */
public class XMLParse {

	public XMLParse() {
		this.getDocEleByIterator();
	}

	public static void main(String[] args) {
		XMLParse xmlParse = new XMLParse();
		xmlParse.getDocEleByIterator();
	}

	/**类内共享变量*/
	private static final String SERVLET_NAME = "servlet-name";
	private static final String SERVLET_CLASS = "servlet-class";
	private static final String URL_PATTERN = "url-pattern";
	public static Map<String, String> servletNameAndClass = new HashMap<>();
	public static Map<String, String> servletUrlAndName = new HashMap<>();

	/**
	 * 通过遍历访问元素
	 * @throws DocumentException
	 */
	public void getDocEleByIterator() {
		// 创建 SAXReader 对象
		SAXReader saxReader = new SAXReader();
		// 获取编译后的classes目录路径
		String path = XMLParse.class.getClassLoader().getResource("").getPath();
		// 解析XML生成 Documet 对象
		Document document = null;
		try {
			document = saxReader.read(new File(path + "/xml/web.xml"));
		} catch (DocumentException e) {
			System.out.println("the file is not found");
			e.printStackTrace();
		}
		// 获取根标签
		Element root = document.getRootElement();
		// 通过根标签遍历
		root.elementIterator().forEachRemaining(element -> {
			AtomicReference<String> servletName = new AtomicReference<>("");
			AtomicReference<String> servletClass = new AtomicReference<>("");
			AtomicReference<String> urlPattern = new AtomicReference<>("");

			element.elementIterator().forEachRemaining(eleItem -> {
				if (SERVLET_NAME.equals(eleItem.getName())){
					servletName.set(eleItem.getStringValue().trim());
				}
				if (SERVLET_CLASS.equals(eleItem.getName())){
					servletClass.set(eleItem.getStringValue().trim());
				}
				if (URL_PATTERN.equals(eleItem.getName())){
					urlPattern.set(eleItem.getStringValue().trim());
				}
			});
			if (null != servletClass.get() && !"".equals(servletClass.get())){
				servletNameAndClass.put(servletName.get(), servletClass.get());
			}
			if (null != urlPattern.get() && !"".equals(urlPattern.get())){
				servletUrlAndName.put(urlPattern.get(), servletName.get());
			}
		});
	}
}
