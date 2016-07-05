package jetsetpaul.e_commerce_app;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest{
    @Rule
    public ActivityTestRule<MainActivity>
            applicationTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

        @Test
        public void storeFrontDisplays(){
            onView(withId(R.id.my_recycler_view)).check(matches(isDisplayed()));
        }

        @Test
        public void cartDisplays(){
            onView(withId(R.id.cart)).perform(click());
            onView(withId(R.id.buyButton)).check(matches(isDisplayed()));
        }

        @Test
        public void showsItemsByMyBand(){
            onView(withId(R.id.my_recycler_view))
                    .check(matches(hasDescendant(withText("First Thought Worst Thought"))));
        }

}
