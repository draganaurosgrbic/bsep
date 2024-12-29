package com.example.demo.controller;

import com.example.demo.dto.ActivationDTO;
import com.example.demo.dto.AuthTokenDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthTokenDTO> login(@Valid @RequestBody LoginDTO loginDTO){
		return ResponseEntity.ok(this.userService.login(loginDTO));
	}

	@PostMapping("/activate")
	public ResponseEntity<UserDTO> activate(@RequestBody ActivationDTO activationDTO) {
		return ResponseEntity.ok(this.userService.activate(activationDTO));
	}

	@GetMapping("/disabled/{uuid}")
	public ResponseEntity<UserDTO> getDisabled(@PathVariable String uuid) {
		return ResponseEntity.ok(this.userService.getDisabled(uuid));
	}

}
