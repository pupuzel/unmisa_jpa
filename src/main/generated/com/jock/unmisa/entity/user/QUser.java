package com.jock.unmisa.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1308521018L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final BooleanPath email_yn = createBoolean("email_yn");

    public final StringPath id = createString("id");

    public final StringPath oauth_client_id = createString("oauth_client_id");

    public final EnumPath<com.jock.unmisa.entity.domain.OauthType> oauth_type = createEnum("oauth_type", com.jock.unmisa.entity.domain.OauthType.class);

    public final StringPath user_age_range = createString("user_age_range");

    public final StringPath user_area = createString("user_area");

    public final StringPath user_birth = createString("user_birth");

    public final StringPath user_email = createString("user_email");

    public final EnumPath<com.jock.unmisa.entity.domain.UserGender> user_gender = createEnum("user_gender", com.jock.unmisa.entity.domain.UserGender.class);

    public final StringPath user_intro = createString("user_intro");

    public final QUserMeta user_meta;

    public final StringPath user_nm = createString("user_nm");

    public final StringPath user_profile_img = createString("user_profile_img");

    public final StringPath user_simple_intro = createString("user_simple_intro");

    public final StringPath user_site = createString("user_site");

    public final StringPath user_sns = createString("user_sns");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user_meta = inits.isInitialized("user_meta") ? new QUserMeta(forProperty("user_meta"), inits.get("user_meta")) : null;
    }

}

