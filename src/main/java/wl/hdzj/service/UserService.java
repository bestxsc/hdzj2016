package wl.hdzj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import wl.hdzj.domain.UserVO;
import wl.hdzj.entity.User;

import java.util.List;

/**
 * Created by lipengbiao on 2016/11/14.
 */
public interface UserService {
    @Transactional
    User add(UserVO userVO) throws Exception;
    Page<User> gets(Pageable p);
    @Transactional
    long deleteByIDs(List<Integer> ids);
    @Transactional
    User update(UserVO userVO) throws Exception;
    Page<User> querys(UserVO userVO, Pageable p);
}
