package com.boot.bucket.common.realm;

import com.boot.bucket.common.extend.shiro.ExtendAuthorizingRealm;
import com.boot.bucket.common.extend.shiro.SerializableSimpleByteSource;
import com.boot.bucket.entity.Func;
import com.boot.bucket.entity.RoleFunc;
import com.boot.bucket.entity.User;
import com.boot.bucket.entity.UserRole;
import com.boot.bucket.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomRealm extends ExtendAuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleFuncService roleFuncService;

    @Autowired
    private FuncService funcService;

    /**
     * 获取授权信息
     * <p>
     * 权限的值是前端ajax请求的路径，角色的存在是为了方便给用户批量赋值权限的。
     * 项目的最终实现是针对用户和权限的关系，不对角色作校验
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        /**
         * 如果项目使用了 spring-boot-devtools 会导致类加载不同
         * jar 使用 {@link sun.misc.Launcher.AppClassLoader}
         * spring-boot-devtools 使用 {@link org.springframework.boot.devtools.restart.classloader.RestartClassLoader}
         */
        Object obj = principalCollection.getPrimaryPrincipal();
        if (ObjectUtils.isEmpty(obj)) {
            throw new AccountException("用户信息查询为空");
        }

        User user;
        if (obj.getClass().getClassLoader().equals(User.class.getClassLoader())) {
            user = (User) obj;
        }else{
            user = new User();
            BeanUtils.copyProperties(obj, user);
        }

        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        List<UserRole> userRoleList = userRoleService.selectByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return authenticationInfo;
        }

        List<RoleFunc> roleFuncList = new ArrayList<>();
        userRoleList.forEach(
                userRole -> roleFuncList.addAll(roleFuncService.selectByRoleId(userRole.getRoleId()))
        );
        if (CollectionUtils.isEmpty(roleFuncList)) {
            return authenticationInfo;
        }

        Set<String> functionSet = roleFuncList.stream()
                .map(roleFunc ->
                {
                    Func func = funcService.queryFuncById(roleFunc.getFuncId());
                    return func.getCode();
                })
                .collect(Collectors.toSet());
        authenticationInfo.addStringPermissions(functionSet);
        return authenticationInfo;
    }

    /**
     * 获取验证信息
     * <p>
     * 将用户实体作为principal方便后续直接使用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.getByUserName(userName);

        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException("用户 " + userName + " 信息查询失败");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName()
        );
        authenticationInfo.setCredentialsSalt(new SerializableSimpleByteSource(user.getSalt()));
        return authenticationInfo;
    }

    /**
     * 删除缓存
     *
     * @param principals
     */
    @Override
    protected void doClearCache(PrincipalCollection principals) {
        super.doClearCache(principals);
    }

}
