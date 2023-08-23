package com.multBancapp.apimultbanc.services.utils;

import com.multBancapp.apimultbanc.entities.UserEntity;

public class ValidateCPF {

      public boolean validade(UserEntity user) {

            if (user.getDocument() == null || user.getDocument().length() != 11) {
                  return false;
            }

            if (user.getDocument().matches("(\\d)\\1{10}")) {
                  return false;
            }

            int sum = 0;
            for (int i = 0; i < 9; i++) {
                  sum += (user.getDocument().charAt(i) - '0') * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);

            if (firstDigit > 9) {
                  firstDigit = 0;
            }

            if (firstDigit != user.getDocument().charAt(9) - '0') {
                  return false;
            }
            sum = 0;
            for (int i = 0; i < 10; i++) {
                  sum += (user.getDocument().charAt(i) - '0') * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit > 9) {
                  secondDigit = 0;
            }
            return secondDigit == user.getDocument().charAt(10) - '0';
      }

}
