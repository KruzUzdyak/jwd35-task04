package com.epam.volodko.entity.users;

import java.util.HashMap;
import java.util.Map;

public class DriverLicenseProvider {

    private static DriverLicenseProvider instance;

    private final Map<Integer, DriverLicenseType> licenseTypes;

    private DriverLicenseProvider(){
        licenseTypes = new HashMap<>();
        licenseTypes.put(DriverLicenseType.AM.getId(), DriverLicenseType.AM);
        licenseTypes.put(DriverLicenseType.A.getId(), DriverLicenseType.A);
        licenseTypes.put(DriverLicenseType.A1.getId(), DriverLicenseType.A1);
        licenseTypes.put(DriverLicenseType.B.getId(), DriverLicenseType.B);
        licenseTypes.put(DriverLicenseType.C.getId(), DriverLicenseType.C);
        licenseTypes.put(DriverLicenseType.D.getId(), DriverLicenseType.D);
        licenseTypes.put(DriverLicenseType.BE.getId(), DriverLicenseType.BE);
        licenseTypes.put(DriverLicenseType.CE.getId(), DriverLicenseType.CE);
        licenseTypes.put(DriverLicenseType.DE.getId(), DriverLicenseType.DE);
        licenseTypes.put(DriverLicenseType.F.getId(), DriverLicenseType.F);
        licenseTypes.put(DriverLicenseType.I.getId(), DriverLicenseType.I);
    }

    public static DriverLicenseType getLicenseType(int licenseTypeId){
        if (instance == null){
            instance = new DriverLicenseProvider();
        }
        return instance.licenseTypes.get(licenseTypeId);
    }


}
