package com.leila.salao.LeilaSalao.user;

import org.springframework.web.bind.annotation.RestController;

import com.leila.salao.LeilaSalao.employerType.UserTypeModel;
import com.leila.salao.LeilaSalao.employerType.UserTypeRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.Beans;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

import org.hibernate.usertype.UserType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    public UserController() {
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody UserDTO userDTO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        UserTypeModel userType = userTypeRepository.findById(userDTO.user_type()).get();
        userModel.setUserType(userType);

        var userName = this.userRepository.findByUsername(userModel.getUsername());
        if (userName != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var passwordCrypted = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordCrypted);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping("/")
    public ResponseEntity<Object> doLogin(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        var authEncoded = authorization.substring(5).trim();
        byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
        var authString = new String(authDecoded);
        String[] credentials = authString.split(":");

        UserModel userLoged = userRepository.findByUsername(credentials[0]);

        if (userLoged == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não existe no banco de dados");
        }

        // gerar API-KEY
        UUID uniqueID = UUID.randomUUID();
        userLoged.setApiKey(uniqueID);
        UserModel userSaved = userRepository.save(userLoged);
        userSaved.setPassword(null);

        return ResponseEntity.status(HttpStatus.OK).body(userSaved);
    }

    @GetMapping("/checkapikey/")
    public ResponseEntity<Boolean> checkapikey(HttpServletRequest request) {
        UUID uuid = null;
        var authorization = request.getHeader("apikey");

        try {
            uuid = UUID.fromString(authorization);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }
        UserModel user = userRepository.findByApiKey(uuid);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(true);
    }

    // put 

    // Delete
}
