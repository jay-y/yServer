package modules.template.security;

import com.google.common.collect.Maps;
import modules.template.utils.CacheUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.y;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 系统安全认证实现类
 */
@Component
public class SystemAuthorizingRealm extends AuthorizingRealm
{
//    @Autowired
//    private UserService userService;

    /**
     * 是否是验证码登录
     *
     * @param useruame 用户名
     * @param isFail   计数加1
     * @param clean    计数清零
     * @return
     */
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean)
    {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtil.get("loginFailMap");
        if (loginFailMap == null)
        {
            loginFailMap = Maps.newHashMap();
            CacheUtil.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum == null)
        {
            loginFailNum = 0;
        }
        if (isFail)
        {
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean)
        {
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    @Transactional
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String password = new String((char[]) token.getCredentials());    //得到密码

        // 校验登录验证码
//        if (isValidateCodeLogin(token.getUsername(), false,
//                false)) {
//            Session session = UserUtil.getSession();
//            String code = (String) session
//                    .getAttribute(ValidateCodeServlet.VALIDATE_CODE);
//            if (token.getCaptcha() == null
//                    || !token.getCaptcha().toUpperCase().equals(code)) {
//                throw new AuthenticationException("验证码错误, 请重试.");
//            }
//        }
        // 校验用户名密码
//        User user = userService.findByUserName(token.getUsername());
//        if (user != null) {
//            if (Constant._Y.equals(user.getIsDel())) {
//                throw new AuthenticationException("该已帐号禁止登录.");
//            }
//            String passwd = new SimpleHash(CiphertextUtil.SHA_1, token.getUsername(), password).toString();    //密码加密
//            if (!passwd.equals(user.getPassword())) {
//                return null;
//            }
//            userService.save(user);
//            return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()), password, getName());
//        } else {
//            return null;
//        }
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        y.log().debug("---------------------------------------------");
//        Principal principal = (Principal) getAvailablePrincipal(principals);
        // 获取当前已登录的用户
//        if (!Constant._TRUE.equals(WeChatProperties.getProperty("multiAccountLogin"))) {
//            Collection<Session> sessions = getSystemService().getSessionDao().getActiveSessions(true, principal, UserUtil.getSession());
//            if (sessions.size() > 0) {
//                // 如果是登录进来的，则踢出已在线用户
//                if (UserUtil.getSubject().isAuthenticated()) {
//                    for (Session session : sessions) {
//                        getSystemService().getSessionDao().delete(session);
//                    }
//                }
//                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
//                else {
//                    UserUtil.getSubject().logout();
//                    throw new AuthenticationException("账号已在其它地方登录，请重新登录。");
//                }
//            }
//        }
//        User user = getSystemService().getUserByLoginName(
//                principal.getLoginName());
//        if (user != null) {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			List<Menu> list = UserUtils.getMenuList();
//			for (Menu menu : list)
//			{
//				if (StringUtils.isNotBlank(menu.getPermission()))
//				{
//					// 添加基于Permission的权限信息
//					for (String permission : StringUtils.split(
//							menu.getPermission(), ","))
//					{
//						info.addStringPermission(permission);
//					}
//				}
//			}
        // 添加用户权限
//            info.addStringPermission("user");
        // 添加用户角色信息
//			for (Role role : user.getRoleList())
//			{
//				info.addRole(role.getEnname());
//			}
        // 更新登录IP和时间
//			getSystemService().updateUserLoginInfo(user);
        // 记录登录日志
//            LogUtils.saveLog(Servlets.getRequest(), "系统登录");
//            return info;
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    protected void checkPermission(Permission permission, AuthorizationInfo info)
    {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }

    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info)
    {
        if (permissions != null && !permissions.isEmpty())
        {
            for (Permission permission : permissions)
            {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission)
    {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }

    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info)
    {
        if (permissions != null && !permissions.isEmpty())
        {
            for (Permission permission : permissions)
            {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }

//	/**
//	 * 设定密码校验的Hash算法与迭代次数
//	 */
//	@PostConstruct
//	public void initCredentialsMatcher()
//	{
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
//				SystemService.HASH_ALGORITHM);
//		matcher.setHashIterations(SystemService.HASH_INTERATIONS);
//		setCredentialsMatcher(matcher);
//	}

    // /**
    // * 清空用户关联权限认证，待下次使用时重新加载
    // */
    // public void clearCachedAuthorizationInfo(Principal principal) {
    // SimplePrincipalCollection principals = new
    // SimplePrincipalCollection(principal, getName());
    // clearCachedAuthorizationInfo(principals);
    // }

    /**
     * 授权验证方法
     *
     * @param permission
     */
    private void authorizationValidate(Permission permission)
    {
        // 模块授权预留接口
    }

//    /**
//     * 授权用户信息
//     */
//    public static class Principal implements Serializable {
//
//        private static final long serialVersionUID = 1L;
//
//        private String code; // 编号
//        private String loginName; // 登录名
//        private String name; // 姓名
//        private boolean mobileLogin; // 是否手机登录
//
//        // private Map<String, Object> cacheMap;
//
//        public Principal(User user, boolean mobileLogin) {
//            this.code = user.getCode();
//            this.loginName = user.getUsername();
//            this.name = user.getUsername();
//            this.mobileLogin = mobileLogin;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public String getLoginName() {
//            return loginName;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public boolean isMobileLogin() {
//            return mobileLogin;
//        }
//
//        /**
//         * 获取SESSIONID
//         */
//        public String getSessionid() {
//            try {
//                return (String) UserUtil.getSession().getId();
//            } catch (Exception e) {
//                return "";
//            }
//        }
//
//        @Override
//        public String toString() {
//            return code;
//        }
//    }
}
