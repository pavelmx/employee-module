package com.innowise.employeemodule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPositionEmployee is a Querydsl query type for PositionEmployee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPositionEmployee extends EntityPathBase<PositionEmployee> {

    private static final long serialVersionUID = 1928539941L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPositionEmployee positionEmployee = new QPositionEmployee("positionEmployee");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath description = createString("description");

    public final QEmployee employee;

    public final DateTimePath<java.time.LocalDateTime> endDateForPosition = createDateTime("endDateForPosition", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosition position;

    public final DateTimePath<java.time.LocalDateTime> startDateForPosition = createDateTime("startDateForPosition", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QPositionEmployee(String variable) {
        this(PositionEmployee.class, forVariable(variable), INITS);
    }

    public QPositionEmployee(Path<? extends PositionEmployee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPositionEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPositionEmployee(PathMetadata metadata, PathInits inits) {
        this(PositionEmployee.class, metadata, inits);
    }

    public QPositionEmployee(Class<? extends PositionEmployee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.position = inits.isInitialized("position") ? new QPosition(forProperty("position")) : null;
    }

}

