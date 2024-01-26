package com.example.comunidadesautonomas.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.comunidadesautonomas.R
import com.example.comunidadesautonomas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("app", MODE_PRIVATE)
        establecerValoresSiExisten()
        binding.btnLogin.setOnClickListener {
            val email = binding.editNombreLogin.editText?.text.toString()
            val password = binding.editPassLogin.editText?.text.toString()
            if (login(email, password)) goToMain()
            guardarPreferencias(email, password)
        }

     binding.motionLayout.setTransitionListener( object: MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                binding.motionLayout.visibility = View.GONE
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })
    }

    private fun eMailValido(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun passwordValidacion(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length >= 7
    }
    private fun login(email: String, password: String): Boolean {
        var valido = false
        if (!eMailValido(email)){
            Toast.makeText(this,
                "email no válido. Introduzca un email correcto",
                Toast.LENGTH_SHORT).show()
        } else if (!passwordValidacion(password)) {
            Toast.makeText(this,
                "password no válida. Debe tener al menos 8 caracteres",
                Toast.LENGTH_SHORT).show()
        } else {
            valido = true
        }
        return valido
    }

    private fun guardarPreferencias(email: String, password: String) {
        val editor = prefs.edit()
        if (binding.stLogin.isChecked) {
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("recordar", true)
            editor.apply()
        } else {
            editor.clear()
            editor.putBoolean("recordar", false)
            editor.apply()
        }
    }

    private fun establecerValoresSiExisten() {
        val email = prefs.getString("email", "")
        val password = prefs.getString("password", "")
        val recordar = prefs.getBoolean("recordar", false)
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            binding.editNombreLogin.editText?.setText(email)
            binding.editPassLogin.editText?.setText(password)
            binding.stLogin.isChecked = recordar
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}