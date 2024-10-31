package be.bnppf.development.books;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

@AnalyzeClasses(packages = "be.bnppf.development.books")
public class ArchitectureTest {

    @Test
    void domainClassesShouldResideInDomainPackage() {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..domain..")
                .should().onlyBeAccessed().byAnyPackage("..domain..", "..application..");
    }

    @Test
    void serviceClassesShouldResideInDomainServicePackage() {
        ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().resideInAPackage("..domain.service..");
    }

    @Test
    void controllerClassesShouldResideInApplicationControllerPackage() {
        ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAPackage("..application.controller..");
    }

    @Test
    void dtoClassesShouldResideInApplicationDtoPackage() {
        ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("Dto")
                .or().haveSimpleNameEndingWith("Request")
                .or().haveSimpleNameEndingWith("Response")
                .should().resideInAPackage("..application.dto..");
    }

    @Test
    void domainClassesShouldNotDependOnApplicationClasses() {
        ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage("..application..");
    }

    @Test
    void applicationClassesShouldOnlyDependOnDomainAndApplicationPackages() {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..application..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("java..", "..domain..", "..application..");
    }
}
