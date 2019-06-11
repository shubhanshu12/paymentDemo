package com.shubhanshu.paytmdemo.repository.facade;

import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepoCustom;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PaytmUserFacade implements PaytmUserRepoCustom {

//    EntityManager em;
//
//    @Override
//    public PaytmUser findPaytmUserByPhone(Long phoneNumber) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<PaytmUser> cq = cb.createQuery(PaytmUser.class);
//
//        Root<PaytmUser> user = cq.from(PaytmUser.class);
//        Predicate phoneNumberPredicate = cb.equal(user.get("phoneNumber"), phoneNumber);
//        cq.where(phoneNumberPredicate);
//
//        TypedQuery<PaytmUser> query = em.createQuery(cq);
//        List<PaytmUser> resultList = query.getResultList();
//        return resultList.size() > 0 ? resultList.get(0) : null;
//    }
}
