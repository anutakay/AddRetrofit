package com.issart.boilerplate2.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.issart.boilerplate2.AppViewModelFactory
import com.issart.boilerplate2.R
import com.issart.boilerplate2.presentation.viewmodels.SimpleViewModel
import kotlinx.android.synthetic.main.fragment_simple.*

class SimpleFragment : Fragment() {
    private var outputPort: InteractionListener? = null
    private lateinit var viewModel: SimpleViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            outputPort = context
        } else {
            throw RuntimeException("$context must implement InteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        outputPort = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
            .of(this, AppViewModelFactory)
            .get(SimpleViewModel::class.java)

        viewModel.message.observe(this, Observer {
            message.text = it
        })
        viewModel.secondMessage.observe(this, Observer {
            second_message.text = it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMessages()
    }

    interface InteractionListener

    companion object {
        @JvmStatic
        fun newInstance() = SimpleFragment()
    }
}