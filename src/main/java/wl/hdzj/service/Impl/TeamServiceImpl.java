package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.TeamRepository;
import wl.hdzj.domain.TeamVO;
import wl.hdzj.entity.Team;
import wl.hdzj.service.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team add(TeamVO teamVO) throws Exception{
        //存储记录
        return teamRepository.save(
                (Team) ModelConverter.convert(Team.class).apply(teamVO)
        );
    }

    @Override
    public Page<Team> gets(Pageable p) {
        return teamRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            count += teamRepository.deleteByTid(v);
        }
        return count;
    }

    @Override
    public Team update(TeamVO teamVO) throws Exception {
        if (teamVO.getTid() == null) return null;
        Team gn = teamRepository.getOne(teamVO.getTid());
        //无关联项
        if (gn == null || gn.getTid() == null) throw new Exception("修改项目不存在");
        ModelConverter.setUpdateEntity(gn, teamVO);
        return teamRepository.saveAndFlush(gn);
    }

    @Override
    public Page<Team> querys(TeamVO teamVO, Pageable p) {
        return teamRepository.findAll(ModelConverter.setQueryStatement(teamVO),p);
    }
}
