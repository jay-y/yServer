package modules.system.utils;

import modules.system.dao.AreaDao;
import modules.system.dao.MenuDao;
import modules.system.dao.RoleDao;
import modules.system.dao.UserDao;
import modules.system.model.Area;
import modules.system.model.Menu;
import modules.system.model.Role;
import modules.system.model.User;
import modules.system.security.SystemAuthorizingRealm.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.yserver.core.config.SpringContextHolder;
import org.yserver.utils.StringUtils;

import java.util.*;

/**
 * 用户工具类
 */
public class UserUtil
{
    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_MOBILE_ = "mobile_";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "ln";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_AREA_LIST = "areaList";
    public static final String CACHE_MENU_LIST = "menuList";
    public static final String CACHE_USER_MENU_LIST = "userMenuList";
//    public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
    private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
    private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
//    public static final String CACHE_OFFICE_LIST = "officeList";
//    public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static User get(String id)
    {
        User user = (User) CacheUtil.get(USER_CACHE, USER_CACHE_ID_ + id);
        if (user == null)
        {
            user = userDao.findOne(new User(id));
            if (user == null)
            {
                return null;
            }
            CacheUtil.put(USER_CACHE, USER_CACHE_ID_ + user.getCode(), user);
            CacheUtil.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     *
     * @param username
     * @return 取不到返回null
     */
    public static User getByLoginName(String username)
    {
        User user = (User) CacheUtil.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + username);
        if (user == null)
        {
            user = userDao.findByUserName(username);
            if (user == null)
            {
                return null;
            }
            CacheUtil.put(USER_CACHE, USER_CACHE_ID_ + user.getCode(), user);
            CacheUtil.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
        }
        return user;
    }

    /**
     * 查找当前用户的合并用户列表
     *
     * @param user
     * @return
     */
    // public static List<User> findByCombineNo(){
    // return userDao.findUserByCombineNo(getUser());
    // }

    /**
     * 清除当前用户缓存
     */
    public static void clearCache()
    {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_AREA_LIST);
        removeCache(CACHE_MENU_LIST);
        removeCache(CACHE_USER_MENU_LIST);
//        removeCache(CACHE_OFFICE_ALL_LIST);
        UserUtil.clearCache(getUser());
    }

    /**
     * 清除指定用户缓存
     *
     * @param user
     */
    public static void clearCache(User user)
    {
        CacheUtil.remove(USER_CACHE, USER_CACHE_ID_ + user.getCode());
        CacheUtil.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername());
    }

    /**
     * 清除指定当前用户权限菜单缓存
     */
    public static void clearUserMenuListCache()
    {
        removeCache(CACHE_USER_MENU_LIST);
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static User getUser()
    {
        Principal principal = getPrincipal();
        if (principal != null)
        {
            User user = get(principal.getCode());
            if (user != null)
            {
                return user;
            }
            return new User();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    public static List<Role> getRoleList()
    {
        List<Role> list = (List<Role>) getCache(CACHE_ROLE_LIST);
        if (list == null)
        {
            list = roleDao.findAll();
            putCache(CACHE_ROLE_LIST, list);
        }
        return list;
    }

    /**
     * 获取所有区域
     *
     * @return
     */
    public static List<Area> getAreaList()
    {
        List<Area> list = (List<Area>) getCache(CACHE_AREA_LIST);
        if (list == null)
        {
            list = areaDao.findAll();
            putCache(CACHE_AREA_LIST, list);
        }
        return list;
    }

    /**
     * 获取当前用户授权菜单通道(针对原表权限设计问题处理)
     *
     * @return
     */
    public static List<Menu> getMenuList()
    {
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
        if (menuList == null)
        {
            menuList = menuDao.findAll();
            putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取所有菜单数据
     * key:菜单id
     * value:菜单名title
     *
     * @return
     */
    public static Map<String, Object> getMenuMap()
    {
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
        Map<String, Object> map = new HashMap<>();
        if (null == menuList)
        {
            menuList = menuDao.findAll();
        }
        if (menuList != null && menuList.size() > 0)
        {
            for (int i = 0; i < menuList.size(); i++)
            {
                map.put(menuList.get(i).getCode(), menuList.get(i).getName());
            }
        }
        return map;
    }

    /**
     * 获取当前用户授权菜单通道(针对原表权限设计问题处理)
     *
     * @return
     */
    public static List<Menu> getUserMenuList()
    {
        List<Menu> menuList = (List<Menu>) getCache(CACHE_USER_MENU_LIST);
        if (menuList == null)
        {
            User user = getUser();
            menuList = new LinkedList<>();
            if (null != user.getRole() && null != user.getRole().getMenus())
            {
                for (Menu menu : user.getRole().getMenus())
                {
                    menuList.add(menu);
                }
            }
            Collections.sort(menuList, new Comparator<Menu>()
            {
                @Override
                public int compare(Menu o1, Menu o2)
                {
                    return o1.getSort() - o2.getSort();
                }
            });
            putCache(CACHE_USER_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal()
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null)
            {
                return principal;
            }
        }
        catch (UnavailableSecurityManagerException e)
        {
            e.printStackTrace();
        }
        catch (InvalidSessionException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Session getSession()
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null)
            {
                session = subject.getSession();
            }
            if (session != null)
            {
                return session;
            }
        }
        catch (InvalidSessionException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // ============== User Cache ==============

    public static Object getCache(String key)
    {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue)
    {
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value)
    {
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key)
    {
        getSession().removeAttribute(key);
    }

    /**
     * 判断当前用户角色列表是否包含指定角色
     *
     * @param enname
     * @return
     */
    public static boolean isContainRole(String enname)
    {
        return true;
//		List<Role> roleList = getUser().getRoleList();
//		for (Role role : roleList)
//		{
//			if (enname.equals(role.getEnname()))
//			{
//				return true;
//			}
//		}
//		return false;
    }

    /**
     * 根据Mobile获取缓存中的用户
     *
     * @param mobile
     * @return user
     */
    public static User getUserByMobile(String mobile)
    {
        User user = (User) CacheUtil.get(USER_CACHE, USER_CACHE_MOBILE_ + mobile);
        return user;
    }

    /**
     * 将所有的用户放入到userCache中
     *
     * @return 取不到返回null
     */
    public static void setUsersToUserCache()
    {
        List<User> userList = userDao.findAll();
        if (userList != null)
        {
            for (User user : userList)
            {
//				user.setRoleList(roleDao.findList(new Role(user)));
                String mobile = user.getPhone();
                User cacheUser = getUserByMobile(mobile);
                if (mobile != null && cacheUser == null)
                {
                    CacheUtil.put(USER_CACHE, USER_CACHE_MOBILE_ + mobile, user);
                }
            }
        }
    }

    /**
     * 清除指定用户缓存
     *
     * @param mobile
     */
    public static void deleteUserCacheMobile(String mobile)
    {
        if (StringUtils.isNotBlank(mobile))
        {
            // 从缓存中移除
            CacheUtil.remove(USER_CACHE, USER_CACHE_MOBILE_ + mobile);
        }
    }

    /**
     * 新增指定用户缓存
     *
     * @param username
     * @param mobile
     */
    public static void insertUserCacheMobile(String username, String mobile)
    {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(mobile))
        {
            // 新增到缓存
            User user = userDao.findByUserName(username);
//			user.setRoleList(roleDao.findList(new Role(user)));
            if (user != null)
            {
                CacheUtil.put(USER_CACHE, USER_CACHE_MOBILE_ + mobile, user);
            }
        }
    }

    /**
     * 更新指定用户缓存
     *
     * @param newMobile
     * @param oldMobile
     */
    public static void updateUserCacheMobile(String newMobile, String oldMobile)
    {
        if (StringUtils.isNotBlank(newMobile) && StringUtils.isNotBlank(oldMobile))
        {
            // 更新到缓存
            User user = getUserByMobile(oldMobile);
            if (user != null)
            {
                user = userDao.findOne(user);
//				user.setRoleList(roleDao.findList(new Role(user)));
                CacheUtil.remove(USER_CACHE, USER_CACHE_MOBILE_ + oldMobile);
                CacheUtil.put(USER_CACHE, USER_CACHE_MOBILE_ + newMobile, user);
            }
        }
    }
}
