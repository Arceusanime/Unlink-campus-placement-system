package com.unlink.validation;

import com.unlink.model.Company;

public class CompanyValidator {

    private CompanyValidator() {
    }

    public static void validate(Company company) {

        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null.");
        }

        if (company.getName() == null ||
                company.getName().isBlank()) {
            throw new IllegalArgumentException("Company name is required.");
        }

        if (company.getHrEmail() != null &&
                !company.getHrEmail().isBlank()) {

            String emailRegex =
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            if (!company.getHrEmail().matches(emailRegex)) {
                throw new IllegalArgumentException("Invalid HR email.");
            }
        }

        if (company.getWebsite() != null &&
                !company.getWebsite().isBlank()) {

            if (!(company.getWebsite().startsWith("http://")
                    || company.getWebsite().startsWith("https://"))) {

                throw new IllegalArgumentException(
                        "Website must start with http:// or https://"
                );
            }
        }
    }
}