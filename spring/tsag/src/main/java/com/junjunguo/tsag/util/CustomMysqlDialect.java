package com.junjunguo.tsag.util;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by GuoJunjun <junjunguo.com> on 15/10/15.
 */
public class CustomMysqlDialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
