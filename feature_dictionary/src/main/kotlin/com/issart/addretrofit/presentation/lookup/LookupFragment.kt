package com.issart.addretrofit.presentation.lookup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_INPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.CHOOSE_OUTPUT_LANGUAGE
import com.issart.addretrofit.R
import com.issart.addretrofit.core.AppViewModelFactory
import com.issart.addretrofit.core.di.BaseInjectHelper
import com.issart.addretrofit.di.DaggerDictionaryComponent
import kotlinx.android.synthetic.main.dictionary.*
import javax.inject.Inject

class LookupFragment : Fragment() {
    private var outputPort: InteractionListener? = null

    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var viewModel: LookupViewModel

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
        DaggerDictionaryComponent.builder()
            .plus(BaseInjectHelper.provideBaseComponent(activity!!.application))
            .build()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)[LookupViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dictionary, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        source_language.setOnClickListener { outputPort?.chooseInputLanguage() }
        result_language.setOnClickListener { outputPort?.chooseOutputLanguage() }

        with(viewModel) {
            input.observe(viewLifecycleOwner, Observer { source_language.text = it })
            output.observe(viewLifecycleOwner, Observer { result_language.text = it })
            word.observe(viewLifecycleOwner, Observer { word_label.text = it })
            lookupResult.observe(viewLifecycleOwner, Observer { dictionary_message.text = it })
            initViewModel()
            setWord("rain")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            CHOOSE_INPUT_LANGUAGE, CHOOSE_OUTPUT_LANGUAGE -> viewModel.initViewModel()
        }
    }

    interface InteractionListener {
        fun chooseInputLanguage()
        fun chooseOutputLanguage()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LookupFragment()
    }
}