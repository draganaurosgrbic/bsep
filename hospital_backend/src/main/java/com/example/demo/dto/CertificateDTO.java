package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.model.enums.CertificateType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CertificateDTO {
	
	@NotBlank(message = "Issuer alias cannot be empty")
	private String issuerAlias;
	
	@NotBlank(message = "Alias cannot be empty")
	private String alias;
	
	@NotBlank(message = "Organization unit cannot be empty")
	private String organizationUnit;
	
	@NotNull(message = "Type cannot be null")
	private CertificateType type;
	
	@NotBlank(message = "Certificate cannot be empty")
	private String certificate;

}