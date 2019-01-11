package com.overlord.fragmenttest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.Serializable

class MainFragment : Fragment() {

    class FragmentInputs(val firstName : String, val surname : String) : Serializable

    interface FragmentInteractor : Serializable {
        fun onSnackbarButtonPressed(message: String)
        fun onSwitchFragmentButtonPressed()
    }

    private lateinit var inputs: FragmentInputs
    private lateinit var interactor: FragmentInteractor

    companion object {
        @JvmStatic
        fun newInstance(inputs : FragmentInputs, interactor : FragmentInteractor) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("inputs", inputs)
                    putSerializable("interactor", interactor)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputs = arguments?.getSerializable("inputs") as FragmentInputs
        interactor = arguments?.getSerializable("interactor") as FragmentInteractor

        //Initialize Heavier things here because onCreateView and onViewCreated are called much more number of times
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?) : View?
            = inflater.inflate(R.layout.fragment_main, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSnackbar.setOnClickListener {
            interactor.onSnackbarButtonPressed("${inputs.firstName} ${inputs.surname}")
        }

        buttonSwitchFragment.setOnClickListener {
            interactor.onSwitchFragmentButtonPressed()
        }
    }
}
