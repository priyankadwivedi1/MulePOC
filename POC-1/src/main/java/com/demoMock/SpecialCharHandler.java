package com.demoMock;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import java.security.Key;

public class SpecialCharHandler extends AbstractMessageTransformer{
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		String jwt = (String)message.getInboundProperty("jwtToken");
		Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary("abcd"))
			       .parseClaimsJws(jwt).getBody();
			    System.out.println("ID: " + claims.getId());
			    System.out.println("Subject: " + claims.getSubject());
			    System.out.println("Issuer: " + claims.getIssuer());
			    System.out.println("Expiration: " + claims.getExpiration());
	    String claimId= claims.getId();
	    return claimId;
	}
}
