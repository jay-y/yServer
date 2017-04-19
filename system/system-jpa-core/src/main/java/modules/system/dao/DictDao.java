package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Dict;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Description: DictDao.<br>
 * Date: 2016/9/23 16:57<br>
 * Author: ysj
 */
public interface DictDao extends SysBaseDao<Dict> {

    @Query("select o from Dict o where o.type = :type order by o.sort")
    List<Dict> findListByType(@Param("type") String type);

    @Query("select o from Dict o where o.type = :type and o.value =:val")
    Dict findByTypeAndValue(@Param("type") String type, @Param("val") String val);
}
