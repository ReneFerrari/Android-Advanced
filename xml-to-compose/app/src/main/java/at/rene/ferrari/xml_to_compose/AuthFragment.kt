package at.rene.ferrari.xml_to_compose

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthFragment : Fragment(R.layout.fragment_auth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etUsername = view.findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = view.findViewById<TextInputEditText>(R.id.etPassword)
        val tilUsername = view.findViewById<TextInputLayout>(R.id.tilUsername)
        val tilPassword = view.findViewById<TextInputLayout>(R.id.tilPassword)
        val tvError = view.findViewById<TextView>(R.id.tvError)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val pbLoading = view.findViewById<ProgressBar>(R.id.pbLoading)

        fun setLoading(loading: Boolean) {
            pbLoading.visibility = if (loading) View.VISIBLE else View.GONE
            etUsername.isEnabled = !loading
            etPassword.isEnabled = !loading
            btnLogin.isEnabled = !loading
            btnRegister.isEnabled = !loading
        }

        fun showError(msg: String?) {
            tvError.text = msg ?: ""
            tvError.visibility = if (msg == null) View.GONE else View.VISIBLE
        }

        btnLogin.setOnClickListener {
            showError(null)
            setLoading(true)

            view.postDelayed({
                setLoading(false)
                val u = etUsername.text?.toString().orEmpty()
                val p = etPassword.text?.toString().orEmpty()
                if (u == "admin" && p == "admin") {
                    Toast.makeText(requireContext(), "Login success 🎉", Toast.LENGTH_SHORT).show()
                } else {
                    showError("Invalid credentials")
                    tilUsername.error = if (u.isBlank()) "Required" else null
                    tilPassword.error = if (p.isBlank()) "Required" else null
                }
            }, 600)
        }

        btnRegister.setOnClickListener {
            Toast.makeText(requireContext(), "Navigate to Register", Toast.LENGTH_SHORT).show()
        }
    }
}