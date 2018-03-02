package com.assently.mobiletest.common;

import android.util.Base64;

import com.assently.mobiletest.data.JwtPayload;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {
    public String sign(JwtPayload payload, String key) {
        byte[] base = Base64.encode(key.getBytes(), Base64.DEFAULT);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, new String(base))
                .setIssuer(payload.getIss())
                .setIssuedAt(payload.getIat())
                .setExpiration(payload.getExp())
                .claim("dnm", payload.getDnm())
                .claim("hst", payload.getHst())
                .setId(payload.getJti())
                .claim("aud", payload.getAud())
                .claim("typ", "JWT")
                .claim("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .compact();
    }
}
