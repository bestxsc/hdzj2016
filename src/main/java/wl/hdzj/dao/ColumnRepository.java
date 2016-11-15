package wl.hdzj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import wl.hdzj.entity.Columnnn;

@Repository
public interface ColumnRepository extends JpaRepository<Columnnn, Integer> {
    @Modifying
    long deleteByCid(int cid);
    Page<Columnnn> findAll(Specification<Columnnn> spec, Pageable pageable);
}
