package com.jock.unmisa.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMeta is a Querydsl query type for UserMeta
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMeta extends EntityPathBase<UserMeta> {

    private static final long serialVersionUID = 1758154591L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMeta userMeta = new QUserMeta("userMeta");

    public final StringPath last_diary_ymd = createString("last_diary_ymd");

    public final DateTimePath<java.time.LocalDateTime> last_login_date = createDateTime("last_login_date", java.time.LocalDateTime.class);

    public final StringPath last_login_ip = createString("last_login_ip");

    public final DateTimePath<java.time.LocalDateTime> register_date = createDateTime("register_date", java.time.LocalDateTime.class);

    public final StringPath register_ip = createString("register_ip");

    public final QUser user;

    public final NumberPath<Integer> user_meta_id = createNumber("user_meta_id", Integer.class);

    public final EnumPath<com.jock.unmisa.entity.domain.UserState> user_state = createEnum("user_state", com.jock.unmisa.entity.domain.UserState.class);

    public QUserMeta(String variable) {
        this(UserMeta.class, forVariable(variable), INITS);
    }

    public QUserMeta(Path<? extends UserMeta> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMeta(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMeta(PathMetadata metadata, PathInits inits) {
        this(UserMeta.class, metadata, inits);
    }

    public QUserMeta(Class<? extends UserMeta> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

