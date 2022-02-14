package com.jock.unmisa.utils;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jock.unmisa.entity.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	@Autowired
    private ObjectMapper MAPPER;

    @Value("${spring.jwt.secret}")
    private String secret;
    
    @Value("${spring.token.timeout}")
    private long token_timeout;
    
    //jwt token 모든 정보 조회
    public Map<String,Object> getBobyFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    //토큰이 유효성검사
    public Boolean isValidateToken(String token) {
    	try {
	        final String userId = (String) getBobyFromToken(token).get("id");
	        return !userId.isEmpty();
    	}catch (Exception e) {
			return false;
		}
    }

    // 토큰이 만료되었는지 확인한다.
    public Boolean isTokenExpired(String token) {
    	try {
	    	long exp = (Long) getBobyFromToken(token).get("exp");
	        final Date expiration = new Date(exp);
	        
	        return expiration.before(new Date());
    	}catch (Exception e) {
			return false;
		}
    }
    
    // 유저를 위한 토큰을 발급해준다.
    public <T> String generateToken(T userDetails) {
    	Map<String,Object> claim;
    	
    	if(userDetails instanceof User) {
    		claim = MAPPER.convertValue(userDetails, Map.class);
    	}else {
    		claim = (Map<String, Object>) userDetails;
    	}
        
        claim.put("iat", new Date(System.currentTimeMillis()));
        claim.put("exp", new Date(System.currentTimeMillis() + (1000 * token_timeout) ));
        
        return Jwts.builder()
        			.setClaims(claim)
        			.signWith(SignatureAlgorithm.HS512, secret)
        			.compact();
    }
    

}
