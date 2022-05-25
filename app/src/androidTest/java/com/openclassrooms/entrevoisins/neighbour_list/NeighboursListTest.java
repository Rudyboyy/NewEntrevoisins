
package com.openclassrooms.entrevoisins.neighbour_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.MatcherViewWithIndex.withIndex;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withIndex(withId(R.id.list_neighbours), 0))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withIndex(withId(R.id.list_neighbours), 0)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withIndex(withId(R.id.list_neighbours), 0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withIndex(withId(R.id.list_neighbours), 0)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on an item it navigates to the detail view
     */
    @Test
    public void myNeighboursList_neighbourAction_shouldShowNeighbourDetail() {
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.neighbour_detail)).check(matches(isDisplayed()));
    }

    /**
     * We ensure that the name corresponding to the item is displayed
     */
    @Test
    public void myNeighbourDetail_textViewShouldDisplayTheNeighbourName() {
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText("Jack")));
    }

    /**
     * When we delete an item, the item is no more shown in both list
     */
    @Test
    public void myFavoriteNeighboursList_deleteAction_shouldRemoveItemInBothList() {
        onView(withIndex(withId(R.id.list_neighbours), 0)).check(withItemCount(ITEMS_COUNT));
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click());
        pressBack();

        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(ViewActions.swipeLeft());

        onView(withIndex(withId(R.id.list_neighbours), 1)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withIndex(withId(R.id.list_neighbours), 0)).check(withItemCount(ITEMS_COUNT - 1));
        onView(withIndex(withId(R.id.list_neighbours), 1)).check(withItemCount(0));
    }

    /**
     * We ensure that the number of items on the list is correct when we add an items on it
     */
    @Test
    public void myFavoriteNeighboursList_checkNumberOfNeighboursIsRight() {
        onView(withIndex(withId(R.id.list_neighbours), 1)).check(withItemCount(0));
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click());
        pressBack();
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click());
        pressBack();
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click());
        pressBack();
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(4, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click());
        pressBack();
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click());
        pressBack();
        onView(withIndex(withId(R.id.list_neighbours), 1)).check(withItemCount(5));
    }

    /**
     * We ensure than the favorite list got no item at the start
     */
    @Test
    public void myFavoriteNeighboursList_shouldBeEmpty() {
        onView(withIndex(withId(R.id.list_neighbours), 1)).check(withItemCount(0));
    }

    /**
     * When we remove an item from favorites, the item is no longer shown in the favorites list
     */
    @Test
    public void myNeighbourDetail_favoriteActionTwice_shouldRemoveItemFromFavorite() {
        onView(withIndex(withId(R.id.list_neighbours), 0)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.doubleClick());
        pressBack();
        onView(withIndex(withId(R.id.list_neighbours), 1)).check(withItemCount(0));
    }
}
