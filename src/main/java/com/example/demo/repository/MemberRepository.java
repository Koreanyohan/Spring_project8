package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Member;

//JpaRepository 상속받기
public interface MemberRepository extends JpaRepository<Member, String>{ //엔티티와 pk타입 지정

}
