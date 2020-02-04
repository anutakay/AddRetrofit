package com.issart.addretrofit.presentation.ui.languages

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.issart.addretrofit.AppViewModelFactory
import com.issart.addretrofit.R
import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.presentation.IntentUtil
import com.issart.addretrofit.presentation.IntentUtil.INPUT_LANGUAGE
import com.issart.addretrofit.presentation.IntentUtil.OUTPUT_LANGUAGE
import kotlinx.android.synthetic.main.fragment_languages.*
import kotlinx.android.synthetic.main.languages_current.*
import kotlinx.android.synthetic.main.languages_list.*

class LanguagesFragment : Fragment(), LanguagesRecyclerViewAdapter.InteractionListener {
    private val adapter: LanguagesRecyclerViewAdapter = LanguagesRecyclerViewAdapter()

    private var outputPort: InteractionListener? = null
    private lateinit var viewModel: LanguagesViewModel

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
        adapter.outputPort = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.outputPort = this

        viewModel = ViewModelProviders
            .of(this, AppViewModelFactory)
            .get(LanguagesViewModel::class.java)

        when (activity?.intent?.getIntExtra(IntentUtil.LANGUAGE_EXTRA, -1)) {
            INPUT_LANGUAGE -> {
            }
            OUTPUT_LANGUAGE -> {
            }
        }

        viewModel.list.observe(this, Observer {
            adapter.values = it
        })

        viewModel.openLanguages.observe(this, Observer {
            source_language.text = it.inputName
            result_language.text = it.outputName
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_languages, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        outputPort?.setCustomToolbar(toolbar)

        with(list) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = this@LanguagesFragment.adapter
        }

        viewModel.loadOpenLanguages()
        viewModel.loadLanguagesList()
    }

    override fun onSelected(lang: Languages) {
        viewModel.selectLanguages(lang)
        outputPort?.goToDictionary(lang)
    }

    interface InteractionListener {
        fun setCustomToolbar(toolbar: Toolbar)
        fun goToDictionary(lang: Languages)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguagesFragment()
    }
}