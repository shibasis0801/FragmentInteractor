package com.overlord.fragmenttest

import android.os.Bundle
import android.support.v4.app.Fragment
import java.io.Serializable
import java.lang.reflect.Constructor

abstract class BaseFragment(val input : FragmentInputs, val interaction : FragmentInteractor)
    : Fragment() {

    val baseActivity = activity as BaseActivity?

    abstract class FragmentInputs : Serializable
    interface FragmentInteractor : Serializable

    protected abstract var inputs: FragmentInputs
    protected abstract var interactor: FragmentInteractor
}
