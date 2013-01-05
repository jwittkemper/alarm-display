package biz.wittkemper.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "posac_status")
@Proxy(lazy = false)
public class Posac_status implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6393042199846523224L;
	private Long id;
	private int posac_status;
	private String text;
	private List<Alarmierung> alarmierungen = new ArrayList<Alarmierung>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "posac_status")
	public int getPosac_status() {
		return posac_status;
	}

	public void setPosac_status(int posacStatus) {
		posac_status = posacStatus;
	}

	@Column(name = "text", length = 40)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
	public List<Alarmierung> getAlarmierungen() {
		return alarmierungen;
	}

	public void setAlarmierungen(List<Alarmierung> alarmierungen) {
		this.alarmierungen = alarmierungen;
	}

}
