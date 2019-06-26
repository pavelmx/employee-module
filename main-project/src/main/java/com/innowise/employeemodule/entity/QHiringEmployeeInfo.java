package com.innowise.employeemodule.entity;

import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QHiringEmployeeInfo is a Querydsl query type for HiringEmployeeInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHiringEmployeeInfo extends EntityPathBase<HiringEmployeeInfo> {

    private static final long serialVersionUID = 1110428283L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHiringEmployeeInfo hiringEmployeeInfo = new QHiringEmployeeInfo("hiringEmployeeInfo");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final DatePath<java.time.LocalDate> dateOfDismissal = createDate("dateOfDismissal", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> dateOfHiring = createDate("dateOfHiring", java.time.LocalDate.class);

    public final QEmployee employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QHiringEmployeeInfo(String variable) {
        this(HiringEmployeeInfo.class, forVariable(variable), INITS);
    }

    public QHiringEmployeeInfo(Path<? extends HiringEmployeeInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHiringEmployeeInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHiringEmployeeInfo(PathMetadata metadata, PathInits inits) {
        this(HiringEmployeeInfo.class, metadata, inits);
    }

    public QHiringEmployeeInfo(Class<? extends HiringEmployeeInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
    }

}

