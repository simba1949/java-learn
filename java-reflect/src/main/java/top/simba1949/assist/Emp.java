package top.simba1949.assist;

import java.io.Serializable;

/**
 * @author SIMBA1949
 * @date 2019/6/2 8:22
 */
public class Emp implements Cloneable, Serializable {
	private static final long serialVersionUID = -6034644192309864288L;
	private int empno;
	private String ename;

	public Emp() {
	}

	public Emp(int empno, String ename) {
		this.empno = empno;
		this.ename = ename;
	}

	public void sayHello(){
		System.out.println("Hello");
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
}
