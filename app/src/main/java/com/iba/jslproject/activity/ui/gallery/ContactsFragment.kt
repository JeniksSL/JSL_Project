package com.iba.jslproject.activity.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.iba.jslproject.activity.USERS_REF
import com.iba.jslproject.data.User
import com.iba.jslproject.databinding.FragmentContactsBinding
import timber.log.Timber

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactsViewModel =
            ViewModelProvider(this).get(ContactsViewModel::class.java)

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        textView = binding.rwContacts
        contactsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        val database = Firebase.database
        val usersRef = database.getReference(USERS_REF)
        usersRef
            .orderByChild("name")
            .equalTo("John")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userList = snapshot
                            .children
                            .map { it-> it.getValue(User::class.java).let { it!! }}
                            .toList()
                        Timber.d(userList.toString())
                        textView.text=userList.joinToString(separator = ", ")
                    } else {
                        Timber.e("Contacts snapshot doesn't exist")
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Timber.e("Database error")
                }
            })
    }

}