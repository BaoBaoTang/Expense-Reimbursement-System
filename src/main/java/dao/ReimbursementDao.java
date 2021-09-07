package dao;

import model.ReimbursementDetailView;
import model.ReimbursementView;

import java.util.List;

/**
 * @author Zimi Li
 */
public interface ReimbursementDao {

    List<ReimbursementView> getAllReimbursement(Integer userID);

    Boolean approveReimbursement(Integer reimbursementID, Integer userID);

    Boolean denyReimbursement(Integer reimbursementID, Integer userID);

    Boolean createReimbursement(Integer amount, String description, Integer type, Integer author, byte[] receipt);

    ReimbursementDetailView getReimbursementDetails(Integer reimbursementID);
}
