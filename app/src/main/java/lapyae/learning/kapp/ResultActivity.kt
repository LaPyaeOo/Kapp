package lapyae.learning.kapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val userName : TextView = findViewById(R.id.user_name)
        val tvTotalQuestions : TextView = findViewById(R.id.total_questions)
        val tvScore : TextView = findViewById(R.id.correct_answers)
        val btnFinish : Button = findViewById(R.id.btn_finish)


        userName.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        tvScore.text = correctAnswers.toString()
        tvTotalQuestions.text = totalQuestions.toString()

        btnFinish.setOnClickListener {
            startActivity(Intent(this,MainActivity ::class.java))

        }
    }
}