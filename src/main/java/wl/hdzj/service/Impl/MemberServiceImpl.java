package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.ExceptionTranslationStrategy;
import org.springframework.stereotype.Service;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.MemberRepository;
import wl.hdzj.dao.TeamRepository;
import wl.hdzj.domain.MemberVO;
import wl.hdzj.domain.NewsVO;
import wl.hdzj.entity.Columnnn;
import wl.hdzj.entity.Member;
import wl.hdzj.entity.Team;
import wl.hdzj.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    //private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Cacheable(value = "hdzj2016_cache")
    public List<Member> findShow(){
        return memberRepository.findShow();
    }

    @Override
    public Member add(MemberVO memberVO) throws Exception{
        //存储记录
        return memberRepository.save(
                (Member) ModelConverter.convert(Member.class).apply(memberVO)
        );
    }

    @Override
    public Page<Member> gets(Pageable p) {
        return memberRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            count += memberRepository.deleteByMid(v);
        }
        return count;
    }

    @Override
    public Member update(MemberVO memberVO) throws Exception{
        if (memberVO.getMid() == null) return null;
        Member gn = memberRepository.getOne(memberVO.getMid());
        if (gn == null || gn.getMid() == null) throw new Exception("修改项目不存在");
        ModelConverter.setUpdateEntity(gn, memberVO);
        return memberRepository.saveAndFlush(gn);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Member> querys(MemberVO memberVO, Pageable p) {
        return memberRepository.findAll(ModelConverter.setQueryStatement(memberVO),p);
    }
}
