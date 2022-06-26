package com.jock.unmisa.entity.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -854497766L;

    public static final QCategory category = new QCategory("category");

    public final NumberPath<Integer> category_id = createNumber("category_id", Integer.class);

    public final StringPath category_nm = createString("category_nm");

    public final NumberPath<Integer> category_order = createNumber("category_order", Integer.class);

    public final BooleanPath category_use_yn = createBoolean("category_use_yn");

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

