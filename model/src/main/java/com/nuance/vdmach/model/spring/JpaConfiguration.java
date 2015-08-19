//package com.nuance.vdmach.model.spring;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// *
// * @author ediband1
// *         date:   8/18/15 4:15 PM
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.nuance.vdmach.model.repositories")
//public class JpaConfiguration {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean merchantMainUnit() {
//        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//
//        bean.setPersistenceXmlLocation("classpath:/META-INF/persistence.xml");
//        bean.setPersistenceUnitName("vdmach");
//
//        return bean;
//    }
//
//}
