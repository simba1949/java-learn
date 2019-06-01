package reflection;

import xml.XMLParse;

import java.lang.reflect.InvocationTargetException;

/**
 * @author SIMBA1949
 * @date 2019/5/29 22:39
 */
public class DispatcherServlet {


	public static void main(String[] args) {
		XMLParse xmlParse = new XMLParse();
		String servletName = XMLParse.servletUrlAndName.get("/login");
		String classStr = XMLParse.servletNameAndClass.get(servletName);
		Servlet servlet;
		try {
			servlet = (Servlet) Class.forName(classStr).getConstructor().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
