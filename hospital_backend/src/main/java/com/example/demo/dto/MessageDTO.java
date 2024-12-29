package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.Message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {

	private long id;
	private Date date;
	private double pulse;
	private double pressure;
	private double temperature;
	private double oxygenLevel;
	private String patient;
		
	public MessageDTO(Message message) {
		super();
		this.id = message.getId();
		this.date = message.getDate();
		this.pulse = message.getPulse();
		this.pressure = message.getPressure();
		this.temperature = message.getTemperature();
		this.oxygenLevel = message.getOxygenLevel();
		this.patient = message.getPatient().getFirstName() + " " + message.getPatient().getLastName();
	}
	
}
