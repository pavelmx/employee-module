package com.innowise.employeemodule.entity;

import com.innowise.employeemodule.entity.Position;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPosition is a Querydsl query type for Position
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPosition extends EntityPathBase<Position> {

    private static final long serialVersionUID = -988861609L;

    public static final QPosition position = new QPosition("position1");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QPosition(String variable) {
        super(Position.class, forVariable(variable));
    }

    public QPosition(Path<? extends Position> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPosition(PathMetadata metadata) {
        super(Position.class, metadata);
    }

}

