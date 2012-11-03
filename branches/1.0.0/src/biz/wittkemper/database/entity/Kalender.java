package biz.wittkemper.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="Kalender")
@Proxy(lazy=false)
public class Kalender implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date datum;
	private String text;
	private KalenderTyp kalenderTyp;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	@Column(length=100)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@ManyToOne
	@JoinColumn(name="typ")
	public KalenderTyp getKalenderTyp() {
		return kalenderTyp;
	}
	public void setKalenderTyp(KalenderTyp kalenderTyp) {
		this.kalenderTyp = kalenderTyp;
	}
	
}
