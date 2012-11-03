package biz.wittkemper.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="monitord_pocsag")
@Proxy(lazy=false)
public class Monitord_POSAC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9205936158051449989L;
	private Long id;
	private Date uhrzeit;
	private String kennung;
	private String sub;
	private String text;
	private int quelle;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getUhrzeit() {
		return uhrzeit;
	}
	public void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getQuelle() {
		return quelle;
	}
	public void setQuelle(int quelle) {
		this.quelle = quelle;
	}
	public String getKennung() {
		return kennung;
	}
	public void setKennung(String kennung) {
		this.kennung = kennung;
	}
	
}
