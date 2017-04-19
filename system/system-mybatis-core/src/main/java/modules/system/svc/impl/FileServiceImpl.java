package modules.system.svc.impl;

import modules.system.dao.FileDao;
import modules.system.model.File;
import modules.system.svc.FileService;
import modules.system.svc.base.SysBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yserver.core.datasource.DataSource;
import org.yserver.y;

/**
 * Description: FileServiceImpl.<br>
 * Date: 2016/9/14 20:09<br>
 * Author: ysj
 */
@Service
@DataSource("default")
@Transactional
public class FileServiceImpl
        extends SysBaseServiceImpl<File, FileDao> implements FileService {
    @Autowired
    private FileDao dao;

    @Override
    public FileDao getDao() {
        return dao;
    }

    @Override
    public File find(String id) {
        return getDao().findOne(new File(id));
    }

    @Override
    public File findByName(String name) {
        File entity = new File();
        entity.setName(name);
        try {
            return getDao().findOne(entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage());
        }
        return null;
    }

    @Override
    public File findByRemarks(String remarks) {
        File entity = new File();
        entity.setRemarks(remarks);
        try {
            return getDao().findOne(entity);
        } catch (Throwable e) {
            y.log().error(e.getMessage());
        }
        return null;
    }
}
