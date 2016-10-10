package modules.template.controller.base;

import org.springframework.beans.factory.annotation.Value;
import org.yserver.core.controller.BaseController;

/**
 * Description: SysBaseController.<br>
 * Date: 2016/9/20 21:11<br>
 * Author: ysj
 */
public class TmpBaseController extends BaseController {
    @Value("${adminPath}")
    protected String adminPath;
}
