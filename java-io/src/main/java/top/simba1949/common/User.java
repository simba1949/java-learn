package top.simba1949.common;

import java.io.Serializable;

/**
 * @author SIMBA1949
 * @date 2019/5/20 22:41
 */
public class User implements Serializable {
	private static final long serialVersionUID = -3250898344958531492L;
	private Integer id;
	private String username;
	private Boolean flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", flag=" + flag +
				'}';
	}
}
