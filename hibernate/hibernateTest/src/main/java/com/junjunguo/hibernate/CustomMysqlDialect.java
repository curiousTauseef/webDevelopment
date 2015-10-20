package com.junjunguo.hibernate;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by GuoJunjun <junjunguo.com> on 20/10/15.
 */

public class CustomMysqlDialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
