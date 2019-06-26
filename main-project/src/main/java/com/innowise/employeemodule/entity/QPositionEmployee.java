package com.innowise.employeemodule.entity;

import com.innowise.employeemodule.entity.PositionEmployee;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


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

    public final QEmployee employee;

    public final DatePath<java.time.LocalDate> endDateForPosition = createDate("endDateForPosition", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosition position;

    public final DatePath<java.time.LocalDate> startDateForPosition = createDate("startDateForPosition", java.time.LocalDate.class);

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

