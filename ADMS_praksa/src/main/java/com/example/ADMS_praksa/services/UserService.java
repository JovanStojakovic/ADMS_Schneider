package com.example.ADMS_praksa.services;

import com.example.ADMS_praksa.dto.RegistrationDTO;
import com.example.ADMS_praksa.dto.UserDTO;

public interface UserService {
    UserDTO register(RegistrationDTO registrationDTO);
}
