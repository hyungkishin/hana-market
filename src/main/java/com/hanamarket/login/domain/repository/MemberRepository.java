package com.hanamarket.login.domain.repository;

import com.hanamarket.login.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Boolean existsMemberByEmail(String email);
}
