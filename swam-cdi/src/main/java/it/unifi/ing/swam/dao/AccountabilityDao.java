package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Accountability;
import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.Party;
import java.util.List;

public class AccountabilityDao extends BaseDao<Accountability> {

    public AccountabilityDao() {
        super(Accountability.class);
    }

    public List<Company> findCompaniesByUser(Long userId) {

        String query = "SELECT a.commissioner FROM Accountability a WHERE a.responsible.id = :userId";

        List<Party> result = entityManager
                .createQuery(query, Party.class)
                .setParameter("userId", userId)
                .getResultList();

        return (List<Company>) (List<?>) result;
    }

    public List<Company> findCompaniesByUserAndAccountabilityTypeName(Long userId, String typeName) {

        String query = "SELECT a.commissioner FROM Accountability a WHERE "
                + "a.responsible.id = :userId AND a.accountabilityType.name = :typeName";

        List<Party> result = entityManager
                .createQuery(query, Party.class)
                .setParameter("typeName", typeName)
                .setParameter("userId", userId)
                .getResultList();

        return (List<Company>) (List<?>) result;
    }

    public void removeByCompany(Long companyId) {
        String query = "SELECT a FROM Accountability a WHERE a.commissioner.id = :companyId";

        List<Accountability> result = entityManager
                .createQuery(query, Accountability.class)
                .setParameter("companyId", companyId)
                .getResultList();

        for (Accountability a : result) {
            entityManager.remove(a);
        }
    }

}
