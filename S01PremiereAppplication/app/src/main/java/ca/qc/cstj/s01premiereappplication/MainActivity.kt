package ca.qc.cstj.s01premiereappplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMessage = findViewById<Button>(R.id.btnMessage)

        btnMessage.setOnClickListener {
            Toast.makeText(this, "Bonjour",Toast.LENGTH_LONG).show()
        }
    }


}