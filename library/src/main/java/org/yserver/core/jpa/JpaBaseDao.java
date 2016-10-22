package org.yserver.core.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Description: JpaBaseDao.<br>
 * Date: 2016/9/7 23:43<br>
 * Author: ysj
 */
@NoRepositoryBean
public interface JpaBaseDao<T, ID extends Serializable> extends
        JpaSpecificationExecutor<T>, JpaRepository<T, ID>,
        PagingAndSortingRepository<T, ID> {

    Page<T> findAll(String jsonParams, Pageable pageable);
}
