package org.example.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberExtractor {
    public static void main(String[] args) {
        String str = "Container exited with a non-zero exit code -103.";

        Pattern pattern = Pattern.compile("non-zero exit code (-)?\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String needle = matcher.group();
            System.out.println("Needle: " + needle);
            String[] split = needle.split(" ");
            String exitCode = split[split.length - 1];

            System.out.println("exitCode => " + exitCode);
        }
    }
}
