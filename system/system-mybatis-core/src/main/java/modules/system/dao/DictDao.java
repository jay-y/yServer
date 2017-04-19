package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Dict;
import org.apache.ibatis.annotations.Param;
import org.yserver.core.mybatis.annotation.MyBatisMapper;

import java.util.List;

/**
 * Description: DictDao.<br>
 * Date: 2016/9/23 16:57<br>
 * Author: ysj
 */
@MyBatisMapper
public interface DictDao extends SysBaseDao<Dict> {
    List<Dict> findListByType(String type);

    Dict findByTypeAndValue(@Param(value = "type") String type, @Param(value = "val") String val);
}
