package com.example.a7minuteworkoutapplication

object constants {
    fun defaultExerciseList(): ArrayList<exercisemodel>{
        val exerciselist=ArrayList<exercisemodel>()
        val jumpingjacks=exercisemodel(
            1,
            "JUMPING JACKS",
            R.drawable.jumping_jacks,
            false,
             false

        )
        exerciselist.add(jumpingjacks)


        val abdominal_crunch=exercisemodel(
            2,
            "ABDOMINAL CRUNCH",
            R.drawable.abdominal_crunch,
            false,
            false
        )
        exerciselist.add(abdominal_crunch)


        val high_knees=exercisemodel(
            3,
            "HIGH KNEES",
            R.drawable.high_knees,
            false,
            false

        )
        exerciselist.add(high_knees)


        val lunge=exercisemodel(
            4,
            "LUNGES",
            R.drawable.lunges,
            false,
            false

        )
        exerciselist.add(lunge)


        val plank=exercisemodel(
            5,
            "PLANK",
            R.drawable.plank1,
            false,
            false

        )
        exerciselist.add(plank)


        val push_up=exercisemodel(
            6,
            "PUSH UP",
            R.drawable.ic_push_up,
            false,
            false

        )
        exerciselist.add(push_up)


        val pushup_rotation=exercisemodel(
            7,
            "PUSH AND ROTATION",
            R.drawable.ic_push_up_and_rotation,
            false,
            false

        )
        exerciselist.add(pushup_rotation)


        val side_plank=exercisemodel(
            8,
            "SIDE PLANK",
            R.drawable.side_plank,
            false,
            false

        )
        exerciselist.add(side_plank)


        val squat=exercisemodel(
            9,
            "SQUATS",
            R.drawable.squats1,
            false,
            false

        )
        exerciselist.add(squat)


        val triceps=exercisemodel(
            10,
            "TRICEPS DIP ON SHOULDER",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false

        )
        exerciselist.add(triceps)


        val wall_sit=exercisemodel(
            11,
            "WALL SIT",
            R.drawable.new1_wallsit,
            false,
            false

        )
        exerciselist.add(wall_sit)


        val seton_chair=exercisemodel(
            12,
            "STEP UP ON CHAIR",
            R.drawable.ic_step_up_onto_chair,
            false,
            false

        )
        exerciselist.add(seton_chair)
        return exerciselist

    }


}