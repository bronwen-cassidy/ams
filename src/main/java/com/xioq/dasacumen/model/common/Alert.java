package com.xioq.dasacumen.model.common;

//@Entity
//@Table(name="alerts")
public class Alert {
	// extends VersionControlled implements java.io.Serializable {

	// @Id @GeneratedValue(strategy=GenerationType.SEQUENCE,
	// generator="alerts_id_seq")
	// @SequenceGenerator(name="alerts_id_seq", sequenceName="alerts_id_seq",
	// allocationSize=1)
	private Long id;
	private String message;
	private String type;

	public Alert() {
	}

	public Alert(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// @Override
	// public EntityType getModelType()
	// {
	// return EntityType.ALERT;
	// }
	//
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
