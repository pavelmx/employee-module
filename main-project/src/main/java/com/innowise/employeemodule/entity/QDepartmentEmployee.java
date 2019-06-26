package com.innowise.employeemodule.entity;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QDepartmentEmployee is a Querydsl query type for DepartmentEmployee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDepartmentEmployee extends EntityPathBase<DepartmentEmployee> {

    private static final long serialVersionUID = -754299698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartmentEmployee departmentEmployee = new QDepartmentEmployee("departmentEmployee");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final QDepartment department;

    public final QEmployee employee;

    public final DatePath<java.time.LocalDate> endDateInDepartment = createDate("endDateInDepartment", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCurrentDepartment = createBoolean("isCurrentDepartment");

    public final DatePath<java.time.LocalDate> startDateInDepartment = createDate("startDateInDepartment", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QDepartmentEmployee(String variable) {
        this(DepartmentEmployee.class, forVariable(variable), INITS);
    }

    public QDepartmentEmployee(Path<? extends DepartmentEmployee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDepartmentEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDepartmentEmployee(PathMetadata metadata, PathInits inits) {
        this(DepartmentEmployee.class, metadata, inits);
    }

    public QDepartmentEmployee(Class<? extends DepartmentEmployee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
    }

}

