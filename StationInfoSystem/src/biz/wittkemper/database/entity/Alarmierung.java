package biz.wittkemper.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="Alarmierungen")
@Proxy(lazy=false)
public class Alarmierung implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7963968162288696489L;
	
	private Long id;
	private Date uhrzeit;
	private String text;
	private Kennung kennung;
	private Posac_status status;
	private Boolean eot;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@ManyToOne
	@JoinColumn(name="kennung")
	public Kennung getKennung() {
		return kennung;
	}
	public void setKennung(Kennung kennung) {
		this.kennung = kennung;
	}
	
	@ManyToOne
	@JoinColumn(name="status")
	public Posac_status getStatus() {
		return status;
	}
	public void setStatus(Posac_status status) {
		this.status = status;
	}
	public Boolean getEot() {
		return eot;
	}
	public void setEot(Boolean eot) {
		this.eot = eot;
	}
	
}
