package test.assignment.test_kwadran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    lateinit var inputBil1: EditText
    lateinit var inputBil2: EditText
    lateinit var inputBil3: EditText
    lateinit var inputBil4: EditText
    lateinit var inputBil5: EditText
    lateinit var btnSubmit: MaterialButton

    var getInputs: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBil1 = findViewById(R.id.bil_1)
        inputBil2 = findViewById(R.id.bil_2)
        inputBil3 = findViewById(R.id.bil_3)
        inputBil4 = findViewById(R.id.bil_4)
        inputBil5 = findViewById(R.id.bil_5)
        btnSubmit = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            val getBil1 = inputBil1.text.toString()
            val getBil2 = inputBil2.text.toString()
            val getBil3 = inputBil3.text.toString()
            val getBil4 = inputBil4.text.toString()
            val getBil5 = inputBil5.text.toString()

            if (
                validateInput(getBil1) &&
                validateInput(getBil2) &&
                validateInput(getBil3) &&
                validateInput(getBil4) &&
                validateInput(getBil5)
            ) {
                getInputs.add(getBil1.toInt())
                getInputs.add(getBil2.toInt())
                getInputs.add(getBil3.toInt())
                getInputs.add(getBil4.toInt())
                getInputs.add(getBil5.toInt())

                startActivity(
                    Intent(this, ResultActivity::class.java)
                        .putExtra("inputs", getInputs)
                )

            } else {
                Toast.makeText(applicationContext, "Hanya boleh input bilangan", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onResume() {
        super.onResume()

        inputBil1.text.clear()
        inputBil2.text.clear()
        inputBil3.text.clear()
        inputBil4.text.clear()
        inputBil5.text.clear()
        getInputs.clear()

    }

    private fun validateInput(input: String): Boolean {
        val regex =
            Regex("^[0-9]+$") // This regex matches only strings containing one or more digits with no spaces
        return input.matches(regex)
    }

}