package service;

import dao.ReimbursementDao;
import dao.ReimbursementImpl;
import model.Reimbursement;
import model.ReimbursementDetailView;
import model.ReimbursementView;

import java.util.List;

/**
 * @author Zimi Li
 */
public class ReimbursementService {
    ReimbursementDao reimbursementDao;

    public ReimbursementService() {
        reimbursementDao = new ReimbursementImpl();
    }

    public Boolean approveReimbursement(Integer reimbursementID, Integer userID) {
        return reimbursementDao.approveReimbursement(reimbursementID, userID);
    }

    public Boolean denyReimbursement(Integer reimbursementID, Integer userID) {
        return reimbursementDao.denyReimbursement(reimbursementID, userID);
    }

    public Boolean createReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.createReimbursement(reimbursement.getAmount(), reimbursement.getDescription(), reimbursement.getType(), reimbursement.getAuthor(), reimbursement.getReceipt());
    }

    public List<ReimbursementView> getReimbursement(Integer userID) {
        return reimbursementDao.getAllReimbursement(userID);
    }

    public ReimbursementDetailView getReimbursementDetails(Integer reimbursementID) {
        return reimbursementDao.getReimbursementDetails(reimbursementID);
    }
}
