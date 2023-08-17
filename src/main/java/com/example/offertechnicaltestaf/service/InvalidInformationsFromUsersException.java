    package com.example.offertechnicaltestaf.service;

    public class InvalidInformationsFromUsersException extends RuntimeException {
        private final ValidateUserParams validateUserParams;

        /**
         *
         * @param validateUserParams A hashmap that contains every error detected in the attributes of a user during its creation in the database
         */
        public InvalidInformationsFromUsersException(ValidateUserParams validateUserParams) {
            this.validateUserParams = validateUserParams;
        }
        public ValidateUserParams getValidateUserParams() {
            return validateUserParams;
        }
    }
