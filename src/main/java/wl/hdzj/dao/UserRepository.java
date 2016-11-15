package wl.hdzj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import wl.hdzj.entity.News;
import wl.hdzj.entity.User;

import java.util.List;

@Repository()
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findTop1ByAccount(String account);
    @Modifying
    long deleteByUid(int uid);
    Page<User> findAll(Specification<User> spec, Pageable pageable);
}
