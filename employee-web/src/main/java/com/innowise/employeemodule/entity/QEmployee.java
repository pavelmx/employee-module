package com.innowise.employeemodule.entity;

import com.innowise.employeemodule.entity.Employee;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -543196964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final QPersonalInfo personalInfo;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.personalInfo = inits.isInitialized("personalInfo") ? new QPersonalInfo(forProperty("personalInfo")) : null;
    }

}

