package com.clinic.PatientsAppointmentsSystem.untils;

public class StringUtils {
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String[] words = input.split(" ");
        StringBuilder camelCaseString = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                camelCaseString.append(word.toLowerCase());
            } else {
                camelCaseString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase());
            }
        }

        return camelCaseString.toString();
    }
}
