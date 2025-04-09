package com.vinot.somativa1;

import com.vinot.somativa1.application.*;
import com.vinot.somativa1.controller.LibraryTest;
import com.vinot.somativa1.demo.RecommendationDemoTest;
import com.vinot.somativa1.features.RecommendationFeatureTest;
import com.vinot.somativa1.features.UserHistoryFeatureTest;
import com.vinot.somativa1.features.WaitlistFeatureTest;
import com.vinot.somativa1.manager.BookJsonTest;
import com.vinot.somativa1.manager.LibraryFileManagerTest;
import com.vinot.somativa1.manager.UserJsonTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Executa todos os testes principais da aplicação.
 */
@Suite
@SelectClasses({
        MainMenuTest.class,
        UserHistoryFeatureTest.class,
        RecommendationFeatureTest.class,
        BookQueueTest.class,
        RecommendationGraphTest.class,
        UserHistoryTest.class,
        WaitlistTest.class,
        LibraryTest.class,
        RecommendationDemoTest.class,
        WaitlistFeatureTest.class,
        BookJsonTest.class,
        LibraryFileManagerTest.class,
        UserJsonTest.class


})
public class SuiteTest {
}
