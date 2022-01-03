package com.edcan.howtosunrin.screen.userData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityUserDataBinding
import com.edcan.howtosunrin.model.user.UserUtill
import com.edcan.howtosunrin.screen.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserDataBinding
    lateinit var viewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_data)
        viewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnUserDataInputEnd.setOnClickListener {
            if(viewModel.name.value!!.isEmpty()){
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val result = viewModel.saveUserData()

                withContext(Dispatchers.Main){
                    if(result == UserUtill.ResultSuccess){
                        Toast.makeText(this@UserDataActivity, "유저 등록에 성했습니다.", Toast.LENGTH_LONG).show()
                        
                        val intent = Intent(this@UserDataActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }   else{
                        Toast.makeText(this@UserDataActivity, "유저 등록에 실패했습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}