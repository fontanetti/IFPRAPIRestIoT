package app.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Temperature implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
		
	private Double temperature;
	
	//@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	
	public Temperature() {}
	
	public Temperature(Integer id, Double temperature, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Temperature [id=" + id + ", temperature=" + temperature + ", timestamp=" + timestamp + "]";
	}
}
