package modules.system.utils;

import modules.system.dao.DictDao;
import modules.system.model.Dict;
import org.yserver.core.config.SpringContextHolder;

import java.util.List;

/**
 * 用户工具类
 */
public class SysUtil
{
    private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

    /**
     * 获取字典列表(指定类型)
     *
     * @return
     */
    public static List<Dict> getDictList(String type)
    {
        return dictDao.findListByType(type);
    }

    /**
     * 获取字典对象(指定类型、值)
     *
     * @param type
     * @param value
     * @return
     */
    public static Dict getDict(String type, String value)
    {
        return dictDao.findByTypeAndValue(type, value);
    }
}
