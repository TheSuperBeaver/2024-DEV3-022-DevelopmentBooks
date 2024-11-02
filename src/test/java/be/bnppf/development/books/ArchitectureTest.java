package be.bnppf.development.books;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "be.bnppf.development.books", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {

    @ArchTest
    static final ArchRule architectureRule = layeredArchitecture().
            consideringOnlyDependenciesInLayers().
            layer("Controller").definedBy("..application.controller..").
            layer("Controller-Dto").definedBy("..application.dto..").
            layer("Domain").definedBy("..domain..").
            layer("Model").definedBy("..domain.model..").
            layer("Service").definedBy("..domain.service..").
            whereLayer("Controller").mayNotBeAccessedByAnyLayer().
            whereLayer("Controller-Dto").mayOnlyBeAccessedByLayers("Controller", "Service").
            whereLayer("Domain").mayOnlyBeAccessedByLayers("Controller").
            whereLayer("Service").mayOnlyBeAccessedByLayers("Controller").
            whereLayer("Service").mayOnlyAccessLayers("Model", "Controller-Dto");
}

