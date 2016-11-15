package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.UserRepository;
import wl.hdzj.domain.UserVO;
import wl.hdzj.entity.Member;
import wl.hdzj.entity.User;
import wl.hdzj.service.UserService;

import java.util.List;

/**
 * Created by lipengbiao on 2016/11/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User add(UserVO userVO) throws Exception {
        return userRepository.save(
                (User) ModelConverter.convert(User.class).apply(userVO)
        );
    }

    @Override
    public Page<User> gets(Pageable p) {
        return userRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            count += userRepository.deleteByUid(v);
        }
        return count;
    }

    @Override
    public User update(UserVO userVO) throws Exception {
        if (userVO.getUid() == null) return null;
        User gn = userRepository.getOne(userVO.getUid());
        //无关联项
        //associatedLoader(userVO);
        if (gn == null || gn.getUid() == null) throw new Exception("修改项目不存在");
        ModelConverter.setUpdateEntity(gn, userVO);
        return userRepository.saveAndFlush(gn);
    }

    @Override
    public Page<User> querys(UserVO userVO, Pageable p) {
        return userRepository.findAll(ModelConverter.setQueryStatement(userVO),p);
    }
}
