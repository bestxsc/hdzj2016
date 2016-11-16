package wl.hdzj.service.Impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.common.SomeThing;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.ColumnRepository;
import wl.hdzj.dao.NewsRepository;
import wl.hdzj.dao.TeamRepository;
import wl.hdzj.domain.NewsVO;
import wl.hdzj.entity.Columnnn;
import wl.hdzj.entity.News;
import wl.hdzj.entity.Team;
import wl.hdzj.service.NewsService;

import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    SessionFactory sf;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private ColumnRepository colRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Value("${my.uf.upload}")
    private String uploadpath;

    @Autowired
    public NewsServiceImpl(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sf = factory.unwrap(SessionFactory.class);
    }

    @Override
    @Transactional
    //@SuppressWarnings("unchecked")
    public News add(@NotNull NewsVO newsVO, @NotNull String sid) throws Exception {
        //获取图片id
        String iid = newsVO.getPic();
        //关联校验
        associatedLoader(newsVO);
        if (iid != null && !iid.isEmpty()) {
            if (stringRedisTemplate.opsForHash().size(iid) == 0) throw new Exception("图片参数错误");
            Map<Object, Object> em = stringRedisTemplate.opsForHash().entries(iid);
            //校验Session ID
            if (!em.get("sessionid").toString().equals(sid)) throw new Exception("不属于当前用户");
            //临时缓存字符串
            String temp = em.get("mime").toString();
            //计算文件名
            String tname = iid + "." + temp.substring(temp.lastIndexOf("/") + 1, temp.length());
            newsVO.setPic(tname);
            SomeThing.transferImg(uploadpath, iid, () -> null, em, tname);
        }
        //存储记录
        return newsRepository.save((News) ModelConverter.convert(News.class).apply(newsVO));
    }

    @Override
    public Page<News> gets(@NotNull Pageable p) {
        return newsRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(@NotNull List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            //删除文件
            News ne = newsRepository.getOne(v);
            if (ne.getPic() != null) {
                File del = new File(uploadpath, ne.getPic());
                if (del.exists()) del.delete();
            }
            count += newsRepository.deleteByNid(v);
        }
        return count;
    }

    //关联装载器
    private void associatedLoader(NewsVO newsVO) throws Exception {
        if (newsVO.getCid() != null) {
            Columnnn col = colRepository.getOne(newsVO.getCid());
            if (col == null || col.getCid() <= 0) throw new Exception("关联错误");
        }
        if (newsVO.getTid() != null) {
            Team team = teamRepository.getOne(newsVO.getTid());
            if (team == null || team.getTid() <= 0) throw new Exception("关联错误");
        }
    }

    @Override
    public News update(@NotNull NewsVO newsVO, String sid) throws Exception {
        if (newsVO.getNid() == null) return null;
        //得到要更新的实体
        News gn = newsRepository.getOne(newsVO.getNid());
        //是否存在
        if (gn == null || !gn.getNid().equals(newsVO.getNid())) throw new Exception("修改项目不存在");
        //关联校验
        associatedLoader(newsVO);
        //获取图片id
        String iid = newsVO.getPic();
        if (iid != null && !iid.isEmpty()) {
            if (stringRedisTemplate.opsForHash().size(iid) == 0) throw new Exception("图片参数错误");
            Map<Object, Object> em = stringRedisTemplate.opsForHash().entries(iid);
            //校验Session ID
            if (!em.get("sessionid").toString().equals(sid)) throw new Exception("不属于当前用户");
            //临时缓存字符串
            String temp = em.get("mime").toString();
            //计算文件名
            String tname = iid + "." + temp.substring(temp.lastIndexOf("/") + 1, temp.length());
            newsVO.setPic(tname);
            SomeThing.transferImg(uploadpath, iid, () -> {
                //删除文件
                News ne = newsRepository.getOne(newsVO.getNid());
                if (ne.getPic() != null) {
                    File del = new File(uploadpath, ne.getPic());
                    if (del.exists()) del.delete();
                }
                return null;
            }, em, tname);
        }
        ModelConverter.setUpdateEntity(gn, newsVO);
        return newsRepository.saveAndFlush(gn);
    }

    @Override
    //@SuppressWarnings("unchecked")
    public Page<News> querys(@NotNull NewsVO newsVO, @NotNull Pageable p) {
        return newsRepository.findAll(ModelConverter.setQueryStatement(newsVO), p);
    }
}
