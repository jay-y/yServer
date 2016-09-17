package modules.template.api;

import modules.template.model.Template;

import java.util.List;

public interface TemplateService {
    List<Template> findAllCreated();
}
