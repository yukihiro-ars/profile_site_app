﻿DROP TABLE IF EXISTS business.NODE_RELATIONSHIP;
CREATE TABLE business.NODE_RELATIONSHIP(
    PARENT_NODE_ID integer NOT NULL,
    CHILD_NODE_ID integer NOT NULL,
    SORT smallint NOT NULL,
    UP_DT timestamp,
    UP_NM varchar(20),
    RG_DT timestamp,
    RG_NM varchar(20),
    VERSION bigint NOT NULL,
    CONSTRAINT pk_NODE_RELATIONSHIP PRIMARY KEY (PARENT_NODE_ID,CHILD_NODE_ID)
);
COMMENT ON COLUMN business.NODE_RELATIONSHIP.PARENT_NODE_ID IS '親ノードID';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.CHILD_NODE_ID IS '子ノードID';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.SORT IS '表示順';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.UP_DT IS '更新日';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.UP_NM IS '更新者';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.RG_DT IS '登録日';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.RG_NM IS '登録者';
COMMENT ON COLUMN business.NODE_RELATIONSHIP.VERSION IS 'バージョン';
COMMENT ON TABLE business.NODE_RELATIONSHIP IS 'ノード関連付け';
