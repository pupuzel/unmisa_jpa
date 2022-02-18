package com.jock.unmisa;

import java.time.Duration;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.entity.user.UserMeta;

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
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UnmisaApplicationTests {

    @Autowired
    private UserQueryRepository userDAO;
    
	@Resource(name = "redisTemplate") 
	private ValueOperations<String, String> valueOperations;
    
    @Test
    public void redisTest() throws Exception{
    	valueOperations.set("test", "1234");
    }
    
    @Transactional
    @Rollback(false)
    //@Test
	public void test() throws Exception{
		User user1 = new User();
		user1.setId("1234");
		user1.setUser_nm("ũ����_Ŭ��");
		user1.setUser_email("jjdo1994");
		user1.setEmail_yn(false);
		user1.setOauth_client_id("1234");
		user1.setOauth_type(OauthType.kakao);
		
		User user2 = new User();
		user2.setId("12345");
		user2.setUser_nm("ũ����_Ŭ��2");
		user2.setUser_email("jjdo1994");
		user2.setEmail_yn(false);
		user2.setOauth_client_id("1234");
		user2.setOauth_type(OauthType.google);
		
		UserMeta m1 = new UserMeta();
		m1.setId("1234");
		
		userDAO.insert(m1);
		
		userDAO.insert(user1);
		userDAO.insert(user2);
		
		test2();
	}
    
    
    
    void test2() throws Exception {
    	User user = userDAO.selectUser("1234", null);
    	
    	if(user != null) {
        	System.out.println("id : "+user.getId());
        	System.out.println("email : "+user.getUser_email());
        	System.out.println("name : "+user.getUser_nm());
    	}
    	
    	UserMeta m = user.getUser_meta();
    	if(m != null) {
    		System.out.println("id : "+m.getId());
    	}
    }
	
}
