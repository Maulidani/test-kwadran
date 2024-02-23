package test.assignment.test_kwadran

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import test.assignment.test_kwadran.database.AppDatabase
import test.assignment.test_kwadran.database.TableData

class ResultActivity : AppCompatActivity() {

    lateinit var result1: TextView
    lateinit var result2: TextView
    lateinit var resultMax: String
    lateinit var resultMaxMin: String
    lateinit var btnSave: MaterialButton
    lateinit var dbRoom: AppDatabase
    lateinit var adapterDatabase: AdapterDatabase
    lateinit var rvDatabase: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val getInputs = intent.getIntegerArrayListExtra("inputs")
        Log.d("ResultActivity", "getInputs: " + getInputs)

        dbRoom = AppDatabase.getInstance(applicationContext)

        result1 = findViewById(R.id.result_1)
        result2 = findViewById(R.id.result_2)
        btnSave = findViewById(R.id.btn_save)
        rvDatabase = findViewById(R.id.rv_result_table)

        resultMax = getInputs?.let { getMax(it) }.toString()
        resultMaxMin = getInputs?.let { getMaxMin(it) }.toString()

        result1.text = "Nilai Max : $resultMax"
        result2.text = "Urutan Nilai Max Ke Min : $resultMaxMin"

        btnSave.setOnClickListener {
            saveDataToDatabase()
        }

        loadDataFromDatabase()
    }

    private fun getMax(inputs: ArrayList<Int>): String {
        var max = inputs[0]
        for (i in 1 until inputs.size) {
            if (inputs[i] > max) {
                max = inputs[i]
            }
        }
        return max.toString()
    }

    private fun getMaxMin(inputs: ArrayList<Int>): ArrayList<Int> {
        val sortedList = ArrayList<Int>(inputs.size)

        for (element in inputs) {
            sortedList.add(element)
        }

        for (i in 0 until sortedList.size - 1) {
            for (j in 0 until sortedList.size - i - 1) {
                if (sortedList[j] < sortedList[j + 1]) {
                    // Swap elements if they are in the wrong order
                    val temp = sortedList[j]
                    sortedList[j] = sortedList[j + 1]
                    sortedList[j + 1] = temp
                }
            }
        }

        return sortedList
    }

    private fun saveDataToDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            dbRoom.dataDao().insertData(
                TableData(
                    max = resultMax,
                    urutan = resultMaxMin
                )
            )

            loadDataFromDatabase()
        }
    }

    private fun loadDataFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataStored = dbRoom.dataDao().getAllData()

            Log.d("ResultActivity", "loadDataFromDatabase: $dataStored")
            if (dataStored.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    adapterDatabase = AdapterDatabase(dataStored)
                    rvDatabase.layoutManager = LinearLayoutManager(applicationContext)
                    rvDatabase.adapter = adapterDatabase
                }
            }
        }

    }

}