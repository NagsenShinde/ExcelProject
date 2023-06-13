package antProject;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "excelbook")
public class EntityClass {
	
	@Column(name ="Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
//	@Column(name = "DOB")
//	private Date DateofBrith;
	
	@Column(name ="Name")
    private String name;
	
	
	@Column(name ="PhoneNo")
	private long phoneno;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "EntityClass [id=" + id + ", "  + ", name=" + name + ", phoneno=" + phoneno
				+ "]";
	}

	public int setId(int id) {
		return this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}

	 
 
}