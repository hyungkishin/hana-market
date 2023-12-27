package com.hanamarket.login.domain.repository;

import com.hanamarket.login.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Boolean existsMemberByEmail(String email);

    Optional<Member> findByEmail(String email);
}
