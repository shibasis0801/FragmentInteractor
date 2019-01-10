package com.overlord.fragmenttest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_second.*
import java.io.Serializable

class SecondFragment : Fragment() {

    class FragmentInputs(val firstName : String, val surname : String) : Serializable

    interface FragmentInteractor {
        fun onSwitchFragmentButtonPressed()
    }

    private var inputs: FragmentInputs? = null
    private lateinit var interactor: FragmentInteractor

    companion object {
        @JvmStatic
        fun newInstance(inputs : FragmentInputs?, interactor : FragmentInteractor) =
            SecondFragment().apply {
                this.interactor = interactor
                inputs?.let { arguments = Bundle().apply { putSerializable("inputs", inputs) } }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputs = arguments?.getSerializable("inputs") as FragmentInputs?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?) : View?
            = inflater.inflate(R.layout.fragment_second, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSwitchFragment.setOnClickListener {
            interactor.onSwitchFragmentButtonPressed()
        }
    }
}
