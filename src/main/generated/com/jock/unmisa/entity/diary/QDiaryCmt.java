package com.jock.unmisa.entity.diary;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiaryCmt is a Querydsl query type for DiaryCmt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiaryCmt extends EntityPathBase<DiaryCmt> {

    private static final long serialVersionUID = -496520730L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiaryCmt diaryCmt = new QDiaryCmt("diaryCmt");

    public final com.jock.unmisa.entity.common.QCommonDateEntity _super = new com.jock.unmisa.entity.common.QCommonDateEntity(this);

    public final NumberPath<Integer> bundle_cmt_cnt = createNumber("bundle_cmt_cnt", Integer.class);

    public final NumberPath<Integer> bundle_cmt_id = createNumber("bundle_cmt_id", Integer.class);

    public final StringPath cmt_content = createString("cmt_content");

    public final NumberPath<Integer> cmt_depth = createNumber("cmt_depth", Integer.class);

    public final NumberPath<Integer> cmt_id = createNumber("cmt_id", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> cre_date = _super.cre_date;

    public final QDiary diary;

    public final StringPath link_user_id = createString("link_user_id");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> upd_date = _super.upd_date;

    public final com.jock.unmisa.entity.user.QUser user;

    public QDiaryCmt(String variable) {
        this(DiaryCmt.class, forVariable(variable), INITS);
    }

    public QDiaryCmt(Path<? extends DiaryCmt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiaryCmt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiaryCmt(PathMetadata metadata, PathInits inits) {
        this(DiaryCmt.class, metadata, inits);
    }

    public QDiaryCmt(Class<? extends DiaryCmt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diary = inits.isInitialized("diary") ? new QDiary(forProperty("diary"), inits.get("diary")) : null;
        this.user = inits.isInitialized("user") ? new com.jock.unmisa.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

