package com.issart.addretrofit.presentation.ui.dictionary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.issart.addretrofit.AppViewModelFactory
import com.issart.addretrofit.R
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_INPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_OUTPUT_LANGUAGE
import kotlinx.android.synthetic.main.dictionary.*

class DictionaryFragment : Fragment() {
    private var outputPort: InteractionListener? = null
    private lateinit var viewModel: DictionaryViewModel

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
            .get(DictionaryViewModel::class.java)

        viewModel.input.observe(this, Observer {
            source_language.text = it
        })

        viewModel.output.observe(this, Observer {
            result_language.text = it
        })

        viewModel.word.observe(this, Observer {
            word.text = it
        })

        viewModel.lookupResult.observe(this, Observer {
            dictionary_message.text = it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dictionary, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        source_language.setOnClickListener {
            outputPort?.chooseInputLanguage()
        }

        result_language.setOnClickListener {
            outputPort?.chooseOutputLanguage()
        }

        viewModel.load()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("onActivityResult(activity)")
        when (requestCode) {
            CHOOSE_INPUT_LANGUAGE, CHOOSE_OUTPUT_LANGUAGE -> {
                viewModel.loadOpenLanguages()
            }
        }
    }

    interface InteractionListener {
        fun chooseInputLanguage()
        fun chooseOutputLanguage()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryFragment()
    }
}