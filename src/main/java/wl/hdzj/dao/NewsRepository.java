package wl.hdzj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wl.hdzj.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    @Modifying
    long deleteByNid(int nid);
    Page<News> findAll(Specification<News> spec, Pageable pageable);
}
