package ca.qc.cstj.s01premiereappplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast

const val MIN_NUMBER = 0
const val MAX_NUMBER = 100

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //Récupérer les contrôles de l'interface
        val txvWelcome = findViewById<TextView>(R.id.txvWelcome)
        val nprNumber = findViewById<NumberPicker>(R.id.nprNumber)
        val btnTestNumber = findViewById<Button>(R.id.btnTestNumber)

        txvWelcome.text = getString(R.string.deviner_le_nombre, intent.getStringExtra(INTENT_EXTRA_NAME))

        nprNumber.minValue = MIN_NUMBER
        nprNumber.maxValue = MAX_NUMBER

        val theNumber = (MIN_NUMBER .. MAX_NUMBER).random()

        btnTestNumber.setOnClickListener {
            when {
                nprNumber.value == theNumber -> {
                    Toast.makeText(this, R.string.msgWinner, Toast.LENGTH_LONG).show()
                }
                nprNumber.value > theNumber -> {
                    Toast.makeText(this, R.string.msgLess, Toast.LENGTH_LONG).show()
                    //nprNumber.minValue = nprNumber.value + 1
                }
                else -> {
                    // Plus petit
                    Toast.makeText(this, R.string.msgHigh, Toast.LENGTH_LONG).show()
                    //nprNumber.maxValue = nprNumber.value - 1
                }
            }
        }


    }

    //Companion statique de ma classe (la partie statique)
    companion object {
        const val INTENT_EXTRA_NAME = "EXTRA_NAME"
        fun newIntent(context: Context, name:String) : Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(INTENT_EXTRA_NAME, name)
            return intent
        }
    }
}