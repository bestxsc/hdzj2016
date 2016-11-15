package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.MemberRepository;
import wl.hdzj.dao.RelationRepository;
import wl.hdzj.dao.TeamRepository;
import wl.hdzj.domain.NewsVO;
import wl.hdzj.domain.RelationVO;
import wl.hdzj.entity.Columnnn;
import wl.hdzj.entity.Member;
import wl.hdzj.entity.Relation;
import wl.hdzj.entity.Team;
import wl.hdzj.service.RelationService;

import java.util.List;

/**
 * Created by micro on 2016/11/15.
 */
@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Override
    public Relation add(RelationVO relationVO) throws Exception {
        //存储记录
        return relationRepository.save(
                (Relation) ModelConverter.convert(Relation.class).apply(relationVO)
        );
    }

    @Override
    public Page<Relation> gets(Pageable p) {
        return relationRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            count += relationRepository.deleteByRid(v);
        }
        return count;
    }

    //关联校验器
    private void associatedLoader(RelationVO relationVO) throws Exception{
        if (relationVO.getMid() != null){
            Member member = memberRepository.getOne(relationVO.getMid());
            if (member == null || member.getMid() <= 0) throw new Exception("关联错误");
        }
        if (relationVO.getTid() != null){
            Team team = teamRepository.getOne(relationVO.getTid());
            if (team == null || team.getTid() <= 0) throw new Exception("关联错误");
        }
    }

    @Override
    public Relation update(RelationVO relationVO) throws Exception {
        if (relationVO.getRid() == null) return null;
        Relation gn = relationRepository.getOne(relationVO.getRid());
        associatedLoader(relationVO);
        if (gn == null || gn.getRid() == null) throw new Exception("修改项目不存在");
        ModelConverter.setUpdateEntity(gn, relationVO);
        return relationRepository.saveAndFlush(gn);
    }

    @Override
    public Page<Relation> querys(RelationVO relationVO, Pageable p) {
        return relationRepository.findAll(ModelConverter.setQueryStatement(relationVO),p);

    }
}
