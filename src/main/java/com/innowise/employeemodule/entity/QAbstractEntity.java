package com.innowise.employeemodule.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QAbstractEntity is a Querydsl query type for AbstractEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QAbstractEntity extends EntityPathBase<AbstractEntity> {

    private static final long serialVersionUID = -2125310381L;

    public static final QAbstractEntity abstractEntity = new QAbstractEntity("abstractEntity");

    public final DateTimePath<java.time.LocalDateTime> created = createDateTime("created", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updated = createDateTime("updated", java.time.LocalDateTime.class);

    public QAbstractEntity(String variable) {
        super(AbstractEntity.class, forVariable(variable));
    }

    public QAbstractEntity(Path<? extends AbstractEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractEntity(PathMetadata metadata) {
        super(AbstractEntity.class, metadata);
    }

}

