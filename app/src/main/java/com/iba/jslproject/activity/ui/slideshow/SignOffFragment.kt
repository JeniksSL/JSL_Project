package com.iba.jslproject.activity.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.iba.jslproject.activity.SplashActivity
import com.iba.jslproject.databinding.FragmentSignOffBinding

class SignOffFragment : Fragment() {

    private var _binding: FragmentSignOffBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val signOffViewModel =
            ViewModelProvider(this)[SignOffViewModel::class.java]

        _binding = FragmentSignOffBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvSignOff
        val btnSignOff: Button = binding.btnSignOff
        signOffViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        signOffViewModel.btn_text.observe(viewLifecycleOwner) {
            btnSignOff.text = it
        }
        btnSignOff.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, SplashActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}