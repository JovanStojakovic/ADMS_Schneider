package com.example.ADMS_praksa.controller;

import com.example.ADMS_praksa.dto.LoginDTO;
import com.example.ADMS_praksa.dto.RegistrationDTO;
import com.example.ADMS_praksa.dto.TokenDTO;
import com.example.ADMS_praksa.dto.UserDTO;
import com.example.ADMS_praksa.services.JWTUtils;
import com.example.ADMS_praksa.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    private ResponseEntity<UserDTO> registration (@RequestBody RegistrationDTO registrationDTO){
        //pravimo objekat klase KorisnikDTO koji u sebi sadrzi sve iz te klase i pozivamo metodu iz KorisnikSevice
        UserDTO userDTO = userService.register(registrationDTO);
        //vrati mi novog korisnikDTO
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> createAuthenticationToken(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),loginDTO.getPassword()));
        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Kreiraj token za tog korisnika
        //UserDetails to je interface koji vec postoji u sistemu i on sadrzi podatke o korisniku
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new TokenDTO(jwt));
    }

}
