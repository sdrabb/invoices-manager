package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.HttpParam;
import it.unifi.ing.swam.dao.CompanyDao;
import it.unifi.ing.swam.model.Company;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ViewCompanyPageController extends UserPermissionController {

    @Inject
    @HttpParam("companyId")
    private String companyId;

    @Inject
    private CompanyDao companyDao;

    private Company companyData;

    public void retrieveCompanyDataFromDB() {

        companyData = companyDao.findById(Long.valueOf(companyId));

    }

    public Company getCompanyData() {
        return companyData;
    }

    public String getCompanyId() {
        return companyId;
    }

}
