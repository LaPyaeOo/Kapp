package lapyae.learning.kapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var gQuestionList: ArrayList<Question>? = ArrayList()
    private var questionSetCount: Int = 1
    private var selectedOptionNumber: Int = 0
    private var myUserName: String? = null
    private var myCorrectAnswer: Int = 0


    private var tvTitle: TextView? = null
    private var ivFlag: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var pBTextView: TextView? = null
    private var textViewOp1: TextView? = null
    private var textViewOp2: TextView? = null
    private var textViewOp3: TextView? = null
    private var textViewOp4: TextView? = null
    private var btnSubmit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        myUserName = intent.getStringExtra(Constants.USER_NAME)

        tvTitle = findViewById(R.id.tv_title)
        ivFlag = findViewById(R.id.iv_flag)
        progressBar = findViewById(R.id.progressBar)
        pBTextView = findViewById(R.id.tv_pb)
        textViewOp1 = findViewById(R.id.textViewOp1)
        textViewOp2 = findViewById(R.id.textViewOp2)
        textViewOp3 = findViewById(R.id.textViewOp3)
        textViewOp4 = findViewById(R.id.textViewOp4)
        btnSubmit = findViewById(R.id.btn_submit)

        textViewOp1?.setOnClickListener(this)
        textViewOp2?.setOnClickListener(this)
        textViewOp3?.setOnClickListener(this)
        textViewOp4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)


        gQuestionList = Constants.getQuestion()
        setQuestion()

    }

    private fun setQuestion() {
        var question: Question = gQuestionList!![questionSetCount - 1]
        ivFlag?.setImageResource(question.image)
        progressBar?.progress = questionSetCount
        pBTextView?.text = "$questionSetCount/${progressBar?.max}"
        tvTitle?.text = question.question
        textViewOp1?.text = question.optionOne
        textViewOp2?.text = question.optionTwo
        textViewOp3?.text = question.optionThree
        textViewOp4?.text = question.optionFour
        if (questionSetCount != gQuestionList!!.size) {
            btnSubmit!!.text = "SUBMIT"
        } else {
            btnSubmit!!.text = "FINISH"
        }
    }

    private fun defaultItemsView() {
        val tvOptionList = ArrayList<TextView>()

        textViewOp1?.let {
            tvOptionList.add(0, it)
        }
        textViewOp2?.let {
            tvOptionList.add(1, it)
        }
        textViewOp3?.let {
            tvOptionList.add(2, it)
        }
        textViewOp4?.let {
            tvOptionList.add(3, it)
        }

        for (option in tvOptionList) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_background_border
            )
        }
    }

    private fun selectedItemView(tv: TextView, selectedItem: Int) {
        selectedOptionNumber = selectedItem
        defaultItemsView()
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_background_border
        )

    }

    override fun onClick(view: View?) {
        defaultItemsView()
        when (view?.id) {
            R.id.textViewOp1 -> {
                textViewOp1?.let {
                    selectedItemView(it, 1)
                }
            }
            R.id.textViewOp2 -> {
                textViewOp2?.let {
                    selectedItemView(it, 2)
                }
            }
            R.id.textViewOp3 -> {
                textViewOp3?.let {
                    selectedItemView(it, 3)
                }
            }
            R.id.textViewOp4 -> {
                textViewOp4?.let {
                    selectedItemView(it, 4)
                }
            }
            R.id.btn_submit -> {
                if (selectedOptionNumber == 0) {
                    questionSetCount++
                    when {
                        questionSetCount <= gQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, myUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,myCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,gQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val ques = gQuestionList?.get(questionSetCount - 1)
                    if (ques!!.correctAns != selectedOptionNumber) {
                        answerView(selectedOptionNumber, R.drawable.wrong_option_background)
                    }else{
                        myCorrectAnswer++
                    }
                    answerView(ques.correctAns, R.drawable.correct_option_background)
                    if (questionSetCount == gQuestionList!!.size) {
                        btnSubmit!!.text = "FINISH"
                    } else {
                        btnSubmit!!.text = "GO TO NEXT QUESTION"
                    }
                    selectedOptionNumber = 0
                }
            }
        }
    }

    private fun answerView(correctAnswer: Int, drawableView: Int) {

        when (correctAnswer) {
            1 -> {
                textViewOp1?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                textViewOp2?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                textViewOp3?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                textViewOp4?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}