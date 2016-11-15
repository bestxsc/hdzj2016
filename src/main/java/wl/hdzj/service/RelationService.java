package wl.hdzj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.domain.RelationVO;
import wl.hdzj.entity.Relation;

import java.util.List;

/**
 * Created by micro on 2016/11/15.
 */
public interface RelationService {
    @Transactional
    Relation add(RelationVO relationVO) throws Exception;
    Page<Relation> gets(Pageable p);
    @Transactional
    long deleteByIDs(List<Integer> ids);
    @Transactional
    Relation update(RelationVO relationVO) throws Exception;
    Page<Relation> querys(RelationVO relationVO, Pageable p);
}
