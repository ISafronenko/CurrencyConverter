package com.ievgensafronenko.currencyconverter.architectural;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DontIncludeTests;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

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
                .layer("entities").definedBy("com.ievgensafronenko.currencyconverter..entities..")
                .layer("dto").definedBy("com.ievgensafronenko.currencyconverter..dto..")
                .layer("repository").definedBy("com.ievgensafronenko.currencyconverter..repository..")
                .layer("resource").definedBy("com.ievgensafronenko.currencyconverter..controller..")
                .layer("service").definedBy("com.ievgensafronenko.currencyconverter..service..")

                .whereLayer("config").mayNotBeAccessedByAnyLayer()
                .whereLayer("repository").mayOnlyBeAccessedByLayers("service")
                .whereLayer("service").mayOnlyBeAccessedByLayers("resource", "config")
                .whereLayer("entities").mayOnlyBeAccessedByLayers( "service")
                .whereLayer("dto").mayOnlyBeAccessedByLayers("resource", "service", "config")
                .whereLayer("resource").mayNotBeAccessedByAnyLayer();
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
    public void dtoShouldResideInDtoLayer() {
        classes().that().haveNameMatching(".*DTO")
                .should().resideInAPackage("..dto..")
                .check(classes);
    }

    @Test
    public void controllersShouldResideInControllerLayer() {
        classes().that().haveNameMatching(".*Controller")
                .should().beAnnotatedWith(Controller.class)
                .andShould().resideInAPackage("..controller..")
                .check(classes);
    }

    @Test
    public void repositoriesShouldResideInRepositoryLayer() {
        classes().that().haveNameMatching(".*Repository")
                .should().beAnnotatedWith(Repository.class)
                .andShould().resideInAPackage("..repository..")
                .check(classes);
    }
}
