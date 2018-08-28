package com.demoMock;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

public class jwtTokenGenerartion {
	//Sample method to construct a JWT
	private String createJWT(String id, String issuer, String subject, long ttlMillis) {

	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("abcd");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);

	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
System.out.println("token::::"+ builder.compact());
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static void main(String args[]){
		jwtTokenGenerartion jTG= new jwtTokenGenerartion();
		jTG.createJWT("priaynka.dwivedi15@gmail.com", "priyanka", "testDemo", 888888);
		//jTG.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJwcmlheW5rYS5kd2l2ZWRpMTVAZ21haWwuY29tIiwiaWF0IjoxNTM1MTAyMDAxLCJzdWIiOiJ0ZXN0RGVtbyIsImlzcyI6InByaXlhbmthIiwiZXhwIjoxNTM1MTAyODkwfQ.E7bDqhZJkTj-nIIz0NXovgNtoYpiTFCoiJNVLY4PU6g");
		
	}
	private void parseJWT(String jwt) {

	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary("abcd"))
	       .parseClaimsJws(jwt).getBody();
	    System.out.println("ID: " + claims.getId());
	    System.out.println("Subject: " + claims.getSubject());
	    System.out.println("Issuer: " + claims.getIssuer());
	    System.out.println("Expiration: " + claims.getExpiration());
	}
}
