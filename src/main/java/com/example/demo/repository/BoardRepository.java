package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

//JpaRepository 상속받기
@Transactional 	// 밑에서와 같이 이렇게 손수 만든 메서드 같은 경우, transaction해주어야 db에 반영된다. 조회의 경우 transaction 처리 필요없음. 등록수정삭제는 이거 달아줘야.
public interface BoardRepository extends JpaRepository<Board, Integer> { //엔티티와 pk타입 지정
 // delete from tbl_board where writer_id = 'user1'  (아래것  sql로 적으면 이래)
	@Modifying
	@Query("delete from Board b where b.writer = :member") // < = JPQL. 엔티티이름(Board)사용
	void deleteWriter(@Param("member") Member member);

}



