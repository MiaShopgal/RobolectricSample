package evan.chen.tutorial.tdd.robolectricsample

import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    fun setupActivity() {

        MockitoAnnotations.openMocks(this)

        activity = Robolectric.buildActivity(MainActivity::class.java).setup().get()

    }

    @Test
    fun registerSuccessShouldDirectToResult() {
        //arrange
        val shadowActivity = Shadows.shadowOf(activity)

        val userId = "A123456789"
        val userPassword = "a123456789"

        activity.loginId.setText(userId)
        activity.password.setText(userPassword)

        //點下註冊按鈕
        activity.send.performClick()

        //驗證註冊成功時，是否有開啟ResultActivity
        val nextIntent = shadowActivity.nextStartedActivity
        assertEquals(nextIntent.component!!.className, ResultActivity::class.java.name)
        assertEquals(1, nextIntent.extras!!.size())
        assertEquals(userId, nextIntent.extras!!.getString("ID"))
    }

    @Test
    fun registerFailShouldAlert() {
        //arrange

        val userId = "A1234"
        val userPassword = "a123456789"

        activity.loginId.setText(userId)
        activity.password.setText(userPassword)

        //點下註冊按鈕
        activity.send.performClick()

        val dialog = ShadowAlertDialog.getLatestDialog()

        //Assert
        assertNotNull(dialog)
        assertTrue(dialog.isShowing)
    }
}