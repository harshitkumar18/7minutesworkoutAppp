package com.example.a7minutesworkoutapp

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciselist = ArrayList<ExerciseModel>()
        val jumpingjacks = ExerciseModel(
            1,"Jumping Jacks",
            R.drawable.ic_jumping_jacks,false,false
        )
        exerciselist.add(jumpingjacks)
        val abdominal_crunch = ExerciseModel(
            2,"Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,false,false
        )
        exerciselist.add(abdominal_crunch)
        val high_kness = ExerciseModel(
            3,"High Knees",
            R.drawable.ic_high_knees_running_in_place,false,false
        )
        exerciselist.add(high_kness)
        val lunge = ExerciseModel(
            4,"Lunge",
            R.drawable.ic_lunge,false,false
        )
        exerciselist.add(lunge)
        val plank = ExerciseModel(
            5,"Plank",
            R.drawable.ic_plank,false,false
        )
        exerciselist.add(plank)
        val push_up = ExerciseModel(
            6,"Push Up",
            R.drawable.ic_push_up,false,false
        )
        exerciselist.add(push_up)
        val push_up_rotation = ExerciseModel(
            7,"Push Up Rotation",
            R.drawable.ic_push_up_and_rotation,false,false
        )
        exerciselist.add(push_up_rotation)
        val side_plank = ExerciseModel(
            8,"Side Plank",
            R.drawable.ic_side_plank,false,false
        )
        exerciselist.add(side_plank)
        val squat = ExerciseModel(
            9,"Squat",
            R.drawable.ic_squat,false,false
        )
        exerciselist.add(squat)
        val step_up_onto_chair = ExerciseModel(
            10,"Step Up Onto Chair",
            R.drawable.ic_step_up_onto_chair,false,false
        )
        exerciselist.add(step_up_onto_chair)
        val triceps_dp_on_chair = ExerciseModel(
            11,"Triceps Dip on Chair",
            R.drawable.ic_triceps_dip_on_chair,false,false
        )
        exerciselist.add(triceps_dp_on_chair)
        val wall_sit = ExerciseModel(
            12,"Wall Sit",
            R.drawable.ic_wall_sit,false,false
        )
        exerciselist.add(wall_sit)




        return exerciselist
    }
}