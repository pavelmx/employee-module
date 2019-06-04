package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.AbstractEntity;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity, N extends Serializable, Q extends EntityPathBase<E>> extends JpaRepository<E, N>,
        QuerydslPredicateExecutor<E>, QuerydslBinderCustomizer<Q> {

    @Override
    public default void customize(final QuerydslBindings bindings, final Q q) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
