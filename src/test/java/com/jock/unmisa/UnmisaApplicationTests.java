package com.jock.unmisa;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.user.User;

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
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UnmisaApplicationTests {

    @Autowired
    private UserQueryRepository userDAO;
    
//    @Transactional
//    @Rollback(false)
//    @Test
//	public void setData() {
//		
//		User user = new User();
//		user.setId(UUID.randomUUID().toString());
//		user.setUser_nm("크린이_클라스2");
//		user.setUser_email("jjdo1994");
//		user.setEmail_yn(false);
//		user.setOauth_client_id("1234");
//		user.setOauth_type(OauthType.kakao);
//		
//		userDAO.insertUser(user);
//		
//	}
//    
    
    @Test
    void test() throws Exception {
    	User user = userDAO.selectUser(null, "크린이_클라스");
    	System.out.println("id : "+user.getId());
    	System.out.println("email : "+user.getUser_email());
    	System.out.println("name : "+user.getUser_nm());
    }
	
}
