package com.jock.unmisa.config.aop;

import java.util.Collections;
import java.util.HashMap;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class TransactionConfig {

	@Autowired
    private PlatformTransactionManager transactionManager;
	
    @Bean
    public TransactionInterceptor transactionAdvice() {
    	TransactionInterceptor txAdvice = new TransactionInterceptor();
        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();

        // 트랜잭션 롤백 규칙
        txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        
        //이미 하나의 트랜잭션이 존재한다면 그 트랜잭션을 지원하고, 트랜잭션이 없다면 새로운 트랜잭션을 시작한다 (PROPAGATION_REQUIRED)
        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        HashMap<String, TransactionAttribute> txMethods = new HashMap<String, TransactionAttribute>();
        txMethods.put("*", txAttribute);
        txAttributeSource.setNameMap(txMethods);
        
        txAdvice.setTransactionAttributeSource(txAttributeSource);
        txAdvice.setTransactionManager(transactionManager);

        return txAdvice;

    }

 

    @Bean
    public Advisor transactionAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.jock.unmisa.service.*Service.*(..))");
        
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());

    }
}
