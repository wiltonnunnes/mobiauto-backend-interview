package com.wilton.mobiauto_backend_interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.io.FileOutputStream;

@SpringBootApplication
public class MobiautoBackendInterviewApplication {

    private static void generateRSAKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();

            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            FileOutputStream fos = new FileOutputStream("public.key");
            fos.write(publicKey.getEncoded());
            fos.close();

            fos = new FileOutputStream("private.key");
            fos.write(privateKey.getEncoded());
            fos.close();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
        generateRSAKeyPair();
		SpringApplication.run(MobiautoBackendInterviewApplication.class, args);
	}

}
