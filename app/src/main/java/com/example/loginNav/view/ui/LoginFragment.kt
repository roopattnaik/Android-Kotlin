package com.example.loginNav.view.ui

import android.annotation.SuppressLint

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.loginNav.R
import com.example.loginNav.viewmodel.LoginViewModel

import com.example.loginNav.databinding.LoginfragmentBinding
import kotlinx.android.synthetic.main.loginfragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Login Fragmnet
 * Uses data Binding and Observe the data
 */
class LoginFragment: Fragment() {

    var counter :Int = 5
    private lateinit var loginviewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val loginbinder :LoginfragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.loginfragment,container,false)
        loginviewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val textView: TextView =loginbinder.root.findViewById(R.id.textloginview)
        loginviewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it?: return@Observer
        })
        loginbinder.apply {
            lifecycleOwner = this@LoginFragment
            loginviewModel = this@LoginFragment.loginviewModel
        }

        return loginbinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        loginviewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                if (it) {
                    findNavController().navigate(R.id.action_loginFragment_to_gameListFragment)
                    Toast.makeText(
                        activity,
                        "Redirecting...", Toast.LENGTH_SHORT
                    ).show()
                }
                else if (emailaddress.getText().isEmpty() || password.getText().isEmpty()) {
                    emailaddress.error = "Cannot be empty"
                    password.setError("Cannot be empty")
                }
                else
                {
                    Toast.makeText(activity, "Invalid Credentials. Please try again!", Toast.LENGTH_LONG)
                        .show()
                    countervalue.setVisibility(View.VISIBLE)
                    countervalue.setBackgroundColor(Color.RED)
                    counter--
                    countervalue.setText(Integer.toString(counter))
                    if (counter == 0) {
                        loginButton.isEnabled = false
                    }
                }
            }
        })
    }

    /**
     * Initialises the Listeners
     */
    private fun initListeners() {
        loginButton.setOnClickListener {
            loginviewModel.performLogin(
                emailAddress = emailaddress.text.toString(),
                password = password.text.toString()
            )
        }
    }

}

