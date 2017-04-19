package modules.system.dao;

import modules.system.dao.base.SysBaseDao;
import modules.system.model.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Description: FileDao.<br>
 * Date: 2016/9/10 1:14<br>
 * Author: ysj
 */
public interface FileDao extends SysBaseDao<File> {

    @Query("select o from File o where o.name = :name")
    File findByName(@Param(value = "name") String name);

    @Query("select o from File o where o.remarks = :remarks")
    File findByRemarks(@Param(value = "remarks") String remarks);
}
