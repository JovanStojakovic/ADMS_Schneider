package com.example.ADMS_praksa.services.Impl;

import com.example.ADMS_praksa.dto.RegistrationDTO;
import com.example.ADMS_praksa.dto.UserDTO;
import com.example.ADMS_praksa.entities.User;
import com.example.ADMS_praksa.repositories.UserRepository;
import com.example.ADMS_praksa.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(RegistrationDTO registrationDTO) {
        // Provera da li postoji korisnik sa istim korisničkim imenom
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new RuntimeException("Korisnik sa datim korisničkim imenom već postoji.");
        }

        //sad treba da ono iz RegistrationDTO (ono sto smo trazili da se unuse u KorisnikService) treba da mapiramo(prebacimo) u Korisnik
        User user = modelMapper.map(registrationDTO, User.class);
        user.setRole("Operator");

        //sad treba sa hesujemo lozinu
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setRegistrationDate(LocalDate.now());

        //sad hocemo da sacuvamo to, tako sto koristimo metodu iz korisnikRepository,sacuvamo sve u tabelu korisnik
        User saveUser = userRepository.save(user);

        //sad ono iz objekta saveKorisnik mapiramo u KorisnikDTO jer nam je korisnikDTO povratna vrednost u KorisnikService
        return modelMapper.map(saveUser, UserDTO.class);
    }
}
