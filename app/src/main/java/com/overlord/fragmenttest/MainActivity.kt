package com.overlord.fragmenttest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun BaseActivity.loadFragment(containerID : Int, fragment : Fragment) {
    val currentFragment = supportFragmentManager.findFragmentByTag(fragment.getName())
    val fragmentsOnTopDestroyed = supportFragmentManager.popBackStackImmediate(fragment.getName(), 0)

    val fragmentNotInBackStack = (currentFragment == null) && (! fragmentsOnTopDestroyed)

    if (fragmentNotInBackStack)
        supportFragmentManager.beginTransaction()
            .replace(containerID, fragment)
            .addToBackStack(fragment.getName())
            .commit()
}

fun BaseActivity.snackbar(message : String) {
    // android.R.id.content Points to the layout file
    Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
}

fun AppCompatActivity.getName() : String {
    return this.javaClass.simpleName
}

fun Fragment.getName() : String {
    return this.javaClass.simpleName
}


class MainActivity : BaseActivity() {


    private var containerID = R.id.fragmentContainer

    private fun createMainFragment() : MainFragment {
        return MainFragment.newInstance(
            MainFragment.FragmentInputs("Shibasis", "Patnaik"),

            object : MainFragment.FragmentInteractor {
                override fun onSnackbarButtonPressed(message: String) {
                    snackbar(message)
                }

                override fun onSwitchFragmentButtonPressed() {
                    loadFragment(containerID, createSecondFragment())
                }
            }
        )
    }

    private fun createSecondFragment() : SecondFragment {
        return SecondFragment.newInstance(
            SecondFragment.FragmentInputs("Diksha", "Agarwal"),
            object : SecondFragment.FragmentInteractor {
                override fun onSwitchFragmentButtonPressed() {
                    snackbar("Switch Called")
                    loadFragment(containerID, createMainFragment())
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(containerID, createMainFragment())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }
}
