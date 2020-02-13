package com.example.assignment.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.assignment.R
import com.example.assignment.databinding.FragmentTodoListBinding
import com.example.assignment.ui.adapter.TodoAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 *
​
 * Purpose – Class observers list from ViewModel and  populates it into UI
​
 * @author ​Rishabh Gupta
​
 * Created on February 13, 2020
​
 * Modified on February 13, 2020
 *
 * */
class TodoListFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: TodoViewModelFactory by instance()

    private lateinit var adapter: TodoAdapter

    private val viewModel: TodoViewModel by lazy {
        ViewModelProvider(this, factory).get(TodoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTodoListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(savedInstanceState)
    }

    /**
     * initial setUp
     */
    private fun init(savedInstanceState: Bundle?) {
        /* setUpRecyclerViewData()
         observeNetworkState()
         initSwipeRefresh()*/
        /* val subDelivery = savedInstanceState?.getString(KEY_SUBDELIVERY) ?: DEFAULT_SUBDELIVERY
         viewModel.showSubdelivery(subDelivery)*/
    }


}
