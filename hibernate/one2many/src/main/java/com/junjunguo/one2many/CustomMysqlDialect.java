package com.junjunguo.one2many;

import org.hibernate.dialect.MySQL5InnoDBDialect;
/**
 * This file is part of one2many.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */

public class CustomMysqlDialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
