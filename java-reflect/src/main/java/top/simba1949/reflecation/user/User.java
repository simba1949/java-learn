package top.simba1949.reflecation.user;

import java.util.Date;
import java.util.List;

/**
 * @author SIMBA1949
 * @date 2019/5/31 9:57
 */
@Data
public class User {

	private int id;
	@Data
	private String username;
	private Date birthday;
	private List<Role> roles;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(int id, String username, Date birthday, List<Role> roles) {
		this.id = id;
		this.username = username;
		this.birthday = birthday;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}
	@Data
	public void setId(int id) throws Exception{
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public void dance(String adj){
		System.out.println(adj + "");
	}

	public void dance(){
		System.out.println("");
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", birthday=" + birthday +
				", roles=" + roles +
				'}';
	}
}
