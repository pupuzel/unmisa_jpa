package com.jock.unmisa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jock.unmisa.entity.domain.OauthType;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UnmisaApplicationTests {

	@Test
	void test() {
		OauthType kakao = OauthType.valueOf("naver");
		System.out.println(kakao.getValue());
		System.out.println(kakao.ordinal());
		
		System.out.println(String.format("%04d", kakao.ordinal()));
	}

}
