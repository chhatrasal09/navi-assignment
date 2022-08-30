package com.app.myapplication.ui.closepullrequest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.app.myapplication.base.ui.BaseInternetActivity
import com.app.myapplication.databinding.ActivityMainBinding
import com.app.myapplication.ui.nointernet.NoInternetFragment
import com.app.myapplication.util.NetworkUtility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseInternetActivity() {

    private lateinit var binding: ActivityMainBinding
    private var noInternetFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        supportFragmentManager.commitNow {
            add(binding.fragmentContainer.id, ClosedRepoFragment())
        }
        supportFragmentManager.commitNow {
            noInternetFragment?.let {
                if (it.isAdded)
                    remove(it)
                add(android.R.id.content, it)
            } ?: kotlin.run {
                internetStatus(NetworkUtility.isNetworkAvailable(this@MainActivity))
            }
        }
    }

    override fun internetStatus(available: Boolean) {
        super.internetStatus(available)
        if (available) {
            supportFragmentManager.commit {
                remove(noInternetFragment ?: return@commit)
            }
        } else {
            noInternetFragment = NoInternetFragment()
            supportFragmentManager.commit {
                add(android.R.id.content, noInternetFragment ?: return@commit)
            }
        }
    }
}