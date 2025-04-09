package com.vinot.somativa1;

import com.vinot.somativa1.application.MainMenuTest;
import com.vinot.somativa1.features.UserHistoryFeatureTest;
import com.vinot.somativa1.features.RecommendationFeatureTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Executa todos os testes principais da aplicação.
 */
@Suite
@SelectClasses({
        MainMenuTest.class,
        UserHistoryFeatureTest.class,
        RecommendationFeatureTest.class
})
public class SuiteTest {
}
