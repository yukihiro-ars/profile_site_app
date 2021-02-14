﻿DROP TABLE IF EXISTS business.CONTENTS;
CREATE TABLE business.CONTENTS(
    CONTENTS_ID serial NOT NULL,
    CONTENTS_TYPE char(1) NOT NULL,
    UP_DT timestamp,
    UP_NM varchar(20),
    RG_DT timestamp,
    RG_NM varchar(20),
    VERSION bigint NOT NULL,
    CONSTRAINT pk_CONTENTS PRIMARY KEY (CONTENTS_ID)
);
COMMENT ON COLUMN business.CONTENTS.CONTENTS_ID IS 'コンテンツID';
COMMENT ON COLUMN business.CONTENTS.CONTENTS_TYPE IS 'コンテンツ種別';
COMMENT ON COLUMN business.CONTENTS.UP_DT IS '更新日';
COMMENT ON COLUMN business.CONTENTS.UP_NM IS '更新者';
COMMENT ON COLUMN business.CONTENTS.RG_DT IS '登録日';
COMMENT ON COLUMN business.CONTENTS.RG_NM IS '登録者';
COMMENT ON COLUMN business.CONTENTS.VERSION IS 'バージョン';
COMMENT ON TABLE business.CONTENTS IS 'コンテンツ';
