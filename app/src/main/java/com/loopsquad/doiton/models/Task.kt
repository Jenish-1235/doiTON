package com.loopsquad.doiton.models

import com.loopsquad.doiton.R

class Task {
   constructor()

    var taskName: String = "Task1"
    var taskStatus: Boolean = false
    var taskDescription: String = "Task1 Description"
    var taskPrice: Double = 0.0
    var image: Int = 0

    constructor(taskName: String, taskStatus: Boolean, taskDescription: String, taskPrice: Double, image: Int) {
        this.taskName = taskName
        this.taskStatus = taskStatus
        this.taskDescription = taskDescription
        this.taskPrice = taskPrice
        this.image = image
    }

}