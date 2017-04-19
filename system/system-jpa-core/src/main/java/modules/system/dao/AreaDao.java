package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.Area;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Description: AreaDao.<br>
 * Date: 2016/9/23 16:57<br>
 * Author: ysj
 */
public interface AreaDao extends SysBaseDao<Area> {

    @Query("select o from Area o where o.type = 0")
    List<Area> findAllParent();

    @Query("select o from Area o where o.type > :tp and o.parent.code = :pcode")
    List<Area> findAllChild(@Param("tp") String type, @Param("pcode") String pcode);

    @Query("select o from Area o where o.type = :tp and o.parent.code = :pcode")
    List<Area> findAllChildByTypeAndPcode(@Param("tp") String type, @Param("pcode") String pcode);
}
