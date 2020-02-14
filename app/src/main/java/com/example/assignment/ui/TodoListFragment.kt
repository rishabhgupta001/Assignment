package com.example.assignment.ui


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.network.NetworkState
import com.example.assignment.data.network.StatusCode
import com.example.assignment.databinding.FragmentTodoListBinding
import com.example.assignment.ui.adapter.TodoAdapter
import com.example.assignment.utils.Constants.DEFAULT_SUBTODO
import com.example.assignment.utils.Constants.KEY_SUBTODO
import com.example.assignment.utils.Utils
import kotlinx.android.synthetic.main.fragment_todo_list.*
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
 * Modified on February 14, 2020
 *
 * */
class TodoListFragment : Fragment(), KodeinAware, View.OnClickListener {

    override val kodein by kodein()
    private val factory: TodoViewModelFactory by instance()

    private lateinit var adapter: TodoAdapter
    private val TAG = TodoListFragment::class.java.simpleName

    private val viewModel: TodoViewModel by lazy {
        ViewModelProvider(this, factory).get(TodoViewModel::class.java)
    }

    //handle view clicks
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.reload_text_view -> {
                reload_text_view.isEnabled = false
                Handler().postDelayed({

                    reload_text_view.isEnabled = true
                    viewModel.refresh()

                }, 100)
            }
        }
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
        reload_text_view.setOnClickListener(this)
        setUpRecyclerViewData()
        observeNetworkState()
        initSwipeRefresh()
        val subTodo = savedInstanceState?.getString(KEY_SUBTODO) ?: DEFAULT_SUBTODO
        viewModel.showSubTodo(subTodo)
    }

    /**
     * initalise recyclerview
     */
    private fun setUpRecyclerViewData() {
        swipeRefresh.setColorSchemeColors(Color.RED, Color.RED, Color.RED, Color.RED)
        todo_recycler_view.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = TodoAdapter()

        //get todoList
        viewModel.todoList.observe(viewLifecycleOwner, Observer { list ->
            //Submits a new list to be diffed, and displayed.
            adapter.submitList(list)
        })

        todo_recycler_view.adapter = adapter
    }

    /**
     * Method to observe status of data loading from web services
     */
    private fun observeNetworkState() {
        //get the Network state (on hitting api respone success,error an all..)
        viewModel.networkState.observe(viewLifecycleOwner,
            Observer<NetworkState> { networkState ->
                when (networkState.statusCode) {
                    StatusCode.START -> swipeRefresh.isRefreshing = true

                    StatusCode.SUCCESS -> {
                        swipeRefresh.isRefreshing = false
                        showReloadView(false)
                    }

                    StatusCode.ERROR -> {
                        Log.d(TAG, "ERROR ${networkState.msg}")
                        swipeRefresh.isRefreshing = false
                        showReloadView(true)
                    }
                }
            })
    }


    /**
     * Method to handle pull to refresh functionality
     */
    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_SUBTODO, viewModel.getCurrentSubTodo())
    }

    /**
     * Method to show msg when something went wrong
     */
    private fun showReloadView(constraint: Boolean) {
        if (constraint) {
            Utils.showFadeInAnimOnText(
                context!!,
                something_text_view,
                getString(R.string.text_something_went_wrong)
            )
            Utils.showFadeInAnimOnText(
                context!!,
                give_it_text_view,
                getString(R.string.text_give_it_another_try)
            )
            Utils.showFadeInAnimOnText(context!!, reload_text_view, getString(R.string.text_reload))
        } else {
            something_text_view.visibility = getVisiblility(constraint)
            give_it_text_view.visibility = getVisiblility(constraint)
            reload_text_view.visibility = getVisiblility(constraint)
        }

        todo_recycler_view.visibility = getVisiblility(!constraint)
    }

    /**
     * Method returns Visibility state Visible: Gone
     */
    fun getVisiblility(constraint: Boolean): Int = if (constraint) View.VISIBLE else View.GONE

}
