package dao;

import config.ConfigurationFile;
import model.ReimbursementType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zimi Li
 */
public class ReimbursementTypeDaoImpl extends Command implements ReimbursementTypeDao{

    @Override
    public List<ReimbursementType> getTypes() {
        ResultSet rs = select("SELECT * FROM ers_reimbursement_type;");
        List<ReimbursementType> ret = new ArrayList<>();
        try {
            while (rs.next()) {
                ret.add(new ReimbursementType(rs.getInt("reime_type_id"),
                                              rs.getString("reime_type")));
            }
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
        return ret;
    }
}
