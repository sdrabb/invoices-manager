package it.unifi.ing.swam.bean;

import it.unifi.ing.swam.dao.AccountabilityTypeDao;
import it.unifi.ing.swam.dao.PartyTypeDao;
import it.unifi.ing.swam.dao.ProductDao;
import it.unifi.ing.swam.model.AccountabilityType;
import it.unifi.ing.swam.model.PartyType;
import it.unifi.ing.swam.model.Product;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

//@Startup
//@Singleton
public class StartupBean {

    @Inject
    private ProductDao productDao;

    @Inject
    private PartyTypeDao partyTypeDao;

    @Inject
    private AccountabilityTypeDao accountabilityTypeDao;

    @PostConstruct
    void init() {

        Product p1 = new Product(UUID.randomUUID().toString());
        Product p2 = new Product(UUID.randomUUID().toString());

        productDao.save(p1);
        productDao.save(p2);

        PartyType userType = new PartyType(UUID.randomUUID().toString(), "User");
        PartyType companyType = new PartyType(UUID.randomUUID().toString(), "Company");

        partyTypeDao.save(userType);
        partyTypeDao.save(companyType);

        AccountabilityType sellTo = new AccountabilityType(UUID.randomUUID().toString());
        AccountabilityType isTheOwnerOf = new AccountabilityType(UUID.randomUUID().toString());

        sellTo.setCommissioner(companyType);
        sellTo.setResponsible(userType);
        accountabilityTypeDao.save(sellTo);

        isTheOwnerOf.setCommissioner(companyType);
        isTheOwnerOf.setResponsible(userType);
        accountabilityTypeDao.save(isTheOwnerOf);

    }
}
