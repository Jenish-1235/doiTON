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

class tasks_fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tasks_fragment, container, false)

        val task1 = Task(
            "Go and have clothes try on at adidas",
            false,
            "Get a photo with shayamal and mint it to app ... ",
            0.002,
            R.drawable.ic_adidas
        )

        val task2 = Task(
            "Click photo with Shashank",
            false,
            "Get a photo with shashank and mint it to app ... ",
            0.002,
            R.drawable.ic_ton
        )

        val task3 = Task(
            "Check in at Hacker House",
            false,
            "Scan our QR code to check in ...",
            0.002,
            R.drawable.ic_ton
        )

        val task4 = Task(
            "Submit your project",
            false,
            "Submit project and mint screenshot ...",
            0.002,
            R.drawable.ic_ton
        )

        var tasks = ArrayList<Task>()
        tasks.add(task3)
        tasks.add(task2)
        tasks.add(task1)
        tasks.add(task4)

        val recyclerView = view.findViewById<RecyclerView>(R.id.dailyTaskRecyclerView)
        recyclerView.adapter = DailyTaskListAdapter(requireContext(), tasks)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        return view
    }
}