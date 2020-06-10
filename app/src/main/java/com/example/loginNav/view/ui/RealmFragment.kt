package com.example.loginNav.view.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.loginNav.R
import com.example.loginNav.RealmApp
import com.example.loginNav.databinding.AddnewitemFragmentBinding
import com.example.loginNav.model.RealmUsers
import com.example.loginNav.viewmodel.RealmViewModel
import io.realm.Realm
import kotlinx.android.synthetic.main.addnewitem_fragment.*

class RealmFragment : Fragment() {

    private lateinit var realm: Realm
    private lateinit var RealmVM: RealmViewModel
    private lateinit var UserList: List<RealmUsers>

    private var views: Views? = null

    private class Views(val binding: AddnewitemFragmentBinding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment


        val binder: AddnewitemFragmentBinding =
            AddnewitemFragmentBinding.inflate(inflater, container, false)
        var myApp: RealmApp =
            RealmApp()

        RealmVM = ViewModelProvider(this).get(RealmViewModel::class.java)
        binder.vm = RealmVM
        binder.apply {
            lifecycleOwner = this@RealmFragment

        }
        return binder.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        RealmVM.writeSuccess.observe(viewLifecycleOwner, Observer {

            if (RealmVM.writeSuccess.value == true) {
                UserList = RealmVM.readResult.value as List<RealmUsers>
                for (user in UserList)
                    tv_response.append("--->  ${user.userId} " + " ${user.name} " + " ${user.location} ")
            } else {
                tv_response.append("Try Again !!!")

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.settings -> {
                Toast.makeText(context, "Settings selected", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.logout -> {
                findNavController().navigate(R.id.action_realmFragment_to_loginFragment)
                return true
            }
            R.id.exit -> {
                activity?.finishAffinity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
