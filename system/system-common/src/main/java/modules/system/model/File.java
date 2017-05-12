package modules.system.model;

import org.yserver.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description: File.<br>
 * Date: 2016/10/10 18:54<br>
 * Author: ysj
 */
@Entity
@Table(name = "sys_file")
public class File extends BaseEntity
{

    @Column(name = "NAME")
    private String name;//文件名

    @Column(name = "URI", nullable = false)
    private String uri;

    @Column(name = "TYPE", nullable = false)
    private String type = "image";//file,image,audio,video

    @Column(name = "FORMAT", nullable = false)
    private String format = "png";//jpg,jpeg,png,mp3,mp4,xls,xlsx,doc...

    @Column(name = "SIZE")
    private double size = 0;

    public File()
    {
        super();
    }

    public File(String code)
    {
        super(code);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }
}
