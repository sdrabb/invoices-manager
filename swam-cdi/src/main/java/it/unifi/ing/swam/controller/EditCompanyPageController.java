package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.HttpParam;
import it.unifi.ing.swam.dao.CompanyDao;
import it.unifi.ing.swam.model.Company;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ViewScoped
public class EditCompanyPageController extends UserPermissionController {

    @Inject
    @HttpParam("companyId")
    private String companyId;

    @Inject
    private CompanyDao companyDao;

    private Company companyData;

    public void retrieveCompanyDataFromDB() {

        companyData = companyDao.findById(Long.valueOf(companyId));

    }

    @Transactional
    public boolean saveCompanyData() {

        companyDao.save(companyData);

        return true;

    }

    public Company getCompanyData() {
        return companyData;
    }

    public void deselectCompany() {
        companyId = null;
    }

    public String getCompanyId() {
        return companyId;
    }

}
