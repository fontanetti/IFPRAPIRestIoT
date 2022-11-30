package app.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Humidity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
		
	private Double humidity;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	
}
