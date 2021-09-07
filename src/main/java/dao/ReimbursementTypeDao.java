package dao;

import model.ReimbursementType;

import java.util.List;

/**
 * @author Zimi Li
 */
public interface ReimbursementTypeDao {
    List<ReimbursementType> getTypes();
}
