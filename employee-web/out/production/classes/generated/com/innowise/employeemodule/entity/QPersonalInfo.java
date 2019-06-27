package com.innowise.employeemodule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonalInfo is a Querydsl query type for PersonalInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonalInfo extends EntityPathBase<PersonalInfo> {

    private static final long serialVersionUID = -151279044L;

    public static final QPersonalInfo personalInfo = new QPersonalInfo("personalInfo");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath adress = createString("adress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath skype = createString("skype");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QPersonalInfo(String variable) {
        super(PersonalInfo.class, forVariable(variable));
    }

    public QPersonalInfo(Path<? extends PersonalInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonalInfo(PathMetadata metadata) {
        super(PersonalInfo.class, metadata);
    }

}

