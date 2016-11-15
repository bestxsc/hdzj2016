package wl.hdzj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.domain.ColumnVO;
import wl.hdzj.entity.Columnnn;

import java.util.List;

public interface ColumnService {
    @Transactional
    Columnnn add(ColumnVO colmnVO) throws Exception;
    Page<Columnnn> gets(Pageable p);
    @Transactional
    long deleteByIDs(List<Integer> ids);
    @Transactional
    Columnnn update(ColumnVO colmnVO) throws Exception;
    Page<Columnnn> querys(ColumnVO colmnVO, Pageable p);
}
