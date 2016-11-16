package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import wl.hdzj.common.SomeThing;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.TeamRepository;
import wl.hdzj.domain.TeamVO;
import wl.hdzj.entity.Team;
import wl.hdzj.service.TeamService;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService {
    @Value("${my.uf.upload}")
    private static String uploadpath;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team add(TeamVO teamVO, String sid) throws Exception {
        //获取图片id
        String iid = teamVO.getPic();
        if (iid != null && !iid.isEmpty()) {
            if (stringRedisTemplate.opsForHash().size(iid) == 0) throw new Exception("图片参数错误");
            Map<Object, Object> em = stringRedisTemplate.opsForHash().entries(iid);
            //校验Session ID
            if (!em.get("sessionid").toString().equals(sid)) throw new Exception("不属于当前用户");
            //关联校验
            //associatedLoader(newsVO);
            //临时缓存字符串
            String temp = em.get("mime").toString();
            //计算文件名
            String tname = iid + "." + temp.substring(temp.lastIndexOf("/") + 1, temp.length());
            teamVO.setPic(tname);
            SomeThing.transferImg(uploadpath, iid, () -> null, em, tname);
        }
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
            //删除文件
            Team ne = teamRepository.getOne(v);
            if (ne.getPic() != null) {
                File del = new File(uploadpath, ne.getPic());
                if (del.exists()) del.delete();
            }
            count += teamRepository.deleteByTid(v);
        }
        return count;
    }

    @Override
    public Team update(TeamVO teamVO, String sid) throws Exception {
        if (teamVO.getTid() == null) return null;
        Team gn = teamRepository.getOne(teamVO.getTid());
        //无关联项
        if (gn == null || gn.getTid() == null) throw new Exception("修改项目不存在");
        //获取图片id
        String iid = teamVO.getPic();
        if (iid != null && !iid.isEmpty()) {
            if (stringRedisTemplate.opsForHash().size(iid) == 0) throw new Exception("图片参数错误");
            Map<Object, Object> em = stringRedisTemplate.opsForHash().entries(iid);
            //校验Session ID
            if (!em.get("sessionid").toString().equals(sid)) throw new Exception("不属于当前用户");
            //关联校验
            //临时缓存字符串
            String temp = em.get("mime").toString();
            //计算文件名
            String tname = iid + "." + temp.substring(temp.lastIndexOf("/") + 1, temp.length());
            teamVO.setPic(tname);
            SomeThing.transferImg(uploadpath, iid, () -> {
                //删除文件
                Team ne = teamRepository.getOne(teamVO.getTid());
                if (ne.getPic() != null) {
                    File del = new File(uploadpath, ne.getPic());
                    if (del.exists()) del.delete();
                }
                return null;
            }, em, tname);
        }
        ModelConverter.setUpdateEntity(gn, teamVO);
        return teamRepository.saveAndFlush(gn);
    }

    @Override
    public Page<Team> querys(TeamVO teamVO, Pageable p) {
        return teamRepository.findAll(ModelConverter.setQueryStatement(teamVO),p);
    }
}
