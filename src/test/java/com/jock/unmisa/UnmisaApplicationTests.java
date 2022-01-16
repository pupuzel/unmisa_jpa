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
	em.find();    // ��ƼƼ ��ȸ
	em.persist(); // ��ƼƼ ����
	em.remove();  // ��ƼƼ ����
	em.flush();   // ���Ӽ� ���ؽ�Ʈ ������ �����ͺ��̽��� �ݿ�
	em.detach();  // ��ƼƼ�� �ؿ��� ���·� ��ȯ
	em.merge();   // �ؿ��� ������ ��ƼƼ�� ���ӻ��·� ����
	em.clear();   // ���Ӽ� ���ؽ�Ʈ �ʱ�ȭ
	em.close();   // ���Ӽ� ���ؽ�Ʈ ����
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
		
		user.setUser_simple_intro("ũ���� Ŭ��");
		
	}
	
}
