package com.ievgensafronenko.currencyconverter.architectural;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DontIncludeTests;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureTest {

    private final ClassFileImporter classFileImporter = new ClassFileImporter();
    private JavaClasses classes;

    @Before
    public void init() {
        classes = classFileImporter
                .withImportOption(new DontIncludeTests())
                .importPackages("com.ievgensafronenko.currencyconverter");
    }

    @Test
    public void testLayeredArchitecture() {
        ArchRule rule = layeredArchitecture()
                .layer("config").definedBy("com.ievgensafronenko.currencyconverter..config..")
                .layer("model").definedBy("com.ievgensafronenko.currencyconverter..model..")
                .layer("repository").definedBy("com.ievgensafronenko.currencyconverter..repository..")
                .layer("resource").definedBy("com.ievgensafronenko.currencyconverter..resource..")
                .layer("service").definedBy("com.ievgensafronenko.currencyconverter..service..")

                .whereLayer("config").mayNotBeAccessedByAnyLayer();
        rule.check(classes);
    }

    @Test
    public void servicesShouldResideInServiceAndAnnotatedWithService() {
        classes().that().haveNameMatching(".*ServiceImpl")
                .should().beAnnotatedWith(Service.class)
                .andShould().resideInAPackage("..service..")
                .check(classes);
    }

    @Test
    public void entitiesShouldResideInDataLayer() {
        classes().that().haveNameMatching(".*DTO")
                .should().resideInAPackage("..model..")
                .check(classes);
    }
}
