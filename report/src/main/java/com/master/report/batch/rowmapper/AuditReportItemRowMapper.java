package com.master.report.batch.rowmapper;

import com.master.report.batch.model.AuditReportItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditReportItemRowMapper implements RowMapper<AuditReportItem> {

    @Override
    public AuditReportItem mapRow(ResultSet resultSet, int i) throws SQLException {
        AuditReportItem item = new AuditReportItem();
        item.setId(resultSet.getInt("id"));
        item.setUserId(resultSet.getString("username"));
        return item;
    }

}