package modules.system.svc;

import modules.system.dao.FileDao;
import modules.system.model.File;
import modules.system.svc.base.SysBaseService;

/**
 * Description: FileService.<br>
 * Date: 2016/9/18 15:28<br>
 * Author: ysj
 */
public interface FileService extends SysBaseService<File, FileDao> {
    File findByName(String name);

    File findByRemarks(String remarks);
}
