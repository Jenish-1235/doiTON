package com.loopsquad.doiton.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopsquad.doiton.R
import com.loopsquad.doiton.adapter.DailyTaskListAdapter
import com.loopsquad.doiton.models.Task
import com.loopsquad.doiton.services.WalletInfoProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class home_fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_fragment, container, false)

        val task1 = Task(
            "Click your smiling selfie...",
            false,
            "Get a smiling selfie and mint it ... ",
            0.002,
            R.drawable.ic_camera
        )

        val task2 = Task(
            "Explore a local business",
            false,
            "explore a local business and mint ... ",
            0.002,
            R.drawable.ic_explore
        )

        val task3 = Task(
            "Have tea at near XYZ tea stall",
            false,
            "Scan our QR code to get a tea ...",
            0.002,
            R.drawable.ic_tea_cup
        )

        var tasks = ArrayList<Task>()
        tasks.add(task3)
        tasks.add(task2)
        tasks.add(task1)

        val recyclerView = view.findViewById<RecyclerView>(R.id.dailyTaskRecyclerView)
        recyclerView.adapter = DailyTaskListAdapter(requireContext(), tasks)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val newCoroutine = CoroutineScope(Dispatchers.IO).launch {

            val sharedPreferences = requireContext().getSharedPreferences("WalletAddress", 0)
            val walletAddress = sharedPreferences.getString("WalletAddress", "")
            val walletInfoProvider = WalletInfoProvider()
            walletInfoProvider.getAccountBalance(walletAddress!!)
        }
        newCoroutine.start()

        return view
    }

}