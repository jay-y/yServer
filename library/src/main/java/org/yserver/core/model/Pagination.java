package org.yserver.core.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.yserver.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Pagination<T>.<br>
 * Date: 2016/9/20 14:01<br>
 * Author: ysj
 */
public class Pagination<T> extends RequestEntity
{
    private String sortValue;

    private String sortMode;

    private int page = 0;

    private int size = 10;

    private int index = 0;

    private List<T> content = new ArrayList<>();

    private int count = 0;

    public Pageable getPageable()
    {
        Sort sort = null;
        if (StringUtils.isNotBlank(sortMode) && StringUtils.isNotBlank(sortValue))
        {
            sort = new Sort(Sort.Direction.fromString(sortMode), sortValue);
        }
        return new PageRequest(page, size, sort);
    }

    public String getSortValue()
    {
        return sortValue;
    }

    public void setSortValue(String sortValue)
    {
        this.sortValue = sortValue;
    }

    public String getSortMode()
    {
        return sortMode;
    }

    public void setSortMode(String sortMode)
    {
        this.sortMode = sortMode;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getIndex()
    {
        this.index = page * size;
        return index;
    }

    public List<T> getContent()
    {
        return content;
    }

    public void setContent(List<T> content)
    {
        this.content = content;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
