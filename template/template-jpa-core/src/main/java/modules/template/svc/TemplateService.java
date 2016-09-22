package modules.template.svc;

import modules.template.model.Template;

import java.util.List;

public interface TemplateService {
    List<Template> findAllCreated();
}
