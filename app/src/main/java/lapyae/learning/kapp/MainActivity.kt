package lapyae.learning.kapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val stButton : Button = findViewById(R.id.stBtn)
        val edtText : EditText = findViewById(R.id.edtText)

        stButton.setOnClickListener {
            if(edtText.text.isEmpty()){
                Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, QuizQuestionActivity :: class.java)
                intent.putExtra(Constants.USER_NAME,edtText.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}