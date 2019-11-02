package com.qa.navigationTests;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory({Cat2.class})
@ExcludeCategory({})
@SuiteClasses({MainMenu.class, UserPageTests.class})
public class NavigationRunner {

	
	
}
