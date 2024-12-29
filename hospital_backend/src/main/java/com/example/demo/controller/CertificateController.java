package com.example.demo.controller;

import com.example.demo.dto.CertificateRequestDTO;
import com.example.demo.dto.CertificateDTO;
import com.example.demo.service.CertificateService;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CertificateController {

	private final CertificateService certificateService;

	@PostMapping
	@PreAuthorize("hasAuthority('SAVE_CERTIFICATES')")
	public ResponseEntity<CertificateDTO> create(@RequestBody CertificateDTO certificateDTO) {
		this.certificateService.create(certificateDTO);
		return ResponseEntity.ok(certificateDTO);			
	}

	@PostMapping("/request")
	@PreAuthorize("hasAuthority('REQUEST_CERTIFICATES')")
	public ResponseEntity<CertificateRequestDTO> request(@RequestBody CertificateRequestDTO requestDTO) {
		this.certificateService.request(requestDTO);
		return ResponseEntity.ok(requestDTO);
	}

	@DeleteMapping("/{fileName}")
	@PreAuthorize("hasAuthority('REQUEST_CERTIFICATES')")
	public ResponseEntity<Void> revoke(@PathVariable String fileName) {
		this.certificateService.revoke(fileName);
		return ResponseEntity.noContent().build();
	}

}
