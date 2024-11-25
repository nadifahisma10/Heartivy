package com.example.heartify.view.main

import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.example.heartify.data.UserHomeModel
import com.example.heartify.data.UserHomePreference
import com.example.heartify.loginwithanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserHomePreference: UserHomePreference
    private lateinit var binding: ActivityMainBinding

    private var isPreferenceEmpty = false
    private lateinit var userHomeModel: UserHomeModel

    private val resultLauncher = registerForActivityResult(
        StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.data != null && result.resultCode == FormUserHomePreferenceActivity.RESULT_CODE) {
            userHomeModel = result.data?.getParcelableExtra<UserHomeModel>(FormUserHomePreferenceActivity.EXTRA_RESULT) as UserHomeModel
            populateView(userHomeModel)
            checkForm(userHomeModel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My User Preference"

        mUserHomePreference = UserHomePreference(this)

        showExistingPreference()

        binding.btnSave.setOnClickListener(this)

    }

    private fun showExistingPreference() {
        userHomeModel = mUserHomePreference.getUser()
        populateView(userHomeModel)
        checkForm(userHomeModel)
    }

    private fun populateView(userHomeModel: UserHomeModel) {
        binding.tvName.text =
            if (userHomeModel.name.toString().isEmpty()) "Tidak Ada" else userHomeModel.name
        binding.tvEmail.text =
            if (userHomeModel.email.toString().isEmpty()) "Tidak Ada" else userHomeModel.email
        binding.tvAge.text =
            if (userHomeModel.age.toString().isEmpty()) "Tidak Ada" else userHomeModel.age.toString()
        binding.tvJk.text = if (userHomeModel.jk) "Ya" else "Tidak"
        binding.tvDesk.text =
            if (userHomeModel.desk.toString().isEmpty()) "Tidak Ada" else userHomeModel.desk
        binding.tvPhone.text =
            if (userHomeModel.phoneNumber.toString().isEmpty()) "Tidak Ada" else userHomeModel.phoneNumber.toString()
        binding.tvCollestrol.text =
            if (userHomeModel.collestrol.toString().isEmpty()) "Tidak Ada" else userHomeModel.collestrol.toString()
        binding.tvIsLoveMu.text = if (userHomeModel.isLove) "Ya" else "Tidak"
    }

    private fun checkForm(userHomeModel: UserHomeModel) {
        when {
            userHomeModel.name.toString().isNotEmpty() -> {
                binding.btnSave.text = getString(R.string.change)
                isPreferenceEmpty = false
            }
            else -> {
                binding.btnSave.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save) {
            val intent = Intent(this@MainActivity, FormUserHomePreferenceActivity::class.java)
            when {
                isPreferenceEmpty -> {
                    intent.putExtra(
                        FormUserHomePreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserHomePreferenceActivity.TYPE_ADD
                    )
                    intent.putExtra("USER", userHomeModel)
                }
                else -> {
                    intent.putExtra(
                        FormUserHomePreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserHomePreferenceActivity.TYPE_EDIT
                    )
                    intent.putExtra("USER", userHomeModel)
                }
            }
            resultLauncher.launch(intent)
        }
    }
}