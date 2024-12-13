package com.example.heartify.view.checking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.heartify.data.UserHomeModel
import com.example.heartify.data.UserHomePreference
import com.example.heartify.loginwithanimation.databinding.ActivityFormUserPreferenceBinding
import com.example.heartify.loginwithanimation.R

class FormUserHomePreferenceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormUserPreferenceBinding

    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_OK = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    private lateinit var userHomeModel: UserHomeModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi tombol simpan
        binding.btnSave.setOnClickListener(this)

        // Ambil data intent
        val receivedIntent = intent
        userHomeModel = receivedIntent.getParcelableExtra("USER") ?: UserHomeModel()
        val formType = receivedIntent.getIntExtra(EXTRA_TYPE_FORM, 0)

        println("Received User Data: $userHomeModel")
        println("Form Type: $formType")

        var actionBarTitle = ""
        var btnTitle = ""

        // Atur tipe form berdasarkan intent
        when (formType) {
            TYPE_ADD -> {
                actionBarTitle = "Tambah Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah"
                btnTitle = "Update"
                showPreferenceInForm() // Tampilkan data pada form
            }
        }

        // Atur title dan tombol
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnSave.text = btnTitle
    }

    // Tampilkan data yang sudah ada di form
    private fun showPreferenceInForm() {
        binding.edtName.setText(userHomeModel.name)
        binding.edtEmail.setText(userHomeModel.email)
        binding.edtAge.setText(userHomeModel.age.toString())
        binding.edtPhone.setText(userHomeModel.phoneNumber.toString())
        if (userHomeModel.isLove) {
            binding.rbYes.isChecked = true
        } else {
            binding.rbNo.isChecked = true
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save) {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val age = binding.edtAge.text.toString().trim()
            val phoneNo = binding.edtPhone.text.toString().trim()
            val isLoveMU = binding.rgLoveMu.checkedRadioButtonId == R.id.rb_yes

            // Validasi input
            if (name.isEmpty()) {
                binding.edtName.error = FIELD_REQUIRED
                return
            }
            if (email.isEmpty()) {
                binding.edtEmail.error = FIELD_REQUIRED
                return
            }
            if (!isValidEmail(email)) {
                binding.edtEmail.error = FIELD_IS_NOT_VALID
                return
            }
            if (age.isEmpty()) {
                binding.edtAge.error = FIELD_REQUIRED
                return
            }
            if (phoneNo.isEmpty()) {
                binding.edtPhone.error = FIELD_REQUIRED
                return
            }
            if (!TextUtils.isDigitsOnly(phoneNo)) {
                binding.edtPhone.error = FIELD_DIGIT_ONLY
                return
            }

            // Simpan data pengguna
            saveUser(name, email, age, phoneNo, isLoveMU)

            // Kirim hasil kembali ke CheckingActivity
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, userHomeModel)
            setResult(RESULT_OK, resultIntent)
            println("Sending Update Result: $userHomeModel")

            finish() // Tutup activity
        }
    }

    private fun saveUser(name: String, email: String, age: String, phoneNo: String, isLoveMU: Boolean) {
        val userHomePreference = UserHomePreference(this)

        userHomeModel.name = name
        userHomeModel.email = email
        userHomeModel.age = Integer.parseInt(age)
        userHomeModel.phoneNumber = Integer.parseInt(phoneNo)
        userHomeModel.isLove = isLoveMU

        userHomePreference.setUser(userHomeModel)
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
