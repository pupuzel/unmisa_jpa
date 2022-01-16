package com.jock.unmisa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

/*	
	em.find();    // 엔티티 조회
	em.persist(); // 엔티티 저장
	em.remove();  // 엔티티 삭제
	em.flush();   // 영속성 컨텍스트 내용을 데이터베이스에 반영
	em.detach();  // 엔티티를 준영속 상태로 전환
	em.merge();   // 준영속 상태의 엔티티를 영속상태로 변경
	em.clear();   // 영속성 컨텍스트 초기화
	em.close();   // 영속성 컨텍스트 종료
*/
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UnmisaApplicationTests {

    @PersistenceContext
    private EntityManager em;
    
    private JPAQueryFactory queryFactory;
    
    @Rollback(false)
	@Test
	void test() {
		queryFactory = new JPAQueryFactory(em);
		
		User user = new User();
		user.setId("test12345");
		user.setUser_email("jjdo1994");
		user.setEmail_yn(false);
		user.setOauth_client_id("1234");
		user.setOauth_type(OauthType.kakao);
		
		em.persist(user);
		
		user.setUser_simple_intro("크린이 클라스");
		
	}
	
}
