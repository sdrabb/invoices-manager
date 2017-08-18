package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Company;
import javax.ejb.Stateless;

@Stateless
public class CompanyDao extends BaseDao<Company> {

    public CompanyDao() {
        super(Company.class);
    }

}
