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
    
//    @Transactional
//    @Rollback(false)
//    @Test
//	public void setData() {
//		
//		User user = new User();
//		user.setId(UUID.randomUUID().toString());
//		user.setUser_nm("ũ����_Ŭ��2");
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
    	User user = userDAO.selectUser(null, "ũ����_Ŭ��");
    	System.out.println("id : "+user.getId());
    	System.out.println("email : "+user.getUser_email());
    	System.out.println("name : "+user.getUser_nm());
    }
	
}
