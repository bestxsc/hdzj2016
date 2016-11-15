package wl.hdzj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.domain.TeamVO;
import wl.hdzj.entity.Team;

import java.util.List;

public interface TeamService {
    @Transactional
    Team add(TeamVO teamVO) throws Exception;
    Page<Team> gets(Pageable p);
    @Transactional
    long deleteByIDs(List<Integer> ids);
    @Transactional
    Team update(TeamVO teamVO) throws Exception;
    Page<Team> querys(TeamVO teamVO, Pageable p);
}
