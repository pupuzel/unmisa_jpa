package com.jock.unmisa.entity.diary;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiary is a Querydsl query type for Diary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiary extends EntityPathBase<Diary> {

    private static final long serialVersionUID = 1130131652L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiary diary = new QDiary("diary");

    public final DateTimePath<java.time.LocalDateTime> cre_date = createDateTime("cre_date", java.time.LocalDateTime.class);

    public final NumberPath<Integer> diary_cmt_cnt = createNumber("diary_cmt_cnt", Integer.class);

    public final StringPath diary_content = createString("diary_content");

    public final EnumPath<com.jock.unmisa.entity.domain.DiaryDay> diary_day = createEnum("diary_day", com.jock.unmisa.entity.domain.DiaryDay.class);

    public final NumberPath<Integer> diary_id = createNumber("diary_id", Integer.class);

    public final NumberPath<Integer> diary_like_cnt = createNumber("diary_like_cnt", Integer.class);

    public final StringPath diary_place = createString("diary_place");

    public final StringPath diary_ymd = createString("diary_ymd");

    public final DateTimePath<java.time.LocalDateTime> upd_date = createDateTime("upd_date", java.time.LocalDateTime.class);

    public final com.jock.unmisa.entity.user.QUser user;

    public QDiary(String variable) {
        this(Diary.class, forVariable(variable), INITS);
    }

    public QDiary(Path<? extends Diary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiary(PathMetadata metadata, PathInits inits) {
        this(Diary.class, metadata, inits);
    }

    public QDiary(Class<? extends Diary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.jock.unmisa.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

