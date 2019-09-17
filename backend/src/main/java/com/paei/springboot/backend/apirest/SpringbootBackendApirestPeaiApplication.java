package com.paei.springboot.backend.apirest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootBackendApirestPeaiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApirestPeaiApplication.class, args);
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String password = "12345";
        System.out.println("\n\n\n----->Constraseñas encriptadas para usuarios con password: " + password + "\n\n\n");

        for (int i = 0; i < 8; ++i){
            String passwordBCryp = passwordEncoder.encode(password);
            System.out.println(passwordBCryp);
        }
    }
}
