package com.overlord.fragmenttest

import android.os.Bundle
import android.support.v4.app.Fragment
import java.io.Serializable
import java.lang.reflect.Constructor

abstract class BaseFragment : Fragment() {

    val baseActivity = activity as BaseActivity?

    class FragmentInputs : Serializable
    interface FragmentInteractor

    protected abstract var inputs: FragmentInputs
    protected abstract var interactor: FragmentInteractor
}