package com.cheng.gamerecorder.repository;

import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 查找所有未删除的对象
     * @return 所有对象
     */
    @Override
    @Query("select e from #{#entityName} e where e.deleted=false")
    List<T> findAll();

    /**
     * 条件查询未删除的对象
     * @param criteria 查询条件
     * @return 对象
     */
    @Where(clause="deleted<>1")
    List<T> findAll(Object criteria);

    /**
     * 查找逻辑删除的对象
      * @return
     */
    @Query("select e from #{#entityName} e where e.deleted=true")
    List<T> recycleBin();

    /**
     * 逻辑删除
     * @param id id
     */
    @Query("update #{#entityName} e set e.deleted=true where e.id=?1")
    @Modifying
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    void softDelete(Long id);

    @Override
    @Where(clause="deleted<>1")
    Optional<T> findOne(@Nullable Specification<T> spec);

    @Override
    @Where(clause="deleted<>1")
    Page<T> findAll(Pageable pageable);
    @Override
    @Where(clause="deleted<>1")
    List<T> findAll(@Nullable Specification<T> spec);

    @Override
    @Where(clause="deleted<>1")
    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

    @Override
    @Where(clause="deleted<>1")
    List<T> findAll(@Nullable Specification<T> spec, Sort sort);
}