package com.jock.unmisa.entity.diary;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiaryLikeHist is a Querydsl query type for DiaryLikeHist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiaryLikeHist extends EntityPathBase<DiaryLikeHist> {

    private static final long serialVersionUID = -505335747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiaryLikeHist diaryLikeHist = new QDiaryLikeHist("diaryLikeHist");

    public final DateTimePath<java.time.LocalDateTime> cre_date = createDateTime("cre_date", java.time.LocalDateTime.class);

    public final QDiary diary;

    public final NumberPath<Integer> like_id = createNumber("like_id", Integer.class);

    public final BooleanPath like_yn = createBoolean("like_yn");

    public final DateTimePath<java.time.LocalDateTime> upd_date = createDateTime("upd_date", java.time.LocalDateTime.class);

    public final com.jock.unmisa.entity.user.QUser user;

    public QDiaryLikeHist(String variable) {
        this(DiaryLikeHist.class, forVariable(variable), INITS);
    }

    public QDiaryLikeHist(Path<? extends DiaryLikeHist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiaryLikeHist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiaryLikeHist(PathMetadata metadata, PathInits inits) {
        this(DiaryLikeHist.class, metadata, inits);
    }

    public QDiaryLikeHist(Class<? extends DiaryLikeHist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diary = inits.isInitialized("diary") ? new QDiary(forProperty("diary"), inits.get("diary")) : null;
        this.user = inits.isInitialized("user") ? new com.jock.unmisa.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

