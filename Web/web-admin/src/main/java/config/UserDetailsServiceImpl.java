package config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.adminServerjie;
import com.djk.server.roleServerjie;
import entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Reference
    private adminServerjie adminServerjie;
    @Reference
    private roleServerjie roleServerjie;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminServerjie.selectbyname(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<String> permissions =roleServerjie.selectpermissions(user.getId());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            if(permission==null) continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return new User(username,user.getPassword(),authorities);
    }
}
