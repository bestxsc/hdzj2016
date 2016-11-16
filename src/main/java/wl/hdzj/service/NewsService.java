package wl.hdzj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.domain.NewsVO;
import wl.hdzj.entity.News;

import java.util.List;

public interface NewsService {
    @Transactional
    News add(NewsVO newsVO, String sid) throws Exception;
    Page<News> gets(Pageable p);
    @Transactional
    long deleteByIDs(List<Integer> ids);
    @Transactional
    News update(NewsVO newsVO, String sid) throws Exception;
    Page<News> querys(NewsVO newsVO, Pageable p);
}
