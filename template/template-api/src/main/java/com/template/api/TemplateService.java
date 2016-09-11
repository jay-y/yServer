package com.template.api;

import com.template.model.Template;

import java.util.List;

public interface TemplateService {
    List<Template> findAllCreated();
}
