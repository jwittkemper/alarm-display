package biz.wittkemper.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="Kennungen")
@Proxy(lazy=false)
public class Kennung implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2180240926117772807L;
	private Long ric;
	private String bezeichnung;
	private Boolean ausblenden;
	
	private List<Alarmierung> alarmierungen = new ArrayList<Alarmierung>();
	
	@Id
	@Column(name="RIC")
	public Long getRic() {
		return ric;
	}
	public void setRic(Long ric) {
		this.ric = ric;
	}
	
	@Column(name="Bezeichnung", length=50)
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public Boolean getAusblenden() {
		return ausblenden;
	}
	public void setAusblenden(Boolean ausblenden) {
		this.ausblenden = ausblenden;
	}
	
	@OneToMany(mappedBy="kennung" , fetch= FetchType.EAGER)
	public List<Alarmierung> getAlarmierungen() {
		return alarmierungen;
	}
	public void setAlarmierungen(List<Alarmierung> alarmierungen) {
		this.alarmierungen = alarmierungen;
	}

}
