package utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName() {
        Faker faker = new Faker();
        return faker.artist().name();
    }

    public static String generateDescription() {
        Faker faker = new Faker();
        faker.bool();
        return faker.hitchhikersGuideToTheGalaxy().quote();
    }
}
