package wl.hdzj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import wl.hdzj.entity.Relation;

/**
 * Created by micro on 2016/11/15.
 */
public interface RelationRepository extends JpaRepository<Relation, Integer> {
    @Modifying
    long deleteByRid(int rid);
    Page<Relation> findAll(Specification<Relation> spec, Pageable pageable);
}
