package ca.qc.cstj.s01premiereappplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // findViewById équivalent document.getElementById()
        val btnMessage = findViewById<Button>(R.id.btnMessage)
        val btnTelephone = findViewById<Button>(R.id.btnTelephone)
        val btnSMS = findViewById<Button>(R.id.btnSMS)
        val btnJeu = findViewById<Button>(R.id.btnJeu)
        val edtName = findViewById<EditText>(R.id.edtName)

        btnJeu.setOnClickListener {
            val secondActivityIntent = SecondActivity.newIntent(this, edtName.text.toString())
            startActivity(secondActivityIntent)
        }

        btnSMS.setOnClickListener {
            val name = edtName.text.toString()
            val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:450-436-1580"))
            smsIntent.putExtra("sms_body","Bonjour de $name")
            startActivity(smsIntent)
        }


        btnMessage.setOnClickListener {
            //Toast.makeText(this, "Bonjour",Toast.LENGTH_LONG).show()

            //val packageName = "com.facebook.orca"
            val packageName = "com.google.android.youtube"
            val packageIntent = packageManager.getLaunchIntentForPackage(packageName)
            if(packageIntent != null) {
                packageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(packageIntent)
            } else {
                val intentMarket = Intent(Intent.ACTION_VIEW)
                intentMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intentMarket.data = Uri.parse("market://details?id=$packageName")
                startActivity(intentMarket)
            }
        }

        btnTelephone.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:450-436-1580"))
            startActivity(phoneIntent)
        }
    }


}