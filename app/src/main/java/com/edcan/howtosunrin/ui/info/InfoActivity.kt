package com.edcan.howtosunrin.ui.info

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityInfoBinding
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import com.edcan.howtosunrin.utill.Url

class InfoActivity : BaseActivity<ActivityInfoBinding>(R.layout.activity_info) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            btnInfoOpenSource.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.openSource.url))
                startActivity(intent)
            }

            btnInfoClub.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.edcanWeb.url))
                startActivity(intent)
            }

            btnInfoDeveloper.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.developerGithub.url))
                startActivity(intent)
            }

            btnInfoDonation.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.developerDonation.url))
                startActivity(intent)
            }

        }
    }
}