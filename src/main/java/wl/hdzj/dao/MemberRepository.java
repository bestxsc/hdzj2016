package wl.hdzj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wl.hdzj.entity.Member;
import wl.hdzj.entity.News;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select m from Member m where m.isshow > 0")
    List<Member> findShow();
    @Modifying
    long deleteByMid(int id);
    Page<Member> findAll(Specification<Member> spec, Pageable pageable);
}
