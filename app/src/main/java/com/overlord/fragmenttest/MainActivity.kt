package com.overlord.fragmenttest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment

fun BaseActivity.loadFragment(containerID : Int, fragment : Fragment) {
    val currentFragment = supportFragmentManager.findFragmentById(containerID)

    if (currentFragment == null)
        supportFragmentManager.beginTransaction()
            .add(containerID, fragment)
            .commit()
    else
        supportFragmentManager.beginTransaction()
            .replace(containerID, fragment)
            .commit()
}

fun BaseActivity.snackbar(message : String) {
    // android.R.id.content Points to the layout file
    Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
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
            null,
            object : SecondFragment.FragmentInteractor {
                override fun onSwitchFragmentButtonPressed() {
                    snackbar("Switch Called")
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(containerID, createMainFragment())
    }
}
