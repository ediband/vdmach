//package com.nuance.vdmach.model.spring;
//
//import javax.enterprise.context.Dependent;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// * Reexport the JPA EntityManager as CDI bean so that it can be found by spring-data CDI extension
// *
// * @author ediband1
// *         date:   8/19/15 12:30 PM
// */
//class CdiConfig {
//    @Produces
//    @PersistenceContext
//    @Dependent
//    public EntityManager entityManager;
//}