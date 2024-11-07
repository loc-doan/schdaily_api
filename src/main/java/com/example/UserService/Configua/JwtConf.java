package com.example.UserService.Configua;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtConf {

    protected static final String SIGN_KEY = "sDBrCvq+P9y2a0ztzkPGfrP7jz0yPYvVw9xTu5OojXX+/iEvhjAf773mmNyvPdIj";

    public String createToken(int id) {
        JWSHeader jh = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jc = new JWTClaimsSet.Builder()

                .issuer("http://localhost:8080")
                .claim("id", id)
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .build();
        Payload payload = new Payload(jc.toJSONObject());
        JWSObject jo = new JWSObject(jh,payload);
        try{
            jo.sign(new MACSigner(SIGN_KEY.getBytes()));
            return jo.serialize();
        } catch (JOSEException e) {

            throw new RuntimeException("invalid");
        }
    }

    public int extractId(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            return claims.getIntegerClaim("id");
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}
