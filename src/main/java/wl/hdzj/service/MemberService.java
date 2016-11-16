package wl.hdzj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.domain.MemberVO;
import wl.hdzj.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findShow();
    @Transactional
    Member add(MemberVO memberVO, String sid) throws Exception;
    Page<Member> gets(Pageable p);
    @Transactional
    long deleteByIDs(List<Integer> ids);
    @Transactional
    Member update(MemberVO memberVO, String sid) throws Exception;
    Page<Member> querys(MemberVO memberVO, Pageable p);
}
