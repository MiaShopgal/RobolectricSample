package evan.chen.tutorial.tdd.robolectricsample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send.setOnClickListener {

            val loginId = loginId.text.toString()
            val pwd = password.text.toString()

            //檢核帳號是否正確
            val isLoginIdOK = RegisterVerify().isLoginIdVerify(loginId)

            //檢核密碼是否正確
            val isPwdOK = RegisterVerify().isPasswordVerify(pwd)

            if (!isLoginIdOK) {
                // 註冊失敗，資料填寫錯誤
                val builder = AlertDialog.Builder(this)
                builder.setMessage("帳號至少要6碼，第1碼為英文").setTitle("錯誤")
                builder.show()

            } else if (!isPwdOK) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("密碼至少要8碼，第1碼為英文，並包含1碼數字").setTitle("錯誤")
                builder.show()
            } else {
                //註冊成功，儲存Id
                Repository(this).saveUserId(loginId)

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ID", loginId)

                startActivity(intent)
            }
        }
    }
}
