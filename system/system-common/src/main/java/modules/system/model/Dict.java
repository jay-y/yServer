package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.*;

/**
 * Description: 数据字典.<br>
 * Date: 2016/10/13 14:17<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_dict")
public class Dict extends BaseEntity {
    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "VALUE", nullable = false)
    private String value;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SORT", nullable = false)
    private int sort;

    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private User creator;

    @OneToOne
    @JoinColumn(name = "UPDATED_BY")
    private User editor;

    public Dict() {
        super();
    }

    public Dict(String code) {
        super(code);
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }
}