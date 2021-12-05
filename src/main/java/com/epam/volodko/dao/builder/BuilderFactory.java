package com.epam.volodko.dao.builder;

import com.epam.volodko.dao.builder.impl.*;

public class BuilderFactory {

    private static final UserBuilder userBuilder = new UserBuilder();
    private static final AdminBuilder adminBuilder = new AdminBuilder();
    private static final ClientBuilder clientBuilder = new ClientBuilder();
    private static final DriverBuilder driverBuilder = new DriverBuilder();
    private static final RoleBuilder roleBuilder = new RoleBuilder();
    private static final LicenseTypeBuilder licenseTypeBuilder = new LicenseTypeBuilder();
    private static final DriverLicenseBuilder driverLicenseBuilder = new DriverLicenseBuilder();


    public static UserBuilder getUserBuilder() {
        return userBuilder;
    }

    public static RoleBuilder getRoleBuilder() {
        return roleBuilder;
    }

    public static AdminBuilder getAdminBuilder() {
        return adminBuilder;
    }

    public static ClientBuilder getClientBuilder() {
        return clientBuilder;
    }

    public static DriverBuilder getDriverBuilder() {
        return driverBuilder;
    }

    public static LicenseTypeBuilder getLicenseTypeBuilder() {
        return licenseTypeBuilder;
    }

    public static DriverLicenseBuilder getDriverLicenseBuilder() {
        return driverLicenseBuilder;
    }
}
