package wl.hdzj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import wl.hdzj.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Modifying
    long deleteByTid(int tid);
    Page<Team> findAll(Specification<Team> spec, Pageable pageable);
}
