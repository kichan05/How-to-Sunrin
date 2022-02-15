package com.edcan.howtosunrin.ui.userData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityUserDataBinding
import com.edcan.howtosunrin.utill.user.UserUtil
import com.edcan.howtosunrin.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataActivity : BaseActivity<ActivityUserDataBinding>(R.layout.activity_user_data) {
    lateinit var viewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)
        binding.viewModel = viewModel

        with(binding){
            btnUserDataInputEnd.setOnClickListener(createUserData)
        }
    }

    private val createUserData : (View) -> Unit = createUserData@{ _ : View ->
        if(viewModel.name.value!!.isEmpty()){
            binding.edtUserDataInputName.error = "이름을 입력해주세요."
            return@createUserData
        }

        CoroutineScope(Dispatchers.IO).launch {
            val result = viewModel.saveUserData()

            withContext(Dispatchers.Main){
                if(result != UserUtil.ResultSuccess){
                    Toast.makeText(this@UserDataActivity, "유저 등록에 실패했습니다.", Toast.LENGTH_LONG).show()
                    return@withContext
                }

                Toast.makeText(this@UserDataActivity, "유저 등록에 성공했습니다.", Toast.LENGTH_LONG).show()

                val intent = Intent(this@UserDataActivity, MainActivity::class.java)
                intent.putExtra("userData", viewModel.userData)
                startActivity(intent)
                finish()
            }
        }
    }
}