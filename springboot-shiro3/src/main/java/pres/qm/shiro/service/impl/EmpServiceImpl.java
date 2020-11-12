package pres.qm.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import pres.qm.shiro.pojo.Emp;
import pres.qm.shiro.mapper.EmpMapper;
import pres.qm.shiro.pojo.Role;
import pres.qm.shiro.pojo.User;
import pres.qm.shiro.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pres.qm.shiro.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qianmuna
 * @since 2020-11-12
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Autowired
    UserService userService;

    @Override
    public List<Emp> getAuthList() {

        Subject subject = SecurityUtils.getSubject();
        String name = (String) subject.getPrincipal();

        User u = userService.findRolesByUserName(name);
        List<Emp> emp = new ArrayList<>();
        if (u != null){

            List<Role> roles = u.getRoles();
            roles.stream().map(Role::getId).forEach(id ->{
                List<Emp> emps = this.baseMapper.selectList(new QueryWrapper<Emp>().eq("auth_id", id));
                emp.addAll(emps);
            });
        }
        return emp;
    }
}
