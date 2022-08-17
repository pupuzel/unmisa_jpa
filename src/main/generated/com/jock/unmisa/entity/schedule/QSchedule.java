package com.jock.unmisa.entity.schedule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchedule is a Querydsl query type for Schedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchedule extends EntityPathBase<Schedule> {

    private static final long serialVersionUID = 125149754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchedule schedule = new QSchedule("schedule");

    public final com.jock.unmisa.entity.category.QCategory category;

    public final NumberPath<Integer> merge_schedule1 = createNumber("merge_schedule1", Integer.class);

    public final NumberPath<Integer> merge_schedule2 = createNumber("merge_schedule2", Integer.class);

    public final NumberPath<Integer> merge_schedule3 = createNumber("merge_schedule3", Integer.class);

    public final NumberPath<Integer> merge_schedule4 = createNumber("merge_schedule4", Integer.class);

    public final NumberPath<Integer> merge_schedule5 = createNumber("merge_schedule5", Integer.class);

    public final BooleanPath merge_yn = createBoolean("merge_yn");

    public final BooleanPath open_yn = createBoolean("open_yn");

    public final StringPath schedule_comment = createString("schedule_comment");

    public final NumberPath<Integer> schedule_id = createNumber("schedule_id", Integer.class);

    public final StringPath schedule_title = createString("schedule_title");

    public final StringPath target_day1 = createString("target_day1");

    public final StringPath target_day2 = createString("target_day2");

    public final StringPath target_day3 = createString("target_day3");

    public final StringPath target_day4 = createString("target_day4");

    public final StringPath target_day5 = createString("target_day5");

    public final StringPath target_day6 = createString("target_day6");

    public final StringPath target_day7 = createString("target_day7");

    public final com.jock.unmisa.entity.user.QUser user;

    public final StringPath workout_day1 = createString("workout_day1");

    public final StringPath workout_day2 = createString("workout_day2");

    public final StringPath workout_day3 = createString("workout_day3");

    public final StringPath workout_day4 = createString("workout_day4");

    public final StringPath workout_day5 = createString("workout_day5");

    public final StringPath workout_day6 = createString("workout_day6");

    public final StringPath workout_day7 = createString("workout_day7");

    public QSchedule(String variable) {
        this(Schedule.class, forVariable(variable), INITS);
    }

    public QSchedule(Path<? extends Schedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchedule(PathMetadata metadata, PathInits inits) {
        this(Schedule.class, metadata, inits);
    }

    public QSchedule(Class<? extends Schedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.jock.unmisa.entity.category.QCategory(forProperty("category")) : null;
        this.user = inits.isInitialized("user") ? new com.jock.unmisa.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

