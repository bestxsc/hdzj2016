package wl.hdzj.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import wl.hdzj.dao.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by micro on 2016/11/1.
 */
public class MyUserDetailsService implements UserDetailsService {

    final UserRepository dbUser;

    public MyUserDetailsService(UserRepository dbUser) {
        Assert.notNull(dbUser, "Repository must not be null!");
        this.dbUser = dbUser;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<wl.hdzj.entity.User> tuser = dbUser.findTop1ByAccount(s);
        if (tuser.size() == 0) throw new UsernameNotFoundException("Error in retrieving user");
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("USER"));
        return new User(tuser.get(0).getAccount(), tuser.get(0).getPasswd(), true, true, true, true, auths);
    }
}
