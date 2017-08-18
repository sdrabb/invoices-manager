package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.dao.AccountabilityDao;
import it.unifi.ing.swam.model.Company;
import java.util.List;
import javax.inject.Inject;

public class ListBuyerCompaniesPageController extends UserPermissionController {

    @Inject
    private AccountabilityDao accountabilityDao;

    private List<Company> listCompaniesData;

    public List<Company> getListCompaniesData() {

        return listCompaniesData;

    }

    public void getBuyerCompaniesFromDB() {

        listCompaniesData = accountabilityDao.findCompaniesByUserAndAccountabilityTypeName(userSession.getUserId(), "SellTo");

    }

    public boolean deleteBuyerCompany(Long selectedCompanyId) {

        accountabilityDao.removeByCompany(selectedCompanyId);
        return true;

    }

}
