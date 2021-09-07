package dao;

import config.ConfigurationFile;
import model.ReimbursementDetailView;
import model.ReimbursementView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zimi Li
 *
 */
public class ReimbursementImpl extends Command implements ReimbursementDao{

    @Override
    public List<ReimbursementView> getAllReimbursement(Integer userID) {
        ResultSet rs;
        if (userID == null) rs = select("SELECT * FROM reimbursement_view;");
        else rs = select("SELECT * FROM reimbursement_view WHERE reime_author = ?;", userID);
        List<ReimbursementView> ret = new ArrayList<>();
        try {
            while (rs.next()) {
                ret.add(new ReimbursementView(rs.getInt("reime_id"),
                                              rs.getInt("reime_amount"),
                                              rs.getString("reime_description"),
                                              rs.getString("author_first_name") + " " + rs.getString("author_last_name"),
                                              rs.getString("reime_status"),
                                              rs.getString("reime_type")
                                          ));
            }
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
        return ret;
    }

    @Override
    public Boolean approveReimbursement(Integer reimbursementID, Integer userID) {
        return update("UPDATE ers_reimbursement SET reime_resolved = current_timestamp, reime_resolver = ?, reime_status_id = ? WHERE reime_id = ?;",
               userID, ConfigurationFile.APPROVED, reimbursementID);
    }

    @Override
    public Boolean denyReimbursement(Integer reimbursementID, Integer userID) {
        return update("UPDATE ers_reimbursement SET reime_resolved = current_timestamp, reime_resolver = ?, reime_status_id = ? WHERE reime_id = ?;",
               userID, ConfigurationFile.DENIED, reimbursementID);
    }

    @Override
    public Boolean createReimbursement(Integer amount, String description, Integer type, Integer author, byte[] receipt) {
        return update("INSERT INTO ers_reimbursement VALUES (DEFAULT, ?, CURRENT_TIMESTAMP, NULL, ?, ?, ?, NULL, ?, ?);",
                      amount, description, receipt, author, ConfigurationFile.PENDING, type);
    }

    @Override
    public ReimbursementDetailView getReimbursementDetails(Integer reimbursementID) {
        ResultSet rs = select("SELECT * FROM reimbursement_detail_view WHERE reime_id = ?;", reimbursementID);
        try {
            if (rs.next()) {
                return new ReimbursementDetailView(rs.getInt("reime_id"),
                                                   rs.getInt("reime_amount"),
                                                   rs.getString("reime_submitted"),
                                                   rs.getString("reime_resolved"),
                                                   rs.getString("reime_description"),
                                                   rs.getBytes("reime_receipt"),
                                                   rs.getString("author_first_name") + " " + rs.getString("author_last_name"),
                                                   rs.getString("resolver_first_name") == null ? null : rs.getString("resolver_first_name") + " " + rs.getString("resolver_last_name"),
                                                   rs.getString("reime_status"),
                                                   rs.getString("reime_type"),
                                                   rs.getInt("reime_author"));
            }
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
        return null;
    }
}
