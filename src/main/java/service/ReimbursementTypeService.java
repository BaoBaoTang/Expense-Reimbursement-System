package service;

import dao.ReimbursementTypeDao;
import dao.ReimbursementTypeDaoImpl;
import model.ReimbursementType;

import java.util.List;

/**
 * @author Zimi Li
 */
public class ReimbursementTypeService {

    ReimbursementTypeDao reimbursementTypeDao;

    public ReimbursementTypeService() {
        reimbursementTypeDao = new ReimbursementTypeDaoImpl();
    }

    public List<ReimbursementType> getTypes() {
        return reimbursementTypeDao.getTypes();
    }
}
